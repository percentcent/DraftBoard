package client;

import java.awt.*;
import java.awt.List;
import java.util.ArrayList;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.border.TitledBorder;

import com.sun.xml.internal.ws.api.message.Message;

import javax.swing.*;
import listener.ListenForBtnSend;

public class ChatArea extends JPanel{
	JScrollPane spnlMessage;
	JList lstMessages;
	public static JTextArea txtInput;
	
	public ChatArea() {
		this.setBorder(new TitledBorder("Chat Room"));
		Dimension d = new Dimension(250, 620);
        this.setPreferredSize(d);
        this.setLayout(new FlowLayout());
      	
      	JList lstUsernames = new JList();
      	lstUsernames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane spnlUserList = new JScrollPane(lstUsernames);
        spnlUserList.setPreferredSize(new Dimension(230, 120));
        spnlUserList.setBorder(new TitledBorder("User List"));
        
        lstMessages = new JList();
        lstMessages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        spnlMessage = new JScrollPane(lstMessages);
        spnlMessage.setPreferredSize(new Dimension(230, 300));
        spnlMessage.setBorder(new TitledBorder("Message List"));
        spnlMessage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        JPanel pnlSendMsg = new JPanel(new FlowLayout());
        pnlSendMsg.setPreferredSize(new Dimension(230, 155));
        pnlSendMsg.setBorder(new TitledBorder("Send Message"));
        
        txtInput = new JTextArea();
        txtInput.setColumns(17);
        txtInput.setRows(5);
        
        JScrollPane spnlInput = new JScrollPane(txtInput);
        spnlInput.setViewportView(txtInput);
        spnlInput.setPreferredSize(new Dimension(210, 90));
        JButton bntSendMsg = new JButton();
        bntSendMsg.setText("Send");
        bntSendMsg.setPreferredSize(new Dimension(220, 25));
        bntSendMsg.addActionListener(new ListenForBtnSend());
        pnlSendMsg.add(spnlInput);
        pnlSendMsg.add(bntSendMsg);
        
        this.add(spnlUserList);
        this.add(spnlMessage);
        this.add(pnlSendMsg);
	}
	
	public void setMsgList(java.util.List<String> msgs) {
		lstMessages.setListData(msgs.toArray());
	}
}
