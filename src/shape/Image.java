package shape;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class Image implements Shape {
	
	private BufferedImage image;
	private String filePath;
	
	public Image(BufferedImage image) {
		this.image=image;
	}
	
	public Image(FileReader reader) throws IOException {
		char[] buf = new char[100];
		reader.read(buf, 0,100);
		File file = new File(new String(buf));
		this.image = ImageIO.read(file);
	}

	@Override
	public void drawShape(Graphics2D g2) {
		g2.drawImage(image, 0, 0, null);
		
	}

	@Override
	public void output(PrintWriter writer) {
		writer.printf("I%s\r\n", filePath);
		
	}

}
