package listener;

import client.IClient;
import client.ChatArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import remote.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import server.IUserList;

import javax.swing.*;
/**
 * Created by hasee on 2017/10/4.
 */
public class UserListListener implements ActionListener{
	@Override
    public void actionPerformed(ActionEvent e) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int IsSelected = ChatArea.lstUsernames.getSelectedValues().length;
				if (IsSelected > 0) {
		            
					if(JOptionPane.showConfirmDialog(null, "Do you want to kick out this user?","Kick Out",
		        			JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
					{
		        		int num = ChatArea.lstUsernames.getSelectedIndex();
		        		if(num!=0) {
		        			try {
		     
		        				IClient.userManager.kickOutClient(num);
		        			
		        			}catch(RemoteException e1)
		        			{
//		        				JOptionPane msg = new JOptionPane("There is something wrong with server. Please try again later.", JOptionPane.WARNING_MESSAGE);
//		    			        final JDialog dlg = msg.createDialog("Exiting");
//		    			        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//		    			        dlg.setVisible(true);
//
//		    			        System.exit(0);
		        				System.out.println("Kick Out Problem");
		        				e1.printStackTrace();
		        			}
		        		}
		        		else {
		        			JOptionPane.showMessageDialog(null, "It is yourself(manager).");
		        		}
		        		
					}
		            
		        } else {
		            JOptionPane.showMessageDialog(null, "please select one user");
		        }
			}
		}).start();
		
	}
}