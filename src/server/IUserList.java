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
    private static ArrayList<Client> clients;
    private ArrayList<String> username;
    private int currentId = 1;
    
    public IUserList() throws RemoteException {
    		clients = new ArrayList<>();
    		username = new ArrayList<>();
    }


    @Override
    public synchronized void addClient(Client c, String name) throws RemoteException {
        if(!clients.contains(c)&&clients.size() ==0)
        {
            clients.add(c);
            c.setUsername(name);
            c.becomeManager();
            username.add(name +"(0: manager)");
            sendUserId(0,c);
            broadcast();
        }

        else if(!clients.contains(c))
        {
            if(manageAdd(name) == 0)
                {
                    clients.add(c);
                    c.setUsername(name);
                    //int id =clients.indexOf(c);
                    username.add(name + "(" + currentId + ")");
                    c.setActive();
                    sendUserId(currentId,c);
                    broadcast();
                    currentId = currentId + 1;
                    System.out.println(currentId);
                }
            else
                {
                    c.reject();
                }
        }
    }

    @Override
    public void removeClient(Client c) throws RemoteException {
        if(clients.contains(c))
        {
            username.remove(clients.indexOf(c));
            clients.remove(c);
            broadcast();
        }

    }

    @Override
    public void kickOutClient(int id) throws RemoteException {
        if(id<clients.size())
        {
        		Client c=clients.get(id);
        		removeClient(c);
        		c.kickedOut();
        }

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
    public int manageAdd(String name) throws RemoteException{
        Client c = clients.get(0);
        return c.permit(name);
    }
    @Override
    public void sendUserId(int a,Client c) throws RemoteException
    {
    		new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						c.getUserId(a);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
    			
    		}).start();
    }
    
    @Override
    public void closeBoard() throws RemoteException {
		for(int i=1; i<clients.size(); i++) {
			Client c = get(i);
			removeClient(c);
			c.managerLeaving();
		}

    }

    @Override
    public void setActive(Client c) throws RemoteException {
        c.setActive();
    }

}
