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

import yanomonitor.YanoData.*;

public class Yanomonitor{

	private Datas datas;


	//Vector<Node> anonyms;
	//Vector<Relations> relation;

	public static void main(String[] args){
		Yanomonitor monitor = new Yanomonitor();
		try{
			ServerSocket sersock = new ServerSocket(4444);
			System.out.println("Waiting for connection...");
			Socket soc = sersock.accept();
			System.out.println("Connected");

			monitor.datas = Datas.parseFrom(soc.getInputStream());


			Window w = new Window(monitor.datas,Integer.parseInt(args[0]),Integer.parseInt(args[1]));

		}catch(Exception e){System.out.println(e.toString());}
	}

}