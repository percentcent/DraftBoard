package client;


import remote.Client;
import remote.MessageList;
import remote.ShapeList;
import remote.UserList;
import shape.Image;
import shape.Shape;

import javax.swing.*;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by hasee on 2017/9/24.
 */
public class IClient extends UnicastRemoteObject implements Client {
	public static String username;
    public static ClientManager clientManager;
    public static Waiting waiting = new Waiting();


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static int userId;


    public void setMsgManager(MessageList msgManager) {
        this.msgManager = msgManager;
    }

    public static MessageList msgManager;
    public static UserList userManager;
    public static ShapeList shapeList;
    public static boolean isManager = false;
    
    public IClient(MessageList msgM, UserList userM,ShapeList shapeM) throws RemoteException, NotBoundException {
    		msgManager = msgM;
            userManager = userM;
            shapeList = shapeM;
            clientManager = new ClientManager(shapeM);
    }

    public void setUsername(String s) throws RemoteException {
    		username = s;
    }
    
    @Override
	public void receiveMsg(List<String> msgs) throws RemoteException{
		((ChatArea) clientManager.chatArea).setMsgList(msgs);
	}


    public void initialMsgLst(List<String> lst) {
    		((ChatArea) clientManager.chatArea).setMsgList(lst);
    }

    @Override
    public void receiveUserList(List<String> userList) throws RemoteException {
    	//for(String user:userList)
    	//	System.out.println(user);
        ((ChatArea) clientManager.chatArea).setUserList(userList);
    }

    public void initialUserLst(List<String> userList) {
        ((ChatArea) clientManager.chatArea).setUserList(userList);
    }

    @Override
    public int permit(String username) throws RemoteException {
        int res = JOptionPane.showConfirmDialog(clientManager, username + " has requested to join. ", "New User Request", JOptionPane.YES_NO_OPTION);
        System.out.println(res);
        return res;
    }
    @Override
    public void reject() throws RemoteException {
        JOptionPane.showMessageDialog(clientManager, "Your request to join has been rejected by the Manager. ", "Failure to Join", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    @Override
    public void kickedOut() throws RemoteException {
        JOptionPane.showMessageDialog(clientManager, "You have been kicked by the Manager.", "Kicked ", JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    }

    @Override
    public void getUserId(int a) throws RemoteException {
        JOptionPane.showMessageDialog(clientManager, "Your userid is " + a, "UserId. ", JOptionPane.YES_NO_OPTION);
        setUserId(a);
    }
    
    @Override
    public void managerLeaving() throws RemoteException {
        JOptionPane msg = new JOptionPane("Manager has left, now exiting", JOptionPane.WARNING_MESSAGE);
        final JDialog dlg = msg.createDialog("Exiting");
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dlg.setVisible(false);
            }
        }).start();
        dlg.setAlwaysOnTop(true);
        dlg.setVisible(true);

        System.exit(0);
    }

    @Override
    public void setActive() throws RemoteException {
        clientManager.setVisible(true);
    }

    @Override
    public void draw(List<Shape> shapes) throws RemoteException {
        //clientManager.getDisplayArea().setShapes(shapes);
        clientManager.getDisplayArea().paintRmiShape(shapes);
        clientManager.getDisplayArea().repaint();
    }

    @Override
    public void update(Image image) throws RemoteException {
       //clientManager.setDisplayArea(drawPan);
       //clientManager.getDisplayArea().drawImage(image);
        System.out.println("Called");
    }


    public static void main(String[] args) {
    		ClientCmdLineArgs argsBean = new ClientCmdLineArgs();
		CmdLineParser parser = new CmdLineParser(argsBean);
		String host;
		String user;
		try {
			parser.parseArgument(args);
			host = argsBean.getHost();
			user = argsBean.getUser();		
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			parser.printUsage(System.err);
			return;
		}
		
        Registry registry;
		try {
			registry = LocateRegistry.getRegistry(host);
		
        MessageList msgManager = (MessageList)registry.lookup("MsgManager");
        UserList userManager = (UserList)registry.lookup("UserManager");
        ShapeList shapeManager = (ShapeList)registry.lookup("ShapeManager");
        Client client = new IClient(msgManager, userManager,shapeManager);
        clientManager.setVisible(false);
        userManager.addClient(client,user);
        if(userId == 0)
        {
            System.out.println("you are manager");
            isManager=true;
            waiting.close();
            client.setActive();
            clientManager.becomeManager();
        }
        else
        {
            waiting.close();
            client.setActive();
        }
//        String ip = InetAddress.getLocalHost().getHostAddress();
//        System.out.println(ip);
        client.initialMsgLst(msgManager.getList());
        client.initialUserLst(userManager.getList());
        client.draw(shapeManager.getList());
        clientManager.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	while(isManager == true){
                	try {
						userManager.closeBoard();
						System.out.println("CLOSING BOARD!");
						if(userManager.size() == 1)
                        {
                            userManager.removeClient(client);
                            System.out.println("try");
                            break;
                        }
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
                }
                if(isManager == false)
                    {
                        try {
                            userManager.removeClient(client);
                            System.out.println("try");
                        } catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                    }



                System.out.println("Exit when Closed event");
                System.exit(0);
            }
        });
		

        clientManager.menu.exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while(isManager == true){
                    try {
                        userManager.closeBoard();
                        System.out.println("CLOSING BOARD!");
                        if(userManager.size() == 1)
                        {
                            userManager.removeClient(client);
                            System.out.println("try");
                            break;
                        }
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
                System.out.println("Exit when Closed event");
                System.exit(0);
            }
        });
		} catch (RemoteException | NotBoundException e2) {
			// TODO Auto-generated catch block
			JOptionPane msg = new JOptionPane("There is something wrong with server. Please try again later.", JOptionPane.WARNING_MESSAGE);
	        final JDialog dlg = msg.createDialog("Exiting");
	        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	        dlg.setVisible(true);

	        System.exit(0);
		}
    }
}
