package shape;

import java.awt.*;
import java.util.ArrayList;

public class Freehand implements Shape {
	


	private ArrayList<ArrayList<Point>> points;
	private Color c;


	public Freehand(ArrayList<ArrayList<Point>> points, Color c) {
		this.points=points;
		this.c=c;

	}

	@Override
	public void drawShape(Graphics g) {
		int x1,x2,y1,y2;
		for(int i=0; i< points.size() ; i++) {
			for(int j = 0; j < points.get(i).size()-1; j++)
			{
				x1 = (int)points.get(i).get(j).getX();
				y1 = (int)points.get(i).get(j).getY();
				x2 = (int)points.get(i).get(j+1).getX();
				y2 = (int)points.get(i).get(j+1).getY();
				g.drawLine(x1, y1, x2, y2);
			}

		}

	}

}
