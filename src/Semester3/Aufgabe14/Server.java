package Semester3.Aufgabe14;

import java.net.*;
import java.nio.file.Paths;

public class Server {//UDP Server

    public static void main (String[] args){
        try{
            DatagramSocket serverSocket = new DatagramSocket(5999);
            System.out.println("Server startet");
            byte[] data = new byte[100]; //Warum byte[] bei UDP?
            DatagramPacket serverPacket = new DatagramPacket(data, data.length); //Länge in Byte
            Paths path = 

            while(true){
                serverSocket.receive(serverPacket);// Wenn keine Nachricht da, macht er nicht weiter
                String rawMessage = new String (serverPacket.getData(), 0, serverPacket.getLength());
                String[] splittedString = rawMessage.split(" ");
                String command = splittedString[0];
                String fileName = splittedString[1];
                String lineNumber = splittedString[2];

                if(command.equals("READ")){

                }

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
