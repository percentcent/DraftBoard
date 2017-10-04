
package shape;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.*;
import listener.WidthPanel;
import listener.ColorChooser;
public class Freehand implements Shape {
	


	private ArrayList<Point> points;
	private Color c;
	private int width;


	public Freehand(ArrayList<Point> points, Color c,int w) {
		this.points=points;
		this.c=c;
		this.width=w;

	}
	
	  public Freehand(BufferedReader reader) throws IOException {
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
				if(reader.read()=='.'){
					break;
				}
			}
		}

	@Override
	public void drawShape(Graphics2D g2) {
	
		int x1,x2,y1,y2;
		g2.setColor(c);
	    g2.setStroke(new BasicStroke(width));

		for(int j = 0; j < points.size()-1; j++)
		{
			x1 = (int)points.get(j).getX();
			y1 = (int)points.get(j).getY();
			x2 = (int)points.get(j+1).getX();
			y2 = (int)points.get(j+1).getY();
			g2.drawLine(x1, y1, x2, y2);
		}

		g2.setColor(ColorChooser.color);
      	g2.setStroke(new BasicStroke(WidthPanel.width));

	}
	
	public void output(PrintWriter writer){
		writer.printf("F%010d%010d", c.getRGB(),width);
		for(int i=0; i<points.size();i++) {
			writer.printf("%010d%010d", (int)points.get(i).getX(),(int)points.get(i).getY());
			if(i!=points.size()-1){
				writer.printf(",");
			} else {
				writer.printf(".");
			}
		}
		writer.printf("\r\n");
	}

}
