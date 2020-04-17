package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client extends Thread { //Klient jest zarówno serwerem jak i klientem

    private String serverIP;
    private int clientListeningPort;
    private int destinationPort;


    public Client(String IP,int clientPort ,int serverPort){
        this.serverIP=IP;
        this.clientListeningPort = clientPort;
        this.destinationPort=serverPort;
    }

    public static void main (String[] args){

        Client client = new Client("127.0.0.1",50002,50001);


        for (int i=0; i<2; i++){
            displayAnswer(client.getAvailableDictionaryServer());
        }


        try {
            String translatedWord = client.translateWord("kwarantanna","FR",client.clientListeningPort);
            System.out.println("Tłumaczenie: "+translatedWord);

        } catch (TranslationException e) {
            e.printStackTrace();
        }


        //miejsce na GUI

    }

    public int getClientPort(){
        return this.clientListeningPort;
    }

    public int getDestinationPort(){
        return this.destinationPort;
    }

    public void setClientListeningPort(int port){
        this.clientListeningPort = port;
    }



    public String[] getAvailableDictionaryServer() {
        try {

                Socket clientSocket = new Socket(serverIP, destinationPort);
                String requestForServer = "show dictionary servers";

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                out.println(requestForServer);
                System.out.println("Sending: " +"{"+requestForServer + "}"+" from port " + clientSocket.getLocalPort() +" to port "+destinationPort);

                String result = in.readLine();
                String[] tableOfAvailableLanguages = result.split(",");

                clientSocket.close();
                in.close();
                out.close();

                if ("Bad requestForServer".equals(result)) {
                    throw new IOException("Sent wrong requestForServer");
                } else if ("Internal Error.".equals(result)) {
                    throw new IOException("Internal Error ");
                } else {

                    return tableOfAvailableLanguages;
                }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void displayAnswer(String[] tab){
        System.out.print("Answer from server: ");
        for (int i=0; i<tab.length; i++){
            System.out.print(tab[i]+",");
        }
        System.out.println(" ");
    }

    public String translateWord(String word, String languageCode, int clientListeningPort) throws TranslationException {
        String translatedWord = "";
            try{

            ServerSocket serverSocket = new ServerSocket(clientListeningPort);
            System.out.println("Client is listening on port: " + serverSocket.getLocalPort());

            Socket clientSocket = new Socket(serverIP, destinationPort);

            BufferedReader inClientSocket = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outClientSocket = new PrintWriter(clientSocket.getOutputStream(), true);

            String request = word + "," + languageCode + "," + clientListeningPort;
            System.out.println("Sending request: " + request);

            outClientSocket.println(request);
            String result = inClientSocket .readLine();

            if ("Got translate request".equals(result)) { //przetwarzanie odpowiedzi

                Socket resultSocket = serverSocket.accept();
                BufferedReader resultIn = new BufferedReader(new InputStreamReader(resultSocket.getInputStream()));
                PrintWriter resultOut = new PrintWriter(resultSocket.getOutputStream(), true);

                translatedWord = resultIn.readLine();

                resultSocket.close();
                resultIn.close();
                resultOut.close();

                if("Translation Not Found".equals(translatedWord)) {
                    throw new TranslationException("Translation Not Found for: " + word);
                }
            }
            else {
                System.out.println("ERROR: " + result);
            }

            serverSocket.close();
            clientSocket.close();
            inClientSocket .close();
            outClientSocket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return translatedWord;
    }

}
