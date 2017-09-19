package shape;

import java.awt.*;

public class Circle implements Shape {
	

	private int x1, x2,y1,y2;
	private Color c;

	public Circle(int x1, int x2, int y1, int y2, Color c) {
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.c=c;

	}

	public void drawShape(Graphics g)
	{
		g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(x1 - x2));
	}

}
