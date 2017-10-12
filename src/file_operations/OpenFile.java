package file_operations;

import client.ClientManager;
import shape.*;
import shape.Image;
import shape.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;

public class OpenFile {
	
	private JFileChooser jFileChooser;
	private FileNameExtensionFilter filter;
	private FileReader filereader;
	private String filePath;
	
	public OpenFile(SaveFile saveInstance) throws RemoteException {
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
		ClientManager.shapesList.clear();
		ClientManager.displayArea.removeAll();
		
		filePath = jFileChooser.getSelectedFile().getAbsolutePath();
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
			filereader = new FileReader(jFileChooser.getSelectedFile());
			BufferedReader reader = new BufferedReader(filereader);
			while(reader.ready()) {
				switch(reader.read()) {
				case 'L':
					Line line = new Line(reader);
					ClientManager.shapesList.add(line);
					ClientManager.shapes.add(line);
					break;
				case 'C':
					Circle circle = new Circle(reader);
					ClientManager.shapesList.add(circle);
					ClientManager.shapes.add(circle);
					break;
				case 'E':
					Eraser eraser = new Eraser(reader);
					ClientManager.shapesList.add(eraser);
					ClientManager.shapes.add(eraser);
					break;
				case 'F':
					Freehand freehand = new Freehand(reader);
					ClientManager.shapesList.add(freehand);
					ClientManager.shapes.add(freehand);
					break;
				case 'O':
					Oval oval = new Oval(reader);
					ClientManager.shapesList.add(oval);
					ClientManager.shapes.add(oval);
					break;
				case 'R':
					Rectangle rectangle = new Rectangle(reader);
					ClientManager.shapesList.add(rectangle);
					ClientManager.shapes.add(rectangle);
					break;
				case 'T':
					Text text = new Text(reader);
					ClientManager.shapesList.add(text);
					ClientManager.shapes.add(text);
					break;
				case 'I':
					Image image = new Image(reader);
					ClientManager.shapesList.add(image);
					ClientManager.shapes.add(image);
					break;
				}
			}
			reader.close();
		} catch(FileNotFoundException e) {
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void jpgOpen(JFileChooser jFileChooser) throws RemoteException {
		BufferedImage image = null;
		try {
			image = ImageIO.read(jFileChooser.getSelectedFile());
		} catch (IOException e) {
		}
		Graphics g = ClientManager.displayArea.getGraphics();
		ClientManager.shapesList.add(new Image(image,filePath));
		ClientManager.shapes.add(new Image(image,filePath));
	}
	
	private void pngOpen(JFileChooser jFileChooser) throws RemoteException {
		BufferedImage image = null;
		try {
			image = ImageIO.read(jFileChooser.getSelectedFile());
		} catch (IOException e) {
		}
		Graphics g = ClientManager.displayArea.getGraphics();
		ClientManager.shapesList.add(new Image(image,filePath));
		ClientManager.shapes.add(new Image(image,filePath));
	}

}
