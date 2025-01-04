package GUI;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.ActionListeners.ButtonsListener;
import dspinger.App;
import dspinger.BackMessage;

public class mainGui {
        JFrame Frame = new JFrame("Pinger client");
        JPanel mainPanel = new JPanel();
        JPanel usersOnlinePanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(usersOnlinePanel);
        
        JLabel wordUsersonline = new JLabel("Users online");
        JPanel underUseronlineword = new JPanel();
        JButton refreshOnline = new JButton("Refresh");
        
        
        
        String usersOnline;

        ButtonsListener bList = new ButtonsListener();


    public void start(){
        Frame.setSize(400,400);
        Frame.setVisible(true);
        Frame.setResizable(false);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.add(mainPanel);

        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.add(scrollPane);
        mainPanel.add(underUseronlineword);
          


        scrollPane.setBounds(0,30,200,200);
        usersOnlinePanel.setLayout(new BoxLayout(usersOnlinePanel, BoxLayout.Y_AXIS));

        underUseronlineword.setBounds(0,0,200,30);
        underUseronlineword.setBackground(Color.white);
        underUseronlineword.setLayout(null);
        underUseronlineword.add(wordUsersonline);  
        underUseronlineword.add(refreshOnline);
        wordUsersonline.setBounds(2,0,100,30);
        wordUsersonline.setForeground(Color.black);


        refreshOnline.setBounds(75,0,125,30);
        refreshOnline.addActionListener(bList);
        refreshOnline.setActionCommand("Refresh online");
        

        

    }

    public void showOnlineUsers(String users){
        usersOnlinePanel.removeAll();
        String[] usersOnline = users.split(",");
        System.out.println(usersOnline.toString());
        for(String user : usersOnline){
            JLabel label = new JLabel(" " + user);
            usersOnlinePanel.add(label);
            usersOnlinePanel.revalidate();
        }
    }


}
