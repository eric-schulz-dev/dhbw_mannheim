package Semester3.Aufgabe13;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPClient {
    static String message  = "message";


    public static void main (String []args) throws Exception { // throws = Exception handling Alternative zu try/catch
        InetAddress iAdress = InetAddress.getByName("localhost");
        DatagramSocket clientSocket = new DatagramSocket();
        DatagramPacket clientPacket = new DatagramPacket(message.getBytes(StandardCharsets.UTF_8), message.getBytes(StandardCharsets.UTF_8).length, iAdress, 4999);
        clientSocket.send(clientPacket);
        System.out.println("send message");

    }
}
