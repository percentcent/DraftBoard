package shape;

import listener.ColorChooser;
import listener.WidthPanel;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
public class Line implements Shape {
	
    int x1, y1,x2, y2;
    private Color c;
    private int width;
    public Line(int x1,int y1,int x2,int y2,Color c,int w){
        this.c=c;
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.width=w;
    }
    
    public Line(BufferedReader reader) throws IOException {
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
    
    public void output(PrintWriter writer){
		writer.printf("L%010d%010d%010d%010d%010d%010d\r\n", c.getRGB(),width,x1,y1,x2,y2);
	}
    
    @Override
    public void drawShape(Graphics2D g2) {
        g2.setColor(c);
        g2.setStroke(new BasicStroke(width));
        g2.drawLine(x1, y1, x2, y2);
        g2.setColor(ColorChooser.color);
      	g2.setStroke(new BasicStroke(WidthPanel.width));
    }
    


}
