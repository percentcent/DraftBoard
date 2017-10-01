package shape;

import listener.ColorChooser;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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
    
    public Text(FileReader reader) throws IOException {
		char[] buf = new char[10];
		reader.read(buf, 0,10);
		this.c = new Color(Integer.parseInt(new String(buf)));
				
		reader.read(buf,0,10);
		this.x1 = Integer.parseInt(new String(buf));
		
		reader.read(buf,0,10);
		this.y1 = Integer.parseInt(new String(buf));
		
		reader.read(buf,0,10);
		this.x2 = Integer.parseInt(new String(buf));
		
		reader.read(buf,0,10);
		this.y2 = Integer.parseInt(new String(buf));
		
		this.text = "";
		char[] newbuf = new char[1];
		while(reader.read(newbuf,0,1)!=-1) {
			String next = text + String.copyValueOf(newbuf);
			this.text = next;
		}
	}
    
    public void output(PrintWriter writer){
		writer.printf("T%010d%010d%010d%010d%010d%s\r\n", c.getRGB(),x1,y1,x2,y2, text);
	}

    @Override
    public void drawShape(Graphics2D g2) {
        g2.setFont(mf);
        g2.setColor(c);
        g2.drawString(text,x2,y2+20);
        g2.setColor(ColorChooser.color);
    }
}
