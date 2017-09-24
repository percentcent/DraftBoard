package server;

import remote.IUserList;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by hasee on 2017/9/21.
 */
public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
    LocateRegistry.createRegistry(1099);
    IUserList iUserList = new IUserList();
    Registry registry = LocateRegistry.getRegistry();
    registry.bind("userOperation",iUserList);
    System.out.println("server ready");



}
}
