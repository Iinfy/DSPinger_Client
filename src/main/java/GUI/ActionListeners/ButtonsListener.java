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
                App.mGui.showError("You are not authorized");
            }
            
        }else if(command.equals("TryAuth")){
            if (App.ServerAvailable) {
                if (!App.mGui.getAuthUsername().contains(" ") || App.mGui.getAuthUsername().length()!=0) {
                    App.tryAuth(App.getSocket(), App.mGui.getAuthUsername());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        System.out.println(e1);
                    }
                    App.sendMessage(App.getSocket(), "GETONLINE");
                } else{
                    System.out.println("You entered spaces");
                }
            } else{
                App.mGui.showError("Server is unavailable");
            }
            
            
            
        }else if(command.equals("PING")){
            App.sendMessage(App.getSocket(), "PING " + App.mGui.getPingUsername());
            
        }

    }
    
}
