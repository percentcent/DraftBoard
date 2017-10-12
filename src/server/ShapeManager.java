package server;

import remote.Client;
import remote.ShapeList;
import remote.UserList;
import shape.Shape;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ShapeManager extends UnicastRemoteObject implements ShapeList {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserList clients;
	public List<Shape> shapes  = new ArrayList<Shape>();
	
	public ShapeManager(UserList c) throws RemoteException {
		super();
		clients = c;
	}

	@Override
	public synchronized void add(Shape shape) throws RemoteException {
		shapes.add(shape);
		System.out.println(shapes.size());
		broadcast(shapes);
	}

	@Override
	public void broadcast(List<Shape> shapes) throws RemoteException {
		for(int i = 0; i < clients.size(); i++) {
			Client c = clients.get(i);
			c.draw(shapes);
		}
	}

	@Override
	public List<Shape> getList() throws RemoteException {
		return shapes;
	}

	@Override
	public void clear() throws RemoteException {
		shapes.clear();
	}

	@Override
	public Shape get(int i) throws RemoteException {
		return shapes.get(i);
	}

	@Override
	public int size() throws RemoteException {
		return shapes.size();
	}
}
