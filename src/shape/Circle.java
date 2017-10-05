package shape;

import listener.ColorChooser;
import listener.WidthPanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Circle implements Shape {
	

	private int x1, x2,y1,y2;
	private Color c;
	private int width;

	public Circle(int x1, int x2, int y1, int y2, Color c,int w) {
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.c=c;
		this.width=w;

	}
	
	public Circle(BufferedReader reader) throws IOException {
		char[] buf = new char[10];
		reader.read(buf, 0,10);
		this.c = new Color(Integer.parseInt(new String(buf)));
		
		reader.read(buf,0,10);
		this.width = Integer.parseInt(new String(buf));
		
		reader.read(buf,0,10);
		this.x1 = Integer.parseInt(new String(buf));
		
		reader.read(buf,0,10);
		this.y1 = Integer.parseInt(new String(buf));
		
		reader.read(buf,0,10);
		this.x2 = Integer.parseInt(new String(buf));
		
		reader.read(buf,0,10);
		this.y2 = Integer.parseInt(new String(buf));
		
	
	}

	@Override
	public void drawShape(Graphics2D g2)
	{
		g2.setColor(c);
		g2.setStroke(new BasicStroke(width));
		g2.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(x1 - x2));
		g2.setColor(ColorChooser.color);
		g2.setStroke(new BasicStroke(WidthPanel.width));

	}
	
	public void output(PrintWriter writer){
		writer.printf("C%010d%010d%010d%010d%010d%010d\r\n", c.getRGB(),width,x1,y1,x2,y2);
	}

}
