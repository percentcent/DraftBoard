package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MessageList extends Remote{
	public void add(String message) throws RemoteException;
	public void broadcast() throws RemoteException;
	public List<String> getList() throws RemoteException;
}
