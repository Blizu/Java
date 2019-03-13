
import java.io.IOException;
import java.net.*;




public class GniazdoSerwer extends Thread {

    public int nrHosta;
    public String adresIp;
    public int port;
    public String katalog;
    public Konsola konsola;
    public ServerSocket gniazdoSerwera;


    public GniazdoSerwer(int nrHosta, String adresIp, int port, String katalog){
        this.nrHosta=nrHosta;
        this.adresIp=adresIp;
        this.port=port;
        this.katalog=katalog;
    }
    public GniazdoSerwer(){}

    @Override
    public void run() {

        try {
            gniazdoSerwera = new ServerSocket(port);
            System.out.println(String.format("Host nr: %d | katalog: %s | adres IPv4: %s pracuje na porcie: %d >", nrHosta, katalog, adresIp, gniazdoSerwera.getLocalPort()));

                boolean running = true;
                byte[] bufor = new byte[256];
                DatagramSocket gniazdo = null;

                try {
                    gniazdo = new DatagramSocket(port);
                } catch (SocketException e) {
                    e.printStackTrace();
                }

                while (running) {
                    DatagramPacket pakiet
                            = new DatagramPacket(bufor, bufor.length);
                    try {
                        gniazdo.receive(pakiet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    InetAddress address = pakiet.getAddress();
                    int port = pakiet.getPort();
                    pakiet = new DatagramPacket(bufor, bufor.length, address, port); //tworze nowy obiekt
                    String received = new String(pakiet.getData(), 0, pakiet.getLength());

                    if (received.equals("end")) {
                        running = false;
                        continue;
                    }
                    try {
                        gniazdo.send(pakiet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                gniazdo.close();

            }catch (Exception e) {
            System.out.println(e.getMessage());
        }
}

}

