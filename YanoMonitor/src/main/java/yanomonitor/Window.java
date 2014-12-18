package yanomonitor;

import java.awt.*;
import java.awt.geom.*;

import yanomonitor.YanoData.*;
import yanomonitor.Yanomonitor.*;
public class Window extends javax.swing.JFrame{
	private YanoData.Datas datas;
	//java.util.Vector<Node> anonyms;
	boolean imageLoaded;
	Image meIco;
	//java.util.Vector<Relations> relation;
	Window(/*java.util.Vector<Node> ano,java.util.Vector<Relations> rel,*/Datas datas,int x, int y){
		this.datas = datas;
		//anonyms=ano;
		//relation=rel;
		setBounds(10,10,x,y);
		setTitle("Yanonymous graphicon monitor");
		setVisible(true);
		imageLoaded=false;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void initPics(){
		imageLoaded=true;
		meIco = new javax.swing.ImageIcon("./Construct/res/drawable-hdpi/ic_launcher.png").getImage();
	}


	public void paint(Graphics g){
		if (g instanceof Graphics2D){
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			}
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(10F));

		for (YanoData.Relations rel : datas.getRelationsList() ){
			g.setColor(Color.cyan);
			g.drawLine(rel.getX1(),rel.getY1(),rel.getX2(),rel.getY2());
		}

		for ( YanoData.Node node : datas.getNodesList()){
			g.setColor(Color.BLACK);
			g.drawOval(node.getX(),node.getY(),10,10);
			Ellipse2D.Double circle = new Ellipse2D.Double(node.getX(),node.getY(),10,10);
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fill(circle);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Times New Roman",Font.PLAIN,25));
			g.drawString(node.getName().toString(),node.getX()/* TODO */-25,node.getY()/* TODO */);

			if ( node.getIsMe() ) {
				if (!imageLoaded)
					initPics();
				g2d.drawImage(meIco, node.getX(), node.getY(), 40, 40, null);
			}
		}
	}
}