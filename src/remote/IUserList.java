package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by hasee on 2017/9/21.
 */
public class IUserList extends UnicastRemoteObject implements UserList {
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<String> username = new ArrayList<>();
    public IUserList() throws RemoteException {
    }


    @Override
    public void addClient(Client c, String msg) throws RemoteException {
        if(!clients.contains(c))
        {
            clients.add(c);
            username.add(msg);
            for(int i = 0;i<clients.size();i++)
            {
                sendMessageto(clients.get(i),username);
            }
        }
    }

    @Override
    public void removeClient(Client c, String msg) throws RemoteException {

    }

    @Override
    public void sendMessageto(Client c, ArrayList<String> username) throws RemoteException {
            c.getMessage(username);
    }




}
