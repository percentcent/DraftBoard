package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.BasicStroke;
import java.util.ArrayList;
import java.awt.Graphics2D;
import listener.EraserPanel;
import listener.WidthPanel;

public class Eraser implements Shape{
	
	private ArrayList<Point> points;
	private Color c;
	private int width;

//initialize object Eraser, color, width, points
	public Eraser(ArrayList<Point> points, Color c,int w) {
		this.points=points;
		this.c=c;
		this.width=w;

	}
	

	@Override
	public void drawShape(Graphics2D g2) {
		int x1,x2,y1,y2;
		g2.setStroke(new BasicStroke(width)); 
		g2.setColor(c);
		
		for(int j = 0; j < points.size()-1; j++)
		{
			x1 = (int)points.get(j).getX();
			y1 = (int)points.get(j).getY();
			x2 = (int)points.get(j+1).getX();
			y2 = (int)points.get(j+1).getY();
			g2.drawLine(x1, y1, x2, y2);
		}
		
      	g2.setStroke(new BasicStroke(EraserPanel.width));

	}

}
