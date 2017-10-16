package listener;

import client.ClientManager;
import shape.*;
import shape.Image;
import shape.Shape;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class DrawPan extends JPanel implements ActionListener, MouseListener,MouseMotionListener {

	private Color color;
	int startX,startY,endX,endY;
	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<ArrayList<Point>> actulpoints = new ArrayList<ArrayList<Point>>();
	private ArrayList<ArrayList<Point>> erasepoints = new ArrayList<ArrayList<Point>>();

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public DrawPan()
	{
		this.setPreferredSize(new Dimension(600, 600));
		Border border = new LineBorder(Color.black);
		this.setBorder(border);
		this.setBackground(Color.white);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	@Override
	protected void paintComponent(Graphics graphics)
	{
		int x1 = startX;
		int y1 = startY;
		Graphics2D g = (Graphics2D) graphics;

		super.paintComponent(g);
		for (Shape shape: ClientManager.shapes)
		{
				shape.drawShape(g);

		}

		switch(ClientManager.type)
		{
			case 0 :
				g.setStroke(new BasicStroke(WidthPanel.width));
				g.setColor(ColorChooser.color);
				g.drawLine(startX, startY, endX, endY);
				break;
			case 1 :
				g.setStroke(new BasicStroke(WidthPanel.width));
				g.setColor(ColorChooser.color);
				g.drawOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startX - endX));
				break;
			case 2 :
				g.setStroke(new BasicStroke(WidthPanel.width));
				g.setColor(ColorChooser.color);
				g.drawRect(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY));
				break;
			case 3 :
				g.setStroke(new BasicStroke(WidthPanel.width));
				g.setColor(ColorChooser.color);
				g.drawOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY));
				break;
			case 4:

				g.setStroke(new BasicStroke(WidthPanel.width));
				g.setColor(ColorChooser.color);
		
				for (int i = 0;i < points.size() -1;i++)
				{
					g.drawLine(points.get(i).x,points.get(i).y,points.get(i+1).x,points.get(i+1).y);
				}
				break;
			case 6:
				g.setStroke(new BasicStroke(EraserPanel.width));
				g.setColor(Color.white);
				for (int i = 0;i < points.size() -1;i++)
				{
					g.drawLine(points.get(i).x,points.get(i).y,points.get(i+1).x,points.get(i+1).y);
				}
				break;


		}




	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("cc");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
		if (ClientManager.type == 4||ClientManager.type == 6)
		{
			points.clear();
			points.add(e.getPoint());
		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				endX = e.getX();
				endY = e.getY();
				try {
				switch(ClientManager.type)
				{
					case 0:
						ClientManager.shapesList.add(new Line(startX, startY, endX, endY, ColorChooser.color,WidthPanel.width));
						//ClientManager.shapes.add(new Line(startX, startY, endX, endY, ColorChooser.color,WidthPanel.width));
						break;
					case 1 :
						ClientManager.shapesList.add(new Circle(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
						//ClientManager.shapes.add(new Circle(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
						break;
					case 2 :
						ClientManager.shapesList.add(new shape.Rectangle(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
						//ClientManager.shapes.add(new shape.Rectangle(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
						break;
					case 3 :
						ClientManager.shapesList.add(new Oval(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
						//ClientManager.shapes.add(new Oval(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
						break;
					case 4:
						ArrayList<Point> midpoints = new ArrayList<Point>();
						for (int i = 0; i < points.size() - 1 ; i ++)
						{
							midpoints.add(points.get(i).getLocation());
						}
						actulpoints.add(midpoints);
						ClientManager.shapesList.add(new Freehand(midpoints,ColorChooser.color,WidthPanel.width));
						//ClientManager.shapes.add(new Freehand(midpoints,ColorChooser.color,WidthPanel.width));
						break;
					case 5:
						String inputValue = JOptionPane.showInputDialog("Please input a value");
						if(inputValue.length() == 0)
						{
							break;
						}
						else
						{
							ClientManager.shapesList.add(new Text(startX, endX, startY, endY, Color.BLACK,inputValue));
							//ClientManager.shapes.add(new Text(startX, endX, startY, endY, Color.BLACK,inputValue));
							break;
						}
					case 6:
						ArrayList<Point> erapoints = new ArrayList<Point>();
						for (int i = 0; i < points.size() - 1 ; i ++)
						{
							erapoints.add(points.get(i).getLocation());
						}
						erasepoints.add(erapoints);
						ClientManager.shapesList.add(new Eraser(erapoints,Color.WHITE,EraserPanel.width));
						//ClientManager.shapes.add(new Eraser(erapoints,Color.WHITE,EraserPanel.width));
						break;
				}
				} catch(RemoteException e) {
					System.out.println("There is something problem with Server.");
				}
				repaint();
				//ClientManager.shapes.add(new Line(0,0,0,1,Color.WHITE,1));
				//repaint();
			}
		}).start();
//		endX = e.getX();
//		endY = e.getY();
//		switch(ClientManager.type)
//		{
//			case 0:
//				try {
//					ClientManager.shapesList.add(new Line(startX, startY, endX, endY, ColorChooser.color,WidthPanel.width));
//				} catch (RemoteException e1) {
//					e1.printStackTrace();
//				}
//				ClientManager.shapes.add(new Line(startX, startY, endX, endY, ColorChooser.color,WidthPanel.width));
//				break;
//			case 1 :
//				try {
//					ClientManager.shapesList.add(new Circle(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
//				} catch (RemoteException e1) {
//					e1.printStackTrace();
//				}
//				ClientManager.shapes.add(new Circle(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
//				break;
//			case 2 :
//				try {
//					ClientManager.shapesList.add(new Rectangle(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
//				} catch (RemoteException e1) {
//					e1.printStackTrace();
//				}
//				ClientManager.shapes.add(new Rectangle(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
//				break;
//			case 3 :
//				try {
//					ClientManager.shapesList.add(new Oval(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
//				} catch (RemoteException e1) {
//					e1.printStackTrace();
//				}
//				ClientManager.shapes.add(new Oval(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
//				break;
//			case 4:
//				ArrayList<Point> midpoints = new ArrayList<Point>();
//				for (int i = 0; i < points.size() - 1 ; i ++)
//				{
//					midpoints.add(points.get(i).getLocation());
//				}
//				actulpoints.add(midpoints);
//				try {
//					ClientManager.shapesList.add(new Freehand(midpoints,ColorChooser.color,WidthPanel.width));
//				} catch (RemoteException e1) {
//					e1.printStackTrace();
//				}
//				ClientManager.shapes.add(new Freehand(midpoints,ColorChooser.color,WidthPanel.width));
//				break;
//			case 5:
//				String inputValue = JOptionPane.showInputDialog("Please input a value");
//				if(inputValue.length() == 0)
//				{
//					break;
//				}
//				else
//				{
//					try {
//						ClientManager.shapesList.add(new Text(startX, endX, startY, endY, Color.BLACK,inputValue));
//					} catch (RemoteException e1) {
//						e1.printStackTrace();
//					}
//					ClientManager.shapes.add(new Text(startX, endX, startY, endY, Color.BLACK,inputValue));
//					break;
//				}
//			case 6:
//				ArrayList<Point> erapoints = new ArrayList<Point>();
//				for (int i = 0; i < points.size() - 1 ; i ++)
//				{
//					erapoints.add(points.get(i).getLocation());
//				}
//				erasepoints.add(erapoints);
//				try {
//					ClientManager.shapesList.add(new Eraser(erapoints,Color.WHITE,EraserPanel.width));
//				} catch (RemoteException e1) {
//					e1.printStackTrace();
//				}
//				ClientManager.shapes.add(new Eraser(erapoints,Color.WHITE,EraserPanel.width));
//				break;
//		}
//
//		repaint();


	}

	@Override
    //mouse in
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    //mouse excite
    public void mouseExited(MouseEvent e) {

    }
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		endX = e.getX();
		endY = e.getY();
		if(ClientManager.type == 4||ClientManager.type == 6)
		{
			points.add(new Point(endX,endY));
		}

		repaint();
//		BufferedImage image = new BufferedImage(ClientManager.displayArea.getWidth(),
//				ClientManager.displayArea.getHeight(),BufferedImage.TYPE_INT_RGB);
//		Image image1 = new Image(image,null);
//		try {
//
//			ClientManager.shapesList.castDraw(image1);
//		} catch (RemoteException e1) {
//			e1.printStackTrace();
//		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public void paintRmiShape(List<Shape> shapes) throws RemoteException {
		repaint();
		ClientManager.shapes = shapes;
		Graphics2D g = (Graphics2D) this.getGraphics();
		for (Shape shape: ClientManager.shapes)
		{
			shape.drawShape(g);
		}
		repaint();
	}

	public void drawImage(Image image) throws RemoteException {
		repaint();
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.drawImage(image.getImage(),0,0,this.getWidth(),this.getHeight(),this);
		repaint();
	}
}