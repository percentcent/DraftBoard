package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import remote.Client;
import remote.UserList;

/**
 * Created by hasee on 2017/9/21.
 */
public class IUserList extends UnicastRemoteObject implements UserList {
    private ArrayList<Client> clients;
    private ArrayList<String> username;
    
    public IUserList() throws RemoteException {
    		clients = new ArrayList<>();
    		username = new ArrayList<>();
    }


    @Override
    public void addClient(Client c, String name) throws RemoteException {
        if(!clients.contains(c))
        {
            clients.add(c);
            c.setUsername(name);
            username.add(name);
        }
    }

    @Override
    public void removeClient(Client c, String msg) throws RemoteException {

    }

	@Override
	public synchronized Client get(int i) throws RemoteException{
		// TODO Auto-generated method stub
		if(i < clients.size())
			return clients.get(i);
		return null;
	}


	@Override
	public int size() throws RemoteException{
		// TODO Auto-generated method stub
		return clients.size();
	}




}
