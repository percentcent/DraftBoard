package client;

import listener.DrawPan;
import listener.Menu;
import shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ClientManager extends JFrame {
	public static List<Shape> shapes;
	private String username;
    public  List<String> user = new ArrayList<>();

    public List<String> getUser() {
        return user;
    }

    public void setUser(List<String> user) {
        this.user = user;
    }

    //DisplayArea
	public static DrawPan displayArea;

	//commandArea
	JPanel commandArea;

	//ManagerArea
	JPanel pnlFile;
	
	//Menu Bar
	Menu menu;
	
	//ChatArea
	public JPanel chatArea;

	/*Define shape types*/
	public final static int LINE = 0;
	public final static int CIRCLE = 1;
	public final static int RECT = 2;
	public final static int OVAL = 3;
	public final static int FREE = 4;
	public final static int TEXT = 5;
	public final static int ERASER = 6;
	public static int type = FREE;
	
	public boolean isManager;

	public ClientManager() {
		username = "Han3";
		shapes = new ArrayList<Shape>();
		this.setLayout(new FlowLayout());
		this.setTitle("MyWriteBoard");
		this.setSize(1170, 680);
		displayArea = new DrawPan();//initialize our canvas
		commandArea = new CommandArea();//initialize out command area
//		menu = new Menu();
		chatArea = new ChatArea();
		this.add(commandArea);
		this.add(displayArea);
		
		this.add(chatArea);
		this.setVisible(true);//show the Window
		
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.out.println("Exit.");
			System.exit(0);
			}
		});
	}
	
	public void becomeManager() {
		menu = new Menu();
		setJMenuBar(menu);
		validate(); 
	}

	public static void main(String[] args) throws RemoteException, NotBoundException {
		ClientManager cm = new ClientManager();
	}
}


