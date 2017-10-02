package file_operations;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import client.ClientManager;
import shape.Shape;

public class SaveFile {
	
	JFileChooser jFileChooser;
	FileWriter writer;
	PrintWriter printer;
	
	public File currentFile;
	public String fileName;
	
	public void saveAs() throws IOException {
		
		FileNameExtensionFilter txt = new FileNameExtensionFilter("TXT", "txt");
		FileNameExtensionFilter png = new FileNameExtensionFilter("PNG", "png");
		FileNameExtensionFilter jpg = new FileNameExtensionFilter("JPEG", "jpg", "jpeg");
		
		jFileChooser  = new JFileChooser();
		jFileChooser.setCurrentDirectory(null);
		jFileChooser.addChoosableFileFilter(jpg);
		jFileChooser.addChoosableFileFilter(png);
		jFileChooser.addChoosableFileFilter(txt);
		if(jFileChooser.showSaveDialog(null)==jFileChooser.CANCEL_OPTION){
			return;
		}
		
		if(jFileChooser.getFileFilter().equals(txt)) {
			txtSave(jFileChooser);
		}
		else if(jFileChooser.getFileFilter().equals(jpg)) {
			jpgSave(jFileChooser);
		}
		else if(jFileChooser.getFileFilter().equals(png)) {
			pngSave(jFileChooser);
		}
		
		else if(jFileChooser.isAcceptAllFileFilterUsed()) {
			String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
			
			if(filePath.endsWith(".png")) {
				pngSave(jFileChooser);
			}
			else if(filePath.endsWith(".jpg")) {
				jpgSave(jFileChooser);
			}
			else if(filePath.endsWith(".txt")) {
				txtSave(jFileChooser);
			}
			else {
				txtSave(jFileChooser);
			}
		}

		this.currentFile = jFileChooser.getSelectedFile();
		
	}
	
	public void save() {
		
		String filePath = currentFile.getAbsolutePath();
		jFileChooser.setSelectedFile(currentFile);
		
		if(filePath.endsWith(".jpg")) {
			jpgSave(jFileChooser);
		}
		else if(filePath.endsWith(".txt")) {
			try {
				txtSave(jFileChooser);
			} catch (IOException e) {
			}
		}
		else if(filePath.endsWith(".png")) {
			pngSave(jFileChooser);
		}
		
	}
	
	private void jpgSave(JFileChooser jFileChooser) {
		String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
		if(!filePath.endsWith(".jpg")) {
			jFileChooser.setSelectedFile(new File(filePath + ".jpg"));
		}
		
		BufferedImage image = new BufferedImage(ClientManager.displayArea.getWidth(), 
				ClientManager.displayArea.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D  = image.createGraphics();
		ClientManager.displayArea.paint(graphics2D);
		try {
			ImageIO.write(image, "jpeg", jFileChooser.getSelectedFile());
		} catch (IOException e) {
		}
	}
	
	private void txtSave(JFileChooser jFileChooser) throws IOException {
		String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
		if(!filePath.endsWith(".txt")) {
			jFileChooser.setSelectedFile(new File(filePath + ".txt"));
		}
		
		writer = new FileWriter(jFileChooser.getSelectedFile());
		printer = new PrintWriter(writer);
		for(Shape s : ClientManager.shapes) {
			s.output(printer);
		}
		printer.close();
		writer.close();
	}
	
	private void pngSave(JFileChooser jFileChooser) {
		String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
		if(!filePath.endsWith(".png")) {
			jFileChooser.setSelectedFile(new File(filePath + ".png"));
		}

		BufferedImage image = new BufferedImage(ClientManager.displayArea.getWidth(), 
				ClientManager.displayArea.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D  = image.createGraphics();
		ClientManager.displayArea.paint(graphics2D);
		try {
			ImageIO.write(image, "png", jFileChooser.getSelectedFile());
		} catch (IOException e) {
		}
	}

}
