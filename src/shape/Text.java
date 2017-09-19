package shape;

import java.awt.*;

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
    public void drawShape(Graphics g) {
        g.setFont(mf);
        g.setColor(c);
        g.drawString(text,x2,y2+20);
    }
}
