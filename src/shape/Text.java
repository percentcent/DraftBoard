package shape;

import java.awt.*;
import listener.ColorChooser;

/**
 * Created by hasee on 2017/9/15.
 */
public class Text implements Shape {
    private int x1, x2,y1,y2;
    private Color c;
    private String text;
    Font mf = new Font("Serif",Font.BOLD|Font.ITALIC,24);

    public Text(int x1, int x2, int y1, int y2, Color c, String text) {
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.c=c;
        this.text = text;
    }

    @Override
    public void drawShape(Graphics2D g2) {
        g2.setFont(mf);
        g2.setColor(c);
        g2.drawString(text,x2,y2+20);
        g2.setColor(ColorChooser.color);
    }
}
