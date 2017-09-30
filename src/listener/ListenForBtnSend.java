package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import client.ChatArea;
import client.IClient;
public class ListenForBtnSend implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			IClient.msgManager.add(IClient.username + ": " + ChatArea.txtInput.getText());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
