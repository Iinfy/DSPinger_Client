package GUI;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import GUI.ActionListeners.ButtonsListener;


public class mainGui {
        JFrame Frame = new JFrame("Pinger client");
        JPanel mainPanel = new JPanel();
        JPanel usersOnlinePanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(usersOnlinePanel);
        
        JLabel wordUsersonline = new JLabel("Users online");
        JPanel underUseronlineword = new JPanel();
        JButton refreshOnline = new JButton("Refresh");

        JTextField nicknameAuthField = new JTextField();
        JButton authButton = new JButton(">");
        JPanel authStatusPanel = new JPanel();
        JLabel authStatusLabel = new JLabel("Not authorized");
        
        
        
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
        mainPanel.add(nicknameAuthField);
        mainPanel.add(authButton);
        mainPanel.add(authStatusPanel);
          


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

        authStatusPanel.setBounds(200,0,200,30);
        authStatusPanel.setBackground(Color.red);
        authStatusPanel.setLayout(null);
        authStatusPanel.add(authStatusLabel);
        authStatusLabel.setBounds(5,0,100,30);
        nicknameAuthField.setBounds(200,30,150,30);
        authButton.setBounds(350,30,50,30);
        authButton.addActionListener(bList);
        authButton.setActionCommand("TryAuth");
        

        

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

    public String getAuthUsername(){
        return nicknameAuthField.getText();
    }

    public void setStaticAuthorizeField(){
        nicknameAuthField.setEditable(false);
    }

    public void setAuthorized(){
        authStatusPanel.setBackground(Color.green);
        authStatusLabel.setBounds(5,0,100,30);
        authStatusLabel.setText("Authorized");
        
    }


}
