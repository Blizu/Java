package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MainServer extends Thread {

    private String mainServerIP;
    private int mainServerPort;
    private Map<String, Integer> dictionaryServers = new HashMap <String, Integer > ();
   

    public MainServer(String serverIP, int serverPort) {
        this.mainServerIP = serverIP;
        this.mainServerPort = serverPort;
    }

    public static void main (String[] args){

        //mainServer 50001
        //client 50002
        //dictionaryServer 50003 - n

        MainServer mainServer = new MainServer("127.0.0.1",50001);
        mainServer.registerDictionaryServer(50003);
        mainServer.start();

    }

    public void registerDictionaryServer(int dictionaryServerPort){

        Map < String, Integer > availableDictionaryServer = new HashMap <String, Integer>();
        PrintWriter out;
        BufferedReader in;
        String request = "hello";

        for (int i = dictionaryServerPort; i <= (dictionaryServerPort+1); i++) {

            try {
                System.out.print("Start looking for Dictionary Server...");

                Socket clientSocketForMainServer = new Socket(mainServerIP, i);
                out = new PrintWriter(clientSocketForMainServer.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocketForMainServer.getInputStream()));

                out.println(request);
                String language = in .readLine();

                System.out.println(language + " server found on port: " + i );
                availableDictionaryServer.put(language, i);

            } catch (IOException e) {
                System.out.println("Dictionary Server not found on port: {" + i + "}");
            }
        }
        this.dictionaryServers = availableDictionaryServer;
    }

    public void run() { // punkt wejścia wątku
        try {
            runMainServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runMainServer() throws IOException {
        boolean flag = true;

        ServerSocket serverSocket = new ServerSocket(mainServerPort);
        System.out.println("Main Server is listening on port: " + serverSocket.getLocalPort());

        do{
            Socket clientSocket = serverSocket.accept();

          System.out.println("Client accepted - source port: "+ clientSocket.getPort());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine = in.readLine();

            String[] requestTab = inputLine.split(",");

            if (requestTab.length == 3) {
                String polishWord = requestTab[0];
                String languageCode = requestTab[1];
                String destinationPort = requestTab[2];

                if (dictionaryServers.containsKey(languageCode)) {
                    int LanguageServerPort = dictionaryServers.get(languageCode);

                    Socket translationSocket = new Socket(mainServerIP, LanguageServerPort);
                    PrintWriter translationOut = new PrintWriter(translationSocket.getOutputStream(), true);
                    BufferedReader translationIn = new BufferedReader(new InputStreamReader(translationSocket.getInputStream()));

                    try {

                        String sendRequest = String.join(",", polishWord, destinationPort);
                        System.out.println("Sending request to Dictionary Server: " + sendRequest);

                        translationOut.println(sendRequest);
                        out.println("Got translate request");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        out.println("internal error");
                    }
                    translationSocket.close();
                    translationOut.close();
                    translationIn.close();

                } else {
                    out.println("This language is not supported");
                }
            }
            else if ("show dictionary servers".equals(inputLine)) {
                System.out.println("Get request: " + inputLine);

                String[] languagesTab = new String[dictionaryServers.size()];
              //  int[] portTab = new int [dictionaryServers.size()];

                int i = 0;

                for (String language: dictionaryServers.keySet()) {
                    System.out.println("key "+language +" value "+dictionaryServers.get(language));
                    languagesTab[i++] = language;
                    // portTab[i++] = dictionaryServers.get(language);
                }

                String joinedLanguages = String.join(",", languagesTab);
                System.out.println("Sending " + joinedLanguages +"\n");
                out.println(joinedLanguages);

            } else {
                out.println("Bad request for server");
                System.out.println("Bad request for server");
            }

            in .close();
            out.close();
            clientSocket.close();

        }while(flag);

        System.out.println("Closing Main Server");
        serverSocket.close();

    }
    
    public String getMainServerIP(){
        return mainServerIP;
    }
    public int getMainServerPort(){
        return mainServerPort;
    }
    
}
