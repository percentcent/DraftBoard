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
		Graphics g = graphics;
		for (Shape shape: ClientManager.shapes)
		{
			shape.drawShape(g);
		}

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
		if (ClientManager.type == 4)
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
				ClientManager.shapes.add(new Line(startX, startY, endX, endY, Color.BLACK));
				break;
			case 1 :
				ClientManager.shapes.add(new Circle(startX, endX, startY, endY, Color.BLACK));
				break;
			case 2 :
				ClientManager.shapes.add(new Rectangle(startX, endX, startY, endY, Color.BLACK));
				break;
			case 3 :
				ClientManager.shapes.add(new Oval(startX, endX, startY, endY, Color.BLACK));
				break;
			case 4:
				ArrayList<Point> midpoints = new ArrayList<Point>();
				for (int i = 0; i < points.size() - 1 ; i ++)
				{
					midpoints.add(points.get(i).getLocation());
				}
				actulpoints.add(midpoints);
				ClientManager.shapes.add(new Freehand(actulpoints,Color.BLACK));
				break;
			case 5:
				String inputValue = JOptionPane.showInputDialog("Please input a value");
				String text = inputValue;
				ClientManager.shapes.add(new Text(startX, endX, startY, endY, Color.BLACK,text));
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
		// TODO Auto-generated method stub
		
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		endX = e.getX();
		endY = e.getY();
		if(ClientManager.type == 4)
		{
			points.add(new Point(endX,endY));
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}