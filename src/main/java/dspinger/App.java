package dspinger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import GUI.mainGui;

public class App {
    public static ExecutorService EService = Executors.newFixedThreadPool(4);
    public static Socket socket;
    public static mainGui mGui;
    public static boolean isAuthorized = false;
    public static boolean ServerAvailable;
    public static void main(String[] args) throws IOException {
        try{   
            mGui = new mainGui();
            mGui.start();
            socket = new Socket("127.0.0.1", 8081);
            new Thread(new reader(socket)).start();
            new Thread(new writer(socket)).start();
            ServerAvailable = true;
        
        System.out.println("Successfully connected");
        } catch(Exception e){
            ServerAvailable = false;
            System.out.println("Server is unavailable");
            mGui.showError("Server is unavailable");
        }



    }

    public static void sendMessage(Socket socket, String message){
        if (isAuthorized) {
            EService.execute(new BackMessage(socket, message));
        }
        
    }

    public static void tryAuth(Socket socket, String username){
        if (!isAuthorized) {
            EService.execute(new BackMessage(socket, username));
        }
    }

    public static Socket getSocket(){
        return socket;
    }
}

class writer implements Runnable{
    Socket socket;
    public writer(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String output = scanner.nextLine();
                pw.println(output);
                if (output.equals("exit")) {
                    App.EService.shutdown();
                    break;
                    
                }

            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

class reader implements Runnable{
    Socket socket;
    public reader(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            Scanner scan = new Scanner(socket.getInputStream());
            while(scan.hasNextLine()){
                String str = scan.nextLine();
                System.out.println(str);
                if (str.equals("sound")) {
                    App.EService.execute(new soundplay("src/main/resources/sounds/bell.wav"));
                }
                if (str.contains("OU/:")) {
                    System.out.println(str);
                    str = str.replace("[", "");
                    str = str.replace("]", "");
                    str = str.replace("OU/:","");
                    str = str.replace(" ","");
                    System.out.println(str);
                    App.mGui.showOnlineUsers(str);
                }
                if (str.equals("You have successfully registered")) {
                    App.isAuthorized = true;
                    App.mGui.setStaticAuthorizeField();
                    App.mGui.setAuthorized();
                }
                if (str.equals("User does not exist")) {
                    App.mGui.showError("User does not exist");
                    
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

