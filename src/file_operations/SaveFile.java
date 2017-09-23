package file_operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
	
	public void saveAs() {
		
		jFileChooser  = new JFileChooser();
		jFileChooser.setCurrentDirectory(null);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
		jFileChooser.setFileFilter(filter);
		if(jFileChooser.showSaveDialog(null)==jFileChooser.CANCEL_OPTION){
			return;
		}
		
		try {
			writer = new FileWriter(jFileChooser.getSelectedFile());
			printer = new PrintWriter(writer);
			for(Shape s : ClientManager.shapes) {
				s.output(printer);
			}
			printer.close();
			writer.close();
			
			this.currentFile = jFileChooser.getSelectedFile();
			
		} catch(FileNotFoundException e) {
		} catch(IOException e) {
		}
		
	}
	
	public void save() {
		
		try {
			writer = new FileWriter(currentFile);
			printer = new PrintWriter(writer);
			for(Shape s : ClientManager.shapes) {
				s.output(printer);
			}
			printer.close();
			writer.close();
			
		} catch(FileNotFoundException e) {
		} catch(IOException e) {
		}
		
	}

}
