package dspinger;

import java.io.PrintWriter;
import java.net.Socket;

public class BackMessage implements Runnable{
    String message;
    Socket socket;
    public BackMessage(Socket socket, String message){
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            pw.println(message);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}