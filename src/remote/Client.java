package remote;

import shape.Image;
import shape.Shape;

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
	public int permit(String username) throws RemoteException;
	public void reject() throws RemoteException;
	public void kickedOut() throws RemoteException;
	public void getUserId(int a) throws RemoteException;
	public void managerLeaving() throws RemoteException;
	public void setActive() throws RemoteException;
	public void draw(List<Shape> shapes) throws RemoteException;
	public void update(Image image) throws RemoteException;
	public void becomeManager() throws RemoteException;
	public static void removeAll() throws RemoteException {

	}
}

