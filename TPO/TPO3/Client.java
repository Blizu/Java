package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private String serverIP;
    private int clientPort;
    private int destinationPort;

    public Client(String IP,int sourcePort,int serverPort){
        this.serverIP=IP;
        this.clientPort=sourcePort;
        this.destinationPort=serverPort;
    }

    public static void main (String[] args){

        Client client = new Client("127.0.0.1",50002,50001);

        for (int i=0; i<20; i++){
            displayAnswer(client.getAvailableDictionaryServer());
        }


        //miejsce na GUI

    }

    public int getClientPort(){
        return this.clientPort;
    }
    public int getDestinationPort(){
        return this.destinationPort;
    }

    public String[] getAvailableDictionaryServer() {
        try {
                Socket clientSocket = new Socket(serverIP, destinationPort);
                String request = "show dictionary servers";

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                out.println(request);
                System.out.println("Sending: " + request + " on port " + destinationPort);

                String result = in.readLine();

                String[] tableOfAvailableLanguages = result.split(",");

                clientSocket.close();
                in.close();
                out.close();

                if ("Bad Request.".equals(result)) {
                    throw new IOException("Bad Request.");
                } else if ("Internal Error.".equals(result)) {
                    throw new IOException("Internal Error.");
                } else {
                    return tableOfAvailableLanguages;
                }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void displayAnswer(String[] tab){

        for (int i=0; i<tab.length; i++){
            System.out.println(tab[i]);
        }
    }



}
