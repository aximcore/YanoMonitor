package yanomonitor;

import java.awt.Color;

public class Relations{
	Node a;
	Node b;
	String name;
	Color col;
public Relations(java.util.Vector<Node> vec,int a_,int b_, String name_){
	for (Node n : vec){
		if (n.get_id()==a_){
			a=n;
		}
		else
		{
			if(n.get_id()==b_){
				b=n;
			}
		}
	}
	name = name_;

	switch (name)
		{
		case "Relationship":
			col = new Color(0x80, 0x21, 0xaa,0x80);
			break;
		case "Csajom":
			col = new Color(0x80, 0xef, 0x9a,0x80);
			break;
		case "Fiúm":
			col = new Color(0x80, 0xef, 0x07,0x80);
			break;
		case "Muter":
			col = new Color(0x80, 0xf1, 0xec,0x80);
			break;
		case "Fater":
			col = new Color(0x80, 0x4d, 0x53,0x80);
			break;
		case "Tesó":
			col = new Color(0x80, 0x1d, 0x8e,0x80);
			break;
		case "Szomszéd":
			col = new Color(0x80, 0x80, 0xe3,0x80);
			break;
	}
}

	public Node get_a(){
		return a;
	}

	public Node get_b(){
		return b;
	}

	public Color get_color(){
		return col;
	}

	public String get_name(){
		return name;
	}
}