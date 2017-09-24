package listener;

import client.ClientManager;
import shape.*;
import shape.Rectangle;
import shape.Shape;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
		super.paintComponent(graphics);
		Graphics graph = graphics;
		Graphics2D g = (Graphics2D) graph;


		switch(ClientManager.type)
		{
			case 0 :
				g.drawLine(startX, startY, endX, endY);
				break;
			case 1 :
				g.drawOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startX - endX));
				break;
			case 2 :
				g.drawRect(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY));
				break;
			case 3 :
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


		for (Shape shape: ClientManager.shapes)
		{
			shape.drawShape(g);
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
		endX = e.getX();
		endY = e.getY();
		switch(ClientManager.type)
		{
			case 0:
				ClientManager.shapes.add(new Line(startX, startY, endX, endY, ColorChooser.color,WidthPanel.width));
				break;
			case 1 :
				ClientManager.shapes.add(new Circle(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
				break;
			case 2 :
				ClientManager.shapes.add(new Rectangle(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
				break;
			case 3 :
				ClientManager.shapes.add(new Oval(startX, endX, startY, endY, ColorChooser.color,WidthPanel.width));
				break;
			case 4:
				ArrayList<Point> midpoints = new ArrayList<Point>();
				for (int i = 0; i < points.size() - 1 ; i ++)
				{
					midpoints.add(points.get(i).getLocation());
				}
				actulpoints.add(midpoints);
				ClientManager.shapes.add(new Freehand(midpoints,ColorChooser.color,WidthPanel.width));
				break;
			case 5:
				String inputValue = JOptionPane.showInputDialog("Please input a value");
				if(inputValue.length() == 0)
				{
					break;
				}
				else
				{
					ClientManager.shapes.add(new Text(startX, endX, startY, endY, Color.BLACK,inputValue));
					break;
				}
			case 6:
				ArrayList<Point> erapoints = new ArrayList<Point>();
				for (int i = 0; i < points.size() - 1 ; i ++)
				{
					erapoints.add(points.get(i).getLocation());
				}
				erasepoints.add(erapoints);
				ClientManager.shapes.add(new Eraser(erapoints,Color.WHITE,EraserPanel.width));
				break;
		}

		repaint();

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
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}