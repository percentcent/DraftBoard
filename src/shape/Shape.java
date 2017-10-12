package shape;

import java.awt.*;
import java.io.PrintWriter;
import java.io.Serializable;

public interface Shape extends Serializable {
	public abstract void drawShape(Graphics2D g2);
	public abstract void output(PrintWriter writer);

}
