package listener;

import shape.Circle;
import shape.Line;
import shape.Shape;
import shape.Rectangle;
import shape.Text;
import shape.Oval;
import shape.Freehand;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawPan extends JPanel implements ActionListener, MouseListener,MouseMotionListener {
	private String shape;
	private Color color;
	private java.util.List<shape.Shape> shapesArray = new ArrayList<shape.Shape>();
	private Graphics g = getGraphics();
	int startX,startY,endX,endY;
	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<ArrayList<Point>> actulpoints = new ArrayList<ArrayList<Point>>();

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public DrawPan()
	{
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
		for (Shape shape: shapesArray)
		{
			shape.drawShape(g);
		}

		switch(getShape())
		{
			case "line" :
				g.drawLine(startX, startY, endX, endY);
				break;
			case "circle" :
				g.drawOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startX - endX));
				break;
			case "rectangle" :
				g.drawRect(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY));
				break;
			case "oval" :
				g.drawOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY));
				break;
			case "freehand":
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
		if (getShape() == "freehand")
		{
			points.clear();
			points.add(e.getPoint());
		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		endX = e.getX();
		endY = e.getY();
		switch(getShape())
		{
			case "line" :
				shapesArray.add(new Line(startX, startY, endX, endY, Color.BLACK));
				break;
			case "circle" :
				shapesArray.add(new Circle(startX, endX, startY, endY, Color.BLACK));
				break;
			case "rectangle" :
				shapesArray.add(new Rectangle(startX, endX, startY, endY, Color.BLACK));
				break;
			case "oval" :
				shapesArray.add(new Oval(startX, endX, startY, endY, Color.BLACK));
				break;
			case "freehand":
				ArrayList<Point> midpoints = new ArrayList<Point>();
				for (int i = 0; i < points.size() - 1 ; i ++)
				{
					midpoints.add(points.get(i).getLocation());
				}
				actulpoints.add(midpoints);
				shapesArray.add(new Freehand(actulpoints,Color.BLACK));
				break;
			case "text":
				String inputValue = JOptionPane.showInputDialog("Please input a value");
				String text = inputValue;
				shapesArray.add(new Text(startX, endX, startY, endY, Color.BLACK,text));
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
		if(getShape() == "freehand")
		{
			points.add(new Point(endX,endY));
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}