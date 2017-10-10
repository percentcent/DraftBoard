package client;

import javax.swing.*;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by hasee on 2017/10/10.
 */
public class Waiting extends JFrame{
    Dialog dialog=new Dialog((Dialog) null,"waiting",false);
    public Waiting()
    {
        dialog.setBounds(300,80,250,200);
        dialog.setVisible(true);
        dialog.setResizable(false);
        dialog.add(new Label("waiting manager permit"));
    }
    public void close()
    {
        dialog.setVisible(false);
    }
    public static void main(String[] args) throws RemoteException, NotBoundException {
      Waiting waiting = new Waiting();

    }


}
