package file_operations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import shape.Circle;
import shape.Eraser;
import shape.Freehand;
import shape.Line;
import shape.Oval;
import shape.Rectangle;
import shape.Text;
import shape.Image;

import client.ClientManager;

public class OpenFile {
	
	private JFileChooser jFileChooser;
	private FileNameExtensionFilter filter;
	private FileReader reader;
	
	public OpenFile(SaveFile saveInstance) {
		FileNameExtensionFilter txt = new FileNameExtensionFilter("TXT", "txt");
		FileNameExtensionFilter jpg = new FileNameExtensionFilter("JPEG", "jpg");
		FileNameExtensionFilter png = new FileNameExtensionFilter("PNG", "png");
		
		jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(null);
		jFileChooser.addChoosableFileFilter(txt);
		jFileChooser.addChoosableFileFilter(png);
		jFileChooser.addChoosableFileFilter(jpg);
		jFileChooser.showOpenDialog(null);
		
		ClientManager.shapes.clear();
		ClientManager.displayArea.removeAll();
		
		String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
		if(filePath.endsWith(".txt")) {
			txtOpen(jFileChooser);
		}
		else if(filePath.endsWith(".jpg")) {
			jpgOpen(jFileChooser);
		} else if(filePath.endsWith(".png")) {
			pngOpen(jFileChooser);
		}
		
		JOptionPane.showMessageDialog(null, "Successfully Opened!", "Message", 1);	
		saveInstance.currentFile = jFileChooser.getSelectedFile();
			
	}
	
	private void txtOpen(JFileChooser jFileChooser) {
		try {
			reader = new FileReader(jFileChooser.getSelectedFile());
			while(reader.ready()) {
				switch(reader.read()) {
				case 'L':
					ClientManager.shapes.add(new Line(reader));
					break;
				case 'C':
					ClientManager.shapes.add(new Circle(reader));
					break;
				case 'E':
					ClientManager.shapes.add(new Eraser(reader));
					break;
				case 'F':
					ClientManager.shapes.add(new Freehand(reader));
					break;
				case 'O':
					ClientManager.shapes.add(new Oval(reader));
					break;
				case 'R':
					ClientManager.shapes.add(new Rectangle(reader));
					break;
				case 'T':
					ClientManager.shapes.add(new Text(reader));
					break;
				case 'I':
					ClientManager.shapes.add(new Image(reader));
					break;
				}
			}
			reader.close();
		} catch(FileNotFoundException e) {
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void jpgOpen(JFileChooser jFileChooser) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(jFileChooser.getSelectedFile());
		} catch (IOException e) {
		}
		Graphics g = ClientManager.displayArea.getGraphics();
		ClientManager.shapes.add(new Image(image));
//		g.drawImage(image, 0,0, null);
	}
	
	private void pngOpen(JFileChooser jFileChooser) {
		
	}

}
