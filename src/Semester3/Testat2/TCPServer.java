package Semester3.Testat2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.*;
import java.util.Random;

//Server auf dem die Daten abgespeichert werden
//Server soll non persistent sein = Nach Übertrag, Verbindung schließen.
public class TCPServer {
    public static int port = 7777;
    public static String pathString = "C:\\Users\\I550616\\Desktop\\Messages\\";
    public static Path path = null;
    public static String rawMessage;
    public static String message;
    public static String command;
    public static File folder = new File(pathString);
    public static String[] folderContent = folder.list();


    public static void main (String[]args) throws Exception {
        System.out.println("Server startet");
        ServerSocket serverSocket = new ServerSocket(TCPServer.port); //Stecker
        while(true){
            //Server vorbereiten
            Socket socket = serverSocket.accept();//Wartet auf Nachrichteneingang
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            //rawMessage auslesen
            rawMessage = br.readLine();
            System.out.println("rawMessage: " + rawMessage);
            //rawMessage aufsplitten und in Array zwischenspeichern
            String[] splittedMessage = rawMessage.split(" ", 2);
            command = splittedMessage [0];
            message = splittedMessage [1];
            // command und message zur probe ausgeben
            System.out.println("command: " + command + ", message: " + message);

            //Verarbeitung der eingehenden Nachricht
            if(command.equals("SAVE")){ //prüfen ob Befehl SAVE
                //Key wird generiert und message in eine Datei abgespeichert
                //Key anlegen
                int key = generateKey(message);

                //Key auf Doppelung überprüfen, falls bereits vorhanden, dann neu generieren;
                while(!keyIsUsed(key)){
                    key = generateKey(message);
                }

                //Datei anlegen
                Path path = Paths.get(pathString + key + ".txt");
                Files.writeString(path, message, StandardCharsets.UTF_8);

                //Key an Client schicken
                OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
                PrintWriter pw = new PrintWriter(osw);
                pw.println(key);
                pw.flush(); //Stream leeren

            }else if(command.equals("GET") && !keyIsUsed(Integer.parseInt(message))){//prüfen ob Befehl GET und Key vorhanden
                //Datei mit erhaltenem Key auslesen und Nachricht senden.
                //Datei auslesen
                path = Paths.get(pathString + message + ".txt");
                String messageFromFile = Files.readString(path);
                System.out.println( message + ".txt says: " + messageFromFile);

                //Nachricht an Client schicken
                OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
                PrintWriter pw = new PrintWriter(osw);
                pw.println("OK " + messageFromFile);
                pw.flush(); // Stream leeren
            }
            else{
                System.out.println("ERROR 404");
                //Nachricht an Client schicken
                OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
                PrintWriter pw = new PrintWriter(osw);
                pw.println("FAILED");
                pw.flush(); // Stream leeren
            }
            System.out.println("\n");
        }
    }

    //Random key wird generiert und pseudo gesalzen
    public static int generateKey(String salt){
        Random random = new Random();
        return  + salt.length() + random.nextInt(10000);
    }

    //prüfen ob key noch frei
    public static boolean keyIsUsed(int key){
        String fileName;
        for(int i=0; i<folderContent.length; i++){
            fileName = folderContent[i];
            if(fileName.contains(Integer.toString(key))){
                System.out.println("Key already exists");
                return false;
            }
        }
        System.out.println("Key doesnt exist");
        return true;
    }

}
