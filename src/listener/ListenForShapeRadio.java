package listener;

import client.ClientManager;
import client.CommandArea;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ListenForShapeRadio implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(CommandArea.rdoCircle.isSelected()) {
			ClientManager.type = ClientManager.CIRCLE;
		}
	}

}
