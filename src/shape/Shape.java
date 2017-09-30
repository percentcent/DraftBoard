package Shape;

import java.awt.*;
import java.io.PrintWriter;

public interface Shape {
	public abstract void drawShape(Graphics2D g2);
	public abstract void output(PrintWriter writer);

}
