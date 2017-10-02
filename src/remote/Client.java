package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by hasee on 2017/9/22.
 */
public interface Client extends Remote {
	public void setUsername(String s) throws RemoteException;
	public void receiveMsg(List<String> msgs) throws RemoteException;
	public void initialMsgLst(List<String> list) throws RemoteException;
	public void receiveUserList(List<String> userList) throws RemoteException;
	public void initialUserLst(List<String> list) throws RemoteException;
	public int permit() throws RemoteException;
	public void reject() throws RemoteException;

}
