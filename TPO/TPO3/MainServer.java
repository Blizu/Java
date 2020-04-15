package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MainServer {

    private String mainServerIP;
    private int mainServerPort;
    private Map<String, Integer> dictionaryServer = new HashMap<String,Integer>();

    public MainServer(String serverIP, int serverPort) {
        this.mainServerIP = serverIP;
        this.mainServerPort = serverPort;
    }

    public static void main (String[] args){

        //mainServer 50001
        //client 50002
        //dictionaryServer 50003 - n

        MainServer mainServer = new MainServer("127.0.0.1",50001);
        mainServer.run();

    }

    public void run() {
        try {
            runMainServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runMainServer() throws IOException {
        boolean flag = true;

        ServerSocket serverSocket = new ServerSocket(mainServerPort);
        System.out.println("Server is listening on port: " + mainServerPort);

        do{
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client accepted");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine = in.readLine();
          //  String[] query = inputLine.split(",");

            if("show dictionary servers".equals(inputLine)) {
                System.out.println("Get request: " + inputLine);
                out.println("no servers available");
            }

            in .close();
            out.close();
            clientSocket.close();

        }while(flag);

        System.out.println("Closing Server");
        serverSocket.close();

    }

    public static void setDictionaryServer(){

    }

    public String getMainServerIP(){
        return mainServerIP;
    }
    public int getMainServerPort(){
        return mainServerPort;
    }



}
