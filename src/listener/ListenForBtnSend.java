package listener;

import client.ChatArea;
import client.IClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ListenForBtnSend implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					IClient.msgManager.add(IClient.username +"("+IClient.userId +")"+": " + ChatArea.txtInput.getText());
					ChatArea.txtInput.setText("");
				} catch (RemoteException e1) {
					JOptionPane msg = new JOptionPane("There is something wrong with server. Please try again later.", JOptionPane.WARNING_MESSAGE);
			        final JDialog dlg = msg.createDialog("Exiting");
			        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			        dlg.setVisible(true);

			        System.exit(0);
				}
			}
		}).start();

	}

}
