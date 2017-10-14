package listener;

import client.IClient;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.rmi.RemoteException;

import javax.swing.*;
/**
 * Created by hasee on 2017/10/4.
 */
public class UserListListener implements ListSelectionListener {
    //@Override
    public void valueChanged(ListSelectionEvent e) {
    	if(IClient.isManager) {
    	ListSelectionModel users = (ListSelectionModel)e.getSource();
    if(!e.getValueIsAdjusting()) {
        System.out.printf("LeadSelectionIndex is %s%n",users.getLeadSelectionIndex());
        if(JOptionPane.showConfirmDialog(null, "Do you want to kick out this user?","Kick Out",
        			JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
        		int num = users.getLeadSelectionIndex();
        		try {
        			IClient.userManager.kickOutClient(num);
        		}catch(RemoteException e1)
        		{
        			e1.printStackTrace();
        		}
        		//users.clearSelection();
        }else {
        		
        		//users.clearSelection();
        }
        
        }
    	}
    	else 
    		System.out.printf("no access");

    }
}
