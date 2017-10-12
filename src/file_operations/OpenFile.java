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
					ClientManager.shapesList.add(new Line(reader));
					ClientManager.shapes.add(new Line(reader));
					break;
				case 'C':
					ClientManager.shapesList.add(new Circle(reader));
					ClientManager.shapes.add(new Circle(reader));
					break;
				case 'E':
					ClientManager.shapesList.add(new Eraser(reader));
					ClientManager.shapes.add(new Eraser(reader));
					break;
				case 'F':
					ClientManager.shapesList.add(new Freehand(reader));
					ClientManager.shapes.add(new Freehand(reader));
					break;
				case 'O':
					ClientManager.shapesList.add(new Oval(reader));
					ClientManager.shapes.add(new Oval(reader));
					break;
				case 'R':
					ClientManager.shapesList.add(new Rectangle(reader));
					ClientManager.shapes.add(new Rectangle(reader));
					break;
				case 'T':
					ClientManager.shapesList.add(new Text(reader));
					ClientManager.shapes.add(new Text(reader));
					break;
				case 'I':
					ClientManager.shapesList.add(new Image(reader));
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
