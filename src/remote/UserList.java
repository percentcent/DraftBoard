package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface UserList extends Remote {

    public void addClient(Client c,String name) throws RemoteException;
    public void removeClient(Client c) throws RemoteException;
    public Client get(int i) throws RemoteException;
    public int size() throws RemoteException;
    public void broadcast() throws RemoteException;
    public List<String> getList() throws RemoteException;
    public int manageAdd() throws RemoteException;
    public void kickOutClient(Client c) throws RemoteException;
    public void sendUserId(int a,Client c) throws RemoteException;
    public void closeBoard() throws RemoteException;
}