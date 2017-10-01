package client;


import remote.Client;
import remote.MessageList;
import remote.UserList;

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

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost");
        MessageList msgManager = (MessageList)registry.lookup("MsgManager");
        UserList userManager = (UserList)registry.lookup("UserManager");
        Client client = new IClient(msgManager, userManager);
        userManager.addClient(client,"han1");
        client.initialMsgLst(msgManager.getList());
    }
}
