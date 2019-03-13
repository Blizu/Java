import java.io.*;
import java.net.Socket;


import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        /*
            1 argument - tryb programu
            2 argument - nazwa hosta
            3 argument - IP
            4 argument - PORT
        */
        String trybProgramu = args[0];
        String nazwaHosta = args[1];
        String adresIP = args[2];
        int port = Integer.parseInt(args[3]);
        String katalog = "";
        int [] tablicaHostów = {1,2,3};
        String lokalnaSciezka = "C:\\TORrent_";
        int nrHosta=0;


        File host1 = new File(lokalnaSciezka+tablicaHostów[0]);
        File host2= new File (lokalnaSciezka+tablicaHostów[1]);
        File host3= new File(lokalnaSciezka+tablicaHostów[2]);


        if (args[1].equals("Host1")) {
            katalog = host1.getPath();
            nrHosta = tablicaHostów[0];
        }
        else if (args[1].equals("Host2")) {
            katalog = host2.getPath();
            nrHosta = tablicaHostów[1];
        }
        else if (args[1].equals("Host3")) {
            katalog = host3.getPath();
            nrHosta = tablicaHostów[2];
        }
        else {
            System.out.println("Nie przewidzano takiego hosta");
        }

        sprawdzajArgumenty(args);
        //Porty prywatne (49152 do 65535)

        if (args[0].equals("H2H")) {
            System.out.println("Program uruchomiony w trybie H2H");

            Thread watekPlik = new Pliki(katalog);
            watekPlik.start();

            H2HStart(nrHosta, adresIP, port, katalog);
        } else {
            bladArgumentow();
        }
    }
//---------------- Koniec funkcji main

    public static void H2HStart(int nrHosta, String adresIp, int Port, String katalog) {
           Thread watekGniazdoSerwer = new GniazdoSerwer(nrHosta, adresIp, Port, katalog);
           watekGniazdoSerwer.start();

      /*  Thread watekKomendyGniazda = new KomendyGniazda(nrHosta, adresIp, Port, katalog);
        watekKomendyGniazda.start();*/

            Thread watekKonsola= new Konsola();
            watekKonsola.start();

            new Thread(() ->
            {
                try {
                    while (true) {
                        String linia = null;
                        Socket gniazdo = ((GniazdoSerwer) watekGniazdoSerwer).gniazdoSerwera.accept();
                        System.out.println("!!--odbiór danych na porcie: " + gniazdo.getPort() + "--!!"); //wyświetla informacje o nadejściu danych
                        BufferedReader daneWejsciowe = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));

                        //czytaj, dopóki linia nie jest pusta
                        while ((linia = daneWejsciowe.readLine()) != null) {
                            if (linia.equals("list")) {

                                ((Konsola)watekKonsola).PokazListePlikow(nrHosta, adresIp, Port, katalog, gniazdo);
                                break;
                            } else if (linia.startsWith("pull") && linia.contains(" ")) {
                                ((Konsola) watekKonsola).PobierzPlik(katalog, gniazdo, linia.split(" ")[1]);
                                break;

                            } else if (linia.startsWith("push") && linia.contains(" ")) {
                                gniazdo.close();
                                Socket downloadSocket = ((GniazdoSerwer) watekGniazdoSerwer).gniazdoSerwera.accept();
                                ((Konsola)watekKonsola).WyslijPlik(katalog, downloadSocket, linia.split(" ")[1]);
                                if (downloadSocket.isClosed() == false) {
                                    downloadSocket.close();
                                }
                                break;

                            } else {
                                out.println("!!--Nieznana komenda--!!\"" + linia + "\"");
                            }
                        }
                        if (gniazdo.isClosed() == false) {
                            gniazdo.close();
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }).start();
        }

    public static void wywietlArgumenty(String[] args) {
        for (int i = 0; i < args.length; i++) {
            out.println("Argument " + i + " " + args[i]);
        }
    }
    public static void sprawdzajArgumenty(String[] args){
        if (args.length < 3) {
            bladArgumentow();
        } else {
            wywietlArgumenty(args);
        }
    }

    public static void bladArgumentow() {
        Runnable r = () -> {
            System.out.println("Podano złą liczbę argumentów.");
            System.exit(0);
        };
        r.run();
    }
}







