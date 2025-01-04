package GUI.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dspinger.App;

public class ButtonsListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Refresh online")) {
            if (App.isAuthorized) {
                App.sendMessage(App.getSocket(), "GETONLINE");
            }else{
                System.out.println("You are not authorized!");
            }
            
        }
    }
    
}
