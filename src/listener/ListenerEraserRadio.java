package listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import client.ClientManager;
import client.CommandArea;;

public class ListenerEraserRadio implements ItemListener {
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(CommandArea.rdoEraser.isSelected()) {
			ClientManager.type = ClientManager.ERASER;
		}
	}

}
