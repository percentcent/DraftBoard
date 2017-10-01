package listener;

import client.ChatArea;
import client.IClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
public class ListenForBtnSend implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			IClient.msgManager.add(IClient.username + ": " + ChatArea.txtInput.getText());
			ChatArea.txtInput.setText("");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
