package remote;

import client.ClientManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by hasee on 2017/9/24.
 */
public class IClient extends UnicastRemoteObject implements Client {
    ClientManager clientManager = new ClientManager();
    public IClient() throws RemoteException {

    }

    @Override
    public String getName() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<String> getMessage(ArrayList<String> username) throws RemoteException {
        System.out.println(username.size());
        clientManager.setUser(username);
        for(int i = 0; i < clientManager.getUser().size();i++)
        {
            System.out.println(clientManager.getUser().get(i));
        }

        return username;

    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost");
        UserList userList = (UserList)registry.lookup("userOperation") ;
        Client client = new IClient();
        userList.addClient(client,"han1");
    }
}
