package listener;

import client.ClientManager;
import file_operations.OpenFile;
import file_operations.SaveFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener {
	
	private boolean haveSaved;
	private SaveFile saveInstance;
	
	public MenuListener(){
		this.haveSaved = false;
		this.saveInstance = new SaveFile();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Save")) {
			if(haveSaved==false) {
				saveInstance.saveAs();
			} else {
				saveInstance.save();
			}
			if(saveInstance.currentFile!=null){
				haveSaved=true;
			}
			
		} else if(e.getActionCommand().equals("Open")) {
			OpenFile open = new OpenFile(saveInstance);
			haveSaved=true;
			ClientManager.displayArea.repaint();
		}
		if(e.getActionCommand().equals("New")) {
			haveSaved=false;
			ClientManager.shapes.clear();
			System.out.println(ClientManager.shapes.size());
			ClientManager.displayArea.repaint();

		}
		else if(e.getActionCommand().equals("Save As")) {
			saveInstance.saveAs();
			if(saveInstance.currentFile!=null){
				haveSaved=true;
			}
		} else if(e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
		
	}
}
