package dspinger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static ExecutorService EService = Executors.newFixedThreadPool(4);
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("212.192.248.73", 8081);
        new Thread(new reader(socket)).start();
        new Thread(new writer(socket)).start();
        System.out.println("Successfully connected");



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
                    App.EService.execute(new soundplay("src/main/java/dspinger/sounds/bell.wav"));
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}