package zad1;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Client extends Application { //Klient jest zarówno serwerem jak i klientem

    private String serverIP;
    private int clientListeningPort;
    private int destinationPort;

    public Client(){
        //clientListeningPort=50002;

    }

    public Client(String IP,int clientPort ,int serverPort){
        this.serverIP=IP;
        this.clientListeningPort = clientPort;
        this.destinationPort=serverPort;
    }

    public static void main (String[] args){
        //Client client = new Client("127.0.0.1",50002,50001);

       /* for (int i=0; i<2; i++){
            displayAnswer(client.getAvailableDictionaryServer());
        }

        try {
            String translatedWord = client.translateWord("kwarantanna","FR",client.clientListeningPort);
            System.out.println("Tłumaczenie: "+translatedWord);

        } catch (TranslationException e) {
            e.printStackTrace();
        }*/

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Client client1 = new Client("127.0.0.1",50002,50001);

        VBox vbox = new VBox();


        // Wybieranie serwera

        Label label1 = new Label("Wybierz język docelowy:");

        ComboBox availableDictionaryServersComboBox = new ComboBox();


        availableDictionaryServersComboBox.setPrefSize(800, 150);


        try {
            availableDictionaryServersComboBox.getItems().clear();
            String[] serversTab = client1.getAvailableDictionaryServer();

            for(String i : serversTab) {
                availableDictionaryServersComboBox.getItems().add(i);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        // Wpisywanie słowa

        Label label2 = new Label("Słowo do przetłumaczenia:");

        TextField inputText = new TextField();
        inputText.setPrefSize(800, 100);



        // Przycisk rozpoczynający tłumaczenie

        Button button = new Button("Tłumacz!");
        button.setPrefSize(800,100);


        // Efekt tłumaczenia

        Label label3 = new Label("Tłumaczenie:");

        TextField outputText = new TextField();
        outputText.setPrefSize(800,100);

        outputText.setEditable(false);

        // Dodanie elementów do VBOX

        vbox.getChildren().add(label1);
        vbox.getChildren().add(availableDictionaryServersComboBox);
        vbox.getChildren().add(label2);
        vbox.getChildren().add(inputText);
        vbox.getChildren().add(button);
        vbox.getChildren().add(label3);
        vbox.getChildren().add(outputText);

        // Scena i primaryStage

        Scene scene = new Scene(vbox, 600, 800);

        primaryStage.setTitle("Aplikacja TPO3");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        button.setOnAction((event) ->{

            String selectedServer = (String) availableDictionaryServersComboBox.getValue();

            if (selectedServer.equals("") || selectedServer == null){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Nie wybrano języka!.");
                alert.showAndWait();
                return;
            }

            String polishWord = inputText.getText();

            if ("".equals(polishWord) || polishWord.contains(" ") || polishWord == null){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Pole nie może być puste i nie może zawierać spacji.");
                alert.showAndWait();

            }

            try {
                String resultWord = translateWord(polishWord,selectedServer,client1);
                inputText.setText("");
                outputText.setText(resultWord);

            }catch(TranslationException e) {
                inputText.setText("");
                outputText.setText("");

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Brak tłumaczenia: " + polishWord + " dla: " + selectedServer);
                alert.showAndWait();

            }catch(Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Wystąpił błąd podczas przetwarzania");
                alert.showAndWait();
                System.out.println(ex);
            }
        });
    }



    public int getClientPort(){
        return this.clientListeningPort;
    }

    public int getDestinationPort(){
        return this.destinationPort;
    }
    public String getServerIP(){
        return this.serverIP;
    }

    public void setClientListeningPort(int port){
        this.clientListeningPort = port;
    }


    public String[] getAvailableDictionaryServer() {
        try {

                Socket clientSocket = new Socket(serverIP, destinationPort);
                String requestForServer = "show dictionary servers";

                BufferedReader clientSocketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter clientSocketOut = new PrintWriter(clientSocket.getOutputStream(), true);

                clientSocketOut.println(requestForServer);
                System.out.println("Sending: " +requestForServer +" from port " + clientSocket.getLocalPort() +" to port "+destinationPort);

                String result = clientSocketIn.readLine();
                String[] tableOfAvailableLanguages = result.split(",");

                clientSocket.close();
                clientSocketIn.close();
                clientSocketOut.close();

                if ("Bad request for server".equals(result)) {
                    throw new IOException("Sent wrong requestForServer");
                } else if ("internal error".equals(result)) {
                    throw new IOException("Internal Error");
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

    public String translateWord(String word, String languageCode, Client client) throws TranslationException {

        String serverIP=getServerIP();
        int clientListeningPort = client.getClientPort();
        int destinationPort = client.getDestinationPort();

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

                if("translation not found".equals(translatedWord)) {
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

