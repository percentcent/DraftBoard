package client;

import listener.DrawPan;
import shape.Shape;
import listener.Menu;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import javax.swing.JMenuBar; 

public class ClientManager extends JFrame {
	public static List<Shape> shapes;

	//DisplayArea
	public static DrawPan displayArea;

	//commandArea
	JPanel commandArea;

	//ManagerArea
	JPanel pnlFile;
	
	//Menu Bar
	Menu menu;

	/*Define shape types*/
	public final static int LINE = 0;
	public final static int CIRCLE = 1;
	public final static int RECT = 2;
	public final static int OVAL = 3;
	public final static int FREE = 4;
	public final static int TEXT = 5;
	public final static int ERASER = 6;
	public static int type = FREE;

	public ClientManager() {
		shapes = new ArrayList<Shape>();
		this.setLayout(new FlowLayout());
		this.setTitle("MyWriteBoard");
		this.setSize(900, 680);
		displayArea = new DrawPan();//initialize our canvas
		commandArea = new CommandArea();//initialize out command area
		menu = new Menu();
//		JMenu file = new JMenu("File");
//		menuBar.add(file);
        //displayArea.setShape("freehand");
		this.add(commandArea);
		this.add(displayArea);
		setJMenuBar(menu);
		this.setVisible(true);//show the Window
		
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.out.println("Exit when Closed event");
			System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new ClientManager();
	}
}


