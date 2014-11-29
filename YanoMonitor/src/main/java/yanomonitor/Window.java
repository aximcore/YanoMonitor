package yanomonitor;

import java.awt.*;
import java.awt.geom.*;

public class Window extends javax.swing.JFrame{
	java.util.Vector<Node> anonyms;
	boolean imageLoaded;
	Image meIco;
	java.util.Vector<Relations> relation;
	Window(java.util.Vector<Node> ano,java.util.Vector<Relations> rel,int x, int y){
		anonyms=ano;
		relation=rel;
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
		for (Relations rel : relation){
			
			g.setColor(rel.get_color());
			g.drawLine(rel.get_a().get_mid_x(),rel.get_a().get_mid_y(),rel.get_b().get_mid_x(),rel.get_b().get_mid_y());
		}

		for (Node node: anonyms){
			g.setColor(Color.BLACK);
			g.drawOval(node.get_x(),node.get_y(),node.get_diam(),node.get_diam());
			Ellipse2D.Double circle = new Ellipse2D.Double(node.get_x(),node.get_y(),node.get_diam(),node.get_diam());
			g2d.setColor(node.get_color());
			g2d.fill(circle);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Times New Roman",Font.PLAIN,25));
			g.drawString(node.get_name(),node.get_mid_x()-25,node.get_mid_y());
			if (node.get_isme()){
				if (!imageLoaded){
						initPics();
				}
				g2d.drawImage(meIco,node.get_x(),node.get_y(),40,40,null);
			}
		}
	}
}