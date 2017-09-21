package shape;

import java.awt.*;
import listener.ColorChooser;
import listener.WidthPanel;

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
    
    @Override
    public void drawShape(Graphics2D g2) {
        g2.setColor(c);
        g2.setStroke(new BasicStroke(width));
        g2.drawLine(x1, y1, x2, y2);
        g2.setColor(ColorChooser.color);
      	g2.setStroke(new BasicStroke(WidthPanel.width));
    }

}
