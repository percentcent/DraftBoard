package server;

import remote.Client;
import remote.UserList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
        if(!clients.contains(c)&&clients.size() ==0)
        {
            clients.add(c);
            c.setUsername(name);
            username.add(name +"(manager)");
            broadcast();
        }

        else if(!clients.contains(c))
        {
            if(manageAdd() == 0)
                {
                    clients.add(c);
                    c.setUsername(name);
                    username.add(name);
                    broadcast();
                }
            else
                {
                    c.reject();
                }
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

    @Override
    public void broadcast() throws RemoteException{
        // TODO Auto-generated method stub
        for(int i = 0; i < clients.size(); i++) {
            Client c = clients.get(i);
            c.receiveUserList(username);
        }
    }

    @Override
    public List<String> getList() throws RemoteException {
        return username;
    }

    @Override
    public int manageAdd() throws RemoteException{
        Client c = clients.get(0);
        return c.permit();
    }

}
