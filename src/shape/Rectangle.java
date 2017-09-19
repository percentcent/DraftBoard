package shape;

import java.awt.*;

public class Rectangle implements Shape {
	

	private int x1, x2,y1,y2;
	private Color c;
	
	public Rectangle(int x1, int x2, int y1, int y2, Color c) {
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.c=c;
	}

	@Override
	public void drawShape(Graphics g) {
		g.setColor(c);
		g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
	}

}
