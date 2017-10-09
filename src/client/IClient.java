package client;


import remote.Client;
import remote.MessageList;
import remote.UserList;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by hasee on 2017/9/24.
 */
public class IClient extends UnicastRemoteObject implements Client {
	public static String username;
    public static ClientManager clientManager = new ClientManager();

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static int userId;
    public static MessageList msgManager;
    public static UserList userManager;
    public static boolean isManager = false;
    
    public IClient(MessageList msgM, UserList userM) throws RemoteException, NotBoundException {
    		msgManager = msgM;
        userManager = userM;
    }

    public void setUsername(String s) throws RemoteException {
    		username = s;
    }
    
    @Override
	public void receiveMsg(List<String> msgs) throws RemoteException{
		((ChatArea) clientManager.chatArea).setMsgList(msgs);
	}


    public void initialMsgLst(List<String> lst) {
    		((ChatArea) clientManager.chatArea).setMsgList(lst);
    }

    @Override
    public void receiveUserList(List<String> userList) throws RemoteException {
        ((ChatArea) clientManager.chatArea).setUserList(userList);
    }

    public void initialUserLst(List<String> userList) {
        ((ChatArea) clientManager.chatArea).setUserList(userList);
    }

    @Override
    public int permit() throws RemoteException {
        int res = JOptionPane.showConfirmDialog(clientManager, "A new user has requested to join. ", "New User Request", JOptionPane.YES_NO_OPTION);
        System.out.println(res);
        return res;
    }
    @Override
    public void reject() throws RemoteException {
        JOptionPane.showMessageDialog(clientManager, "Your request to join has been rejected by the Manager. ", "Failure to Join", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    @Override
    public void kickedOut() throws RemoteException {
        JOptionPane.showMessageDialog(clientManager, "You have been kicked by the Manager.", "Kicked ", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    @Override
    public void getUserId(int a) throws RemoteException {
        JOptionPane.showMessageDialog(clientManager, "Your userid is " + a, "UserId. ", JOptionPane.YES_NO_OPTION);
        setUserId(a);
    }
    
    @Override
    public void managerLeaving() throws RemoteException {
    	JOptionPane.showMessageDialog(clientManager, "Manager has left, now exiting.", "Exiting ", JOptionPane.ERROR_MESSAGE);
    	System.exit(0);
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost");
        MessageList msgManager = (MessageList)registry.lookup("MsgManager");
        UserList userManager = (UserList)registry.lookup("UserManager");
        Client client = new IClient(msgManager, userManager);
        userManager.addClient(client,"han2");
        if(userId == 0)
        {
            System.out.println("you are manager");
            isManager=true;
            clientManager.becomeManager();
        }
        client.initialMsgLst(msgManager.getList());
        client.initialUserLst(userManager.getList());
        clientManager.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	while(isManager == true){
                	try {
						userManager.closeBoard();
						System.out.println("CLOSING BOARD!");
						if(userManager.size() == 1)
                        {
                            userManager.removeClient(client);
                            System.out.println("try");
                            break;
                        }
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
                }
                if(isManager == false)
                    {
                        try {
                            userManager.removeClient(client);
                            System.out.println("try");
                        } catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                    }



                System.out.println("Exit when Closed event");
                System.exit(0);
            }
        });
    }
}
