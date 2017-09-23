package file_operations;

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
	
	public SaveFile() {
		
		jFileChooser  = new JFileChooser();
		jFileChooser.setCurrentDirectory(null);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
		jFileChooser.setFileFilter(filter);
		jFileChooser.showSaveDialog(null);
		try {
			writer = new FileWriter(jFileChooser.getSelectedFile());
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
