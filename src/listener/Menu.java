package listener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JMenu file;
	
	JMenuItem newFile;
	JMenuItem openFile;
	JMenuItem saveFile;
	JMenuItem saveasFile;
	JMenuItem exit;
	
	public Menu() {
		file = new JMenu("File");
		newFile = new JMenuItem("New");
		openFile = new JMenuItem("Open");
		saveFile = new JMenuItem("Save");
		saveasFile = new JMenuItem("Save As");
		exit = new JMenuItem("Exit");
		
		add(file);
		file.add(newFile);
		file.add(openFile);
		file.add(saveFile);
		file.add(saveasFile);
		file.add(exit);
		
		MenuListener menuListener = new MenuListener();
		newFile.addActionListener(menuListener);
		openFile.addActionListener(menuListener);
		saveFile.addActionListener(menuListener);
		saveasFile.addActionListener(menuListener);
		exit.addActionListener(menuListener);
		
	}

}
