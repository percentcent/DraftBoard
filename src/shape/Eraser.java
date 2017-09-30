package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	public Eraser(FileReader reader) throws IOException {
		char[] buf = new char[10];
		
		reader.read(buf, 0,10);
		this.c = new Color(Integer.parseInt(new String(buf)));
		
		reader.read(buf,0,10);
		this.width = Integer.parseInt(new String(buf));
		
		char[] x = new char[10];
		char[] y = new char[10];
		this.points = new ArrayList<Point>();
		while(reader.read(x,0,10)!=-1 && reader.read(y,0,10)!=-1) {
			int newx = Integer.parseInt(new String(x));
			int newy = Integer.parseInt(new String(y));
			Point newpoint = new Point(newx,newy);
			points.add(newpoint);
		}
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

	public void output(PrintWriter writer){
		writer.printf("E%010d%010d", c.getRGB(),width);
		for(Point p : points) {
			writer.printf("%010d%010d", (int)p.getX(),(int)p.getY());
		}
		writer.printf("\r\n");
	}

}
