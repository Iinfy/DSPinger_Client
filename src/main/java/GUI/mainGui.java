package GUI;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class mainGui {
        JFrame Frame = new JFrame("Pinger client");
        JPanel mainPanel = new JPanel();
        JPanel usersOnlinePanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(usersOnlinePanel);

        JLabel wordUsersonline = new JLabel("Users online");
        JPanel underUseronlineword = new JPanel();
        
        
        
        String usersOnline;


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
          


        scrollPane.setBounds(0,30,150,200);
        usersOnlinePanel.setLayout(new BoxLayout(usersOnlinePanel, BoxLayout.Y_AXIS));

        underUseronlineword.setBounds(0,0,150,30);
        underUseronlineword.setBackground(Color.white);
        underUseronlineword.setLayout(null);
        underUseronlineword.add(wordUsersonline);  
        wordUsersonline.setBounds(2,0,100,30);
        wordUsersonline.setForeground(Color.black);
        

        

    }

    public void showOnlineUsers(String users){
        String[] usersOnline = users.split(",");
        for(String user : usersOnline){
            JLabel label = new JLabel(user);
            usersOnlinePanel.add(label);
        }
    }


    public static void main(String[] args) {
        mainGui mg = new mainGui();
        mg.start();


    }
}
