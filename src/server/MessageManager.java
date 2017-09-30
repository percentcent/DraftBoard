package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import remote.*;

public class MessageManager extends UnicastRemoteObject implements MessageList{
	UserList clients;
	List<String> messages = new ArrayList<String>();
	
	public MessageManager(UserList c) throws RemoteException {
		clients = c;
	}

	@Override
	public void add(String message) throws RemoteException{
		// TODO Auto-generated method stub
		messages.add(message);
		broadcast();
	}

	@Override
	public void broadcast() throws RemoteException{
		// TODO Auto-generated method stub
		for(int i = 0; i < clients.size(); i++) {
			Client c = clients.get(i);
			c.receiveMsg(messages);
		}
	}
	
	@Override
	public List<String> getList() {
		return messages;
	}

}
