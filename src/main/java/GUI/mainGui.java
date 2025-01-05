package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import GUI.ActionListeners.ButtonsListener;
import dspinger.App;
import dspinger.soundplay;


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

        JTextField usernameToPingField = new JTextField();
        JButton pingButton = new JButton("Ping");

        String[] usersOnline;

        ButtonsListener bList = new ButtonsListener();

        JLabel versionLabel = new JLabel("Release v1.1");


    public void start(){
        Frame.setSize(400,270);
        Frame.setVisible(true);
        Frame.setResizable(false);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.add(mainPanel);
        Frame.setLocationRelativeTo(null);

        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.add(scrollPane);
        mainPanel.add(underUseronlineword);
        mainPanel.add(nicknameAuthField);
        mainPanel.add(authButton);
        mainPanel.add(authStatusPanel);
        mainPanel.add(usernameToPingField);
        mainPanel.add(pingButton);
        mainPanel.add(versionLabel);
          


        scrollPane.setBounds(0,30,200,200);
        usersOnlinePanel.setLayout(new BoxLayout(usersOnlinePanel, BoxLayout.Y_AXIS));

        underUseronlineword.setBounds(0,0,200,30);
        underUseronlineword.setBackground(Color.BLACK);
        underUseronlineword.setLayout(null);
        underUseronlineword.add(wordUsersonline);  
        underUseronlineword.add(refreshOnline);
        wordUsersonline.setBounds(1,0,100,30);
        wordUsersonline.setForeground(Color.WHITE);


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

        usernameToPingField.setBounds(200,100,200,50);
        usernameToPingField.setFont(new Font("Arial",Font.BOLD,24));
        pingButton.setBounds(200,150,185,50);
        pingButton.setBackground(Color.GRAY);
        pingButton.setForeground(Color.white);
        pingButton.setBorder(null);
        pingButton.addActionListener(bList);
        pingButton.setActionCommand("PING");

        versionLabel.setBounds(310,212,100,20);
        versionLabel.setForeground(Color.white);

        

        

    }

    public void showOnlineUsers(String users){
        usersOnlinePanel.removeAll();
        usersOnline = users.split(",");
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

    public String getPingUsername(){
        return usernameToPingField.getText();
    }

    public void showError(String errorText){
        JDialog d = new JDialog(Frame, "Error");
            App.EService.execute(new soundplay("src/main/resources/sounds/error.wav"));
 
            // create a label
            JLabel l = new JLabel(" " + errorText);
 
            d.add(l);
 
            // setsize of dialog
            d.setSize(200, 100);
            d.setResizable(false);
            d.setLocationRelativeTo(null);
            // set visibility of dialog
            d.setVisible(true);
    }

}
