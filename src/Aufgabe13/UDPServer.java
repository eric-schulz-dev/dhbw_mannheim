package Aufgabe13;

import java.awt.desktop.ScreenSleepEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class UDPServer {

    public static void main (String[]args){
        String message = "";
        try {
            DatagramSocket serverSocket = new DatagramSocket(4999);
            System.out.println("Server läuft");
            byte[] pufferArray = new byte[100];
            DatagramPacket serverPacket = new DatagramPacket(pufferArray, pufferArray.length); //länge in Bytee
            while (true){
                serverSocket.receive(serverPacket);// Wenn keine Nachricht da, macht er nicht weiter
                message = new String(serverPacket.getData(), 0, serverPacket.getLength());
                if(!message.equals("") ){
                    System.out.println("got message " +  message);//Korrekt nach Pagnia?
                }else{
                    System.out.println("tick");
                }
                Thread.sleep(6000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
