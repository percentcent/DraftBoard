package server;

import remote.UserList;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by hasee on 2017/9/21.
 */
public class Server {
	static UserList userList;
	static MessageManager msgManager;
    static ShapeManager shapeManager;
	
	public Server() throws RemoteException {
		userList = new IUserList();
		msgManager = new MessageManager(userList);
        shapeManager = new ShapeManager(userList);
	}
	
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
    LocateRegistry.createRegistry(1099);
    Registry registry = LocateRegistry.getRegistry();
    Server server = new Server();
    registry.bind("UserManager", userList);
    registry.bind("MsgManager",msgManager);
    registry.bind("ShapeManager",shapeManager);
    System.out.println("server ready");



}
}
