package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.UserList;

/**
 * Created by hasee on 2017/9/21.
 */
public class Server {
	static UserList userList;
	static MessageManager msgManager;
	
	public Server() throws RemoteException {
		userList = new IUserList();
		msgManager = new MessageManager(userList);
	}
	
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
    LocateRegistry.createRegistry(1099);
    Registry registry = LocateRegistry.getRegistry();
    Server server = new Server();
    registry.bind("UserManager", userList);
    registry.bind("MsgManager",msgManager);
    System.out.println("server ready");



}
}
