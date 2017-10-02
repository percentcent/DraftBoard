package client;


import remote.Client;
import remote.MessageList;
import remote.UserList;

import javax.swing.*;
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
    
    public static MessageList msgManager;
    public static UserList userManager;
    
    
    
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
        int res = JOptionPane.showConfirmDialog(clientManager, "A new user want join ", "new user", JOptionPane.YES_NO_OPTION);
        System.out.println(res);
        return res;
    }
    @Override
    public void reject() throws RemoteException {
        JOptionPane.showMessageDialog(clientManager, "rejected by manager ", "fail to join ", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost");
        MessageList msgManager = (MessageList)registry.lookup("MsgManager");
        UserList userManager = (UserList)registry.lookup("UserManager");
        Client client = new IClient(msgManager, userManager);
        userManager.addClient(client,"han1");
        client.initialMsgLst(msgManager.getList());
        client.initialUserLst(userManager.getList());
    }
}
