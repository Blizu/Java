package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class DictionaryServer extends Thread {

    private Map<String, String> dictionary = new HashMap<String, String>();
    private String language;
    private String serverIP;
    private int serverListeningPort;

    public DictionaryServer(String language, String serverIP, int serverPort, Map<String, String> dictionary) {
        this.language = language;
        this.serverIP = serverIP;
        this.serverListeningPort = serverPort;
        this.dictionary = dictionary;
    }

    public static void main(String[] args) {

        Map<String, String> englishDictionary = new HashMap<String, String>();
        englishDictionary.put("poczta","post");
        englishDictionary.put("policja","police");
        englishDictionary.put("kwarantanna", "quarantine");
        englishDictionary.put("kubek", "mug");
        englishDictionary.put("książka", "book");

        DictionaryServer englishServer = new DictionaryServer("ENG", "127.0.0.1", 50003, englishDictionary);

        Map < String, String > frenchDictionary = new HashMap < String, String > ();
        frenchDictionary.put("poczta","poste");
        frenchDictionary.put("policja", "police");
        frenchDictionary.put("kwarantanna","quarantaine");
        frenchDictionary.put("kubek","gobelet");
        frenchDictionary.put("książka", "livre");

        DictionaryServer frenchServer = new DictionaryServer("FR", "127.0.0.1", 50004, frenchDictionary);

        englishServer.start();
        frenchServer.start();

    }

    public void run() {
        try {
            runDictionaryServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runDictionaryServer() throws IOException {
        boolean flag = true;
        ServerSocket dictionaryServerSocket = new ServerSocket(serverListeningPort);

        System.out.println("Dictionary server (" + this.language + ") is waiting for requests on port: " + this.serverListeningPort);

        do {

            //Komunikacja z MainServerem

            Socket clientSocket = dictionaryServerSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine = in .readLine();
            String[] requesTab = inputLine.split(",");


            if ("hello".equals(inputLine)) {
                System.out.println("Got " + inputLine +  " request from Main Server on port" + clientSocket.getPort());
                out.println(this.language);
            }

            // Obługa komunikacji między klientem, a serwerem słownikowym

            else if (requesTab.length==2) {
                String polishWord = requesTab[0];
                int destinationPort = Integer.parseInt(requesTab[1]);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }

                System.out.println(destinationPort);

                Socket languageSocket = new Socket(serverIP, destinationPort);
                PrintWriter languageOut = new PrintWriter(languageSocket.getOutputStream(), true);

                if (dictionary.containsKey(polishWord)) {
                    String translatedWord = dictionary.get(polishWord);

                    System.out.println("Translating:{" + polishWord + "} to: {" + translatedWord);

                    languageOut.println(translatedWord);
                }
                else {
                    System.out.println("Translation not found for: " + polishWord);

                    languageOut.println("translation not found");
                }
                languageOut.close();
                languageSocket.close();
            }
            else {
                out.println("Bad Request.");
            }

        }while (flag) ;

            System.out.println("Closing Dictionary Server");
            dictionaryServerSocket.close();
        }


    }

