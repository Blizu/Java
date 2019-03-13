import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.out;

public class KomendyGniazda extends Thread {
    public int nrHosta;
    public String adresIp;
    public int port;
    public String katalog;
    GniazdoSerwer gniazdoSerwer;
    Konsola konsola;

    public KomendyGniazda(int nrHosta, String adresIp, int port, String katalog) throws IOException {
        this.nrHosta=nrHosta;
        this.adresIp=adresIp;
        this.port=port;
        this.katalog=katalog;
    }

    @Override
    public void run() {
        try {

            while (true) {
                String linia = null;
                Socket gniazdo = gniazdoSerwer.gniazdoSerwera.accept();
                System.out.println("!!--odbiór danych na porcie: " + gniazdo.getPort() + "--!!"); //wyświetla informacje o nadejściu danych
                BufferedReader daneWejsciowe = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));

                //czytaj, dopóki linia nie jest pusta
                while ((linia = daneWejsciowe.readLine()) != null) {
                    if (linia.equals("list")) {

                        konsola.PokazListePlikow(nrHosta, adresIp, port, katalog, gniazdo);
                        break;
                    } else if (linia.startsWith("pull") && linia.contains(" ")) {
                        konsola.PobierzPlik(katalog, gniazdo, linia.split(" ")[1]);
                        break;

                    } else if (linia.startsWith("push") && linia.contains(" ")) {
                        gniazdo.close();
                        Socket pobierajaceGniazdo = gniazdoSerwer.gniazdoSerwera.accept();
                        konsola.WyslijPlik(katalog, pobierajaceGniazdo, linia.split(" ")[1]);
                        if (pobierajaceGniazdo.isClosed() == false) {
                            pobierajaceGniazdo.close();
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
    }
    }

