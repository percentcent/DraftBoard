package file_operations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

import client.ClientManager;

public class OpenFile {
	
	private JFileChooser jFileChooser;
	private FileNameExtensionFilter filter;
	private FileReader reader;
	
	public OpenFile(SaveFile saveInstance) {
		jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(null);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
		jFileChooser.setFileFilter(filter);
		jFileChooser.showOpenDialog(null);
		
		ClientManager.shapes.clear();
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
				}
			}
			reader.close();
			JOptionPane.showMessageDialog(null, "Successfully Opened!", "Message", 1);
			
			saveInstance.currentFile = jFileChooser.getSelectedFile();
			
		} catch(FileNotFoundException e) {
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
