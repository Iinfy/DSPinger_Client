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
    public static void main(String[] args) throws IOException {
        
        socket = new Socket("127.0.0.1", 8081);
        new Thread(new reader(socket)).start();
        new Thread(new writer(socket)).start();
        mGui = new mainGui();
        mGui.start();
        System.out.println("Successfully connected");
        



    }

    public static void sendMessage(Socket socket, String message){
        if (isAuthorized) {
            EService.execute(new BackMessage(socket, message));
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
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

