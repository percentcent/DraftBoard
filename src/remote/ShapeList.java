package remote;

import shape.Shape;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ShapeList extends Remote {
	//public static List<Shape> shapes = new ArrayList<Shape>();
	public void add(Shape shape) throws RemoteException;
	public void broadcast(List<Shape> shapes) throws RemoteException;
	public List<Shape> getList() throws RemoteException;
	public void clear() throws RemoteException;
	public Shape get(int i) throws RemoteException;
	public int size() throws RemoteException;
}
