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
		if(CommandArea.rdoOval.isSelected()) {
			ClientManager.type = ClientManager.OVAL;
		}
		if(CommandArea.rdoLine.isSelected()) {
			ClientManager.type = ClientManager.LINE;
		}
		if(CommandArea.rdoRect.isSelected()) {
			ClientManager.type = ClientManager.RECT;
		}
		if(CommandArea.rdoFree.isSelected()) {
			ClientManager.type = ClientManager.FREE;
		}
		if(CommandArea.rdoText.isSelected()) {
			ClientManager.type = ClientManager.TEXT;
		}
	}

}
