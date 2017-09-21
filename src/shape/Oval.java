package shape;

import java.awt.*;
import listener.ColorChooser;
import listener.WidthPanel;

public class Oval implements Shape {
	

	private int x1, x2,y1,y2;
	private Color c;
	private int width;
	
	public Oval(int x1, int x2, int y1, int y2, Color c,int w) {
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.c=c;
		this.width=w;
	}

	@Override
	public void drawShape(Graphics2D g2) {
		g2.setColor(c);
		g2.setStroke(new BasicStroke(width));
		g2.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		g2.setColor(ColorChooser.color);
      	g2.setStroke(new BasicStroke(WidthPanel.width));
	}

}
