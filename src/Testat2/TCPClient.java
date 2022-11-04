package Testat2;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static String ip = "localhost";
    public static int port = 7777;
    public static String receivedMessage;

    public static void main  (String[]args) throws Exception {
        int counter = 0;
        System.out.println("Client startet");
        //Client sendet und frägt 10 Nachrichten an
        while(counter < 10){
            sendMessage("SAVE Hallo Batman!");
            //sendMessage("GET " + 8308);
            counter++;
        }
    }

    public static void sendMessage(String message) throws Exception{
        //senden
        Socket socket = new Socket(ip, port);//Verbindung öffnen
        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
        PrintWriter pw = new PrintWriter(osw);
        pw.println(message);
        pw.flush();//Stream leeren

        //empfangen
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        receivedMessage = br.readLine();
        System.out.println(receivedMessage);
        Thread.sleep(1000);
        socket.close();//Verbindung schließen
    }
}
