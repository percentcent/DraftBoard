package shape;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import javax.imageio.ImageIO;

public class Image implements Shape {
	
	private BufferedImage image;
	private String filePath;
	
	public Image(BufferedImage image,String filePath) {
		this.image=image;
		this.filePath = filePath;
	}
	
	public Image(BufferedReader reader) throws IOException {
		String buf = reader.readLine();
		System.out.println(buf);
		File file = new File(new String(buf));
		this.image = ImageIO.read(file);
		//this.filePath=String.valueOf(buf);
		this.filePath=buf;
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
