package shape;

import client.ClientManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Image implements Shape,Serializable {

	public BufferedImage getImage() {
		return image;
	}

	private transient BufferedImage image;
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
		g2.drawImage(image, 0, 0, ClientManager.getDisplayArea());
		
	}

	@Override
	public void output(PrintWriter writer) {
		writer.printf("I%s\r\n", filePath);
		
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		ImageIO.write(image, "png", out); // png is lossless

	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		image = ImageIO.read(in);

	}


}
