package yanomonitor;

import javax.swing.JFrame;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Graphics;

public class Yanomonitor{
	Vector<Node> anonyms;
	Vector<Relations> relation;

	public static void main(String[] args){
		try{
			ServerSocket sersock = new ServerSocket(4444);
			System.out.println("Waiting for connection...");
			Socket s = sersock.accept();
			System.out.println("Connected");
			BufferedReader in= new BufferedReader(new InputStreamReader(s.getInputStream()));
				int n;
				int diffx=0;
				int diffy=0;
				String buff;
					Vector<Node> ano = new Vector<Node>();				
					n = Integer.parseInt(in.readLine());
					for (int i=0;i<n;i++){
						buff = in.readLine();
						StringTokenizer st = new StringTokenizer(buff);
						ano.add(new Node(Integer.parseInt(st.nextToken()) ,st.nextToken() ,Integer.parseInt(st.nextToken())
										 ,Integer.parseInt(st.nextToken()),Boolean.parseBoolean(st.nextToken())));
						if (ano.lastElement().get_isme()){
							diffx=Integer.parseInt(args[0])/2-ano.lastElement().get_x();
							diffy=Integer.parseInt(args[1])/2-ano.lastElement().get_y();
							ano.lastElement().set_x((Integer.parseInt(args[0])/2));
							ano.lastElement().set_y((Integer.parseInt(args[1])/2));
						}
					}


					Vector<Relations> rel = new Vector<Relations>();
					n = Integer.parseInt(in.readLine());
					for (int i=0;i<n;i++){
						buff = in.readLine();
						StringTokenizer st = new StringTokenizer(buff);
						rel.add(new Relations(ano,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),st.nextToken()));
					}

					Window w = new Window(ano,rel,Integer.parseInt(args[0]),Integer.parseInt(args[1]));

		}catch(Exception e){System.out.println(e.toString());}
	}

}