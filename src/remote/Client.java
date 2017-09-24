package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by hasee on 2017/9/22.
 */
public interface Client extends Remote {
     public String getName() throws RemoteException;
     public ArrayList<String> getMessage(ArrayList<String> username) throws RemoteException;
}
