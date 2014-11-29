import java.awt.Color;

public class Node{
	boolean isme;
	int x;
	int y;
	String name;
	int id;
	final static int diameter = 80;
	Color col;
public Node(int id_, String name_, int x_, int y_, boolean isme_){
	id = id_;
	name = name_;
	x = x_;
	y = y_;
	isme = isme_;
	col= new Color (0,0,0);
	switch (name){
		case "Party":
			col = new Color(0x01, 0x0a, 0xbf,0xb0);
			break;
		case "Fidesz":
			col = new Color(0xef, 0x9a, 0x2b,0xb0);
			break;
		case "MSZP":
			col = new Color( 0xef, 0x07, 0x0c,0xb0);
			break;
		case "KDNP":
			col = new Color( 0xf1, 0xec, 0x5b,0xb0);
			break;
		case "Jobbik":
			col = new Color( 0x4d, 0x53, 0x53,0xb0);
			break;
		case "LMP":
			col = new Color(0x1d, 0x8e, 0x06,0xb0);
			break;
		case "Együtt-PM":
			col = new Color(0xb0, 0xB0, 0x66,0xb0);
			break;
		case "DK":
			col = new Color(0xb0, 0xB0, 0x66,0xb0);
			break;
		case "YANO":
			col = new Color(0xb0, 0x80, 0xe3,0xb0);
			break;
	}
}

public Color get_color(){
	return col;
}

public int get_diam(){
	return diameter;
}

public int get_id(){
	return id;
}

public String get_name(){
	return name;
}

public int get_x(){
	return x;
}

public int get_y(){
	return y;
}

public int get_mid_x(){
	return x+(diameter/2);
}

public int get_mid_y(){
	return y+(diameter/2);
}

public boolean get_isme(){
	return isme;
}

public void set_x(int x_){
x=x_;
}

public void set_y(int y_){
y=y_;
}
}