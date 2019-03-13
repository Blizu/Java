import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

import static java.lang.System.out;

public class Konsola extends Thread {
    String katalog;

    public Konsola() {
    }

    @Override
    public void run() {
        try {
            Scanner daneZKonsoli = new Scanner(System.in);
            int port = 0;
            String komenda = null;
            Socket gniazdo = null;
            String linia = null;
            String adres = null;

            do {
                System.out.println("!!--Wpisz wybraną komendę--!!");
                System.out.print("list <adresIp hosta docelowego>:<PORT> - Wyświetla listę plików z wybranego hosta" + "\n" +
                        "pull <nazwa_pliku> <adresIp hosta docelowego>:<PORT> - Pobiera z wybranego hosta plik o wksazanej nazwie" + "\n" +
                        "push <nazwa_pliku> <adresIp hosta docelowego>:<PORT> - Wysyła na plik o wskazanej nazwie do wybranego hosta" + "\n");


                linia = daneZKonsoli.nextLine();
                String[] podzielDane = linia.split(" ");
                String Plik = null;
                if (podzielDane.length == 3) {

                    adres = podzielDane[1];
                    port = Integer.parseInt(podzielDane[2]);
                } else if (podzielDane.length == 4) {
                    Plik = podzielDane[1];
                    adres = podzielDane[2];
                    port = Integer.parseInt(podzielDane[3]);
                }
                komenda = podzielDane[0];
                if (Plik != null && !Plik.equals("")) {
                    komenda += " " + Plik;
                }

                if (linia.contains("list") || linia.contains("push") || linia.contains("pull") && linia != null && !linia.equals("")) {

                    try {
                        if (gniazdo == null || gniazdo.isClosed()) {
                            gniazdo = new Socket(adres, port);
                        }

                        if (komenda.startsWith("pull")) {

                            BufferedWriter out = WyslijKomende(gniazdo, komenda);
                            WyslijPlik(katalog, gniazdo, Plik);

                        } else if (komenda.startsWith("list")) {
                            BufferedWriter out = WyslijKomende(gniazdo, komenda);
                            PobierzListe(gniazdo, out);
                        } else if (komenda.startsWith("push")) {

                            BufferedWriter out = WyslijKomende(gniazdo, komenda);
                            if (gniazdo.isClosed() == false) {
                                gniazdo.close();
                            }
                            Socket fileSocket = new Socket(adres, port);
                            PobierzPlik(katalog, fileSocket, Plik);
                            if (fileSocket.isClosed() == false) {
                                fileSocket.close();
                            }
                        } else {
                            System.out.println("!!--Nieznana komenda--!!");
                        }

                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                } else {
                    out.println("!!--Nieznana komenda--!!");
                }
            } while (!linia.equals("EXIT"));
            if (gniazdo.isClosed() == false) {
                gniazdo.close();
            }

        } catch (Exception ex) {
            out.println(ex.toString());
        }
    }
    public  void PobierzListe(Socket gniazdo, BufferedWriter out) throws IOException {
        String wejsciowaLinia;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(gniazdo.getInputStream()));
        while ((wejsciowaLinia = in.readLine()) != null) {
            System.out.println(wejsciowaLinia);
        }
        in.close();
        out.close();
    }
    public  void PobierzPlik(String katalog, Socket gniazdo, String nazwaPliku) {
        try {
            File file = new File(katalog + "//" + nazwaPliku);
            InputStream inF = Files.newInputStream(file.toPath());
            OutputStream out = gniazdo.getOutputStream();

            int count;
            byte[] buffer = new byte[8192];
            while ((count = inF.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }

            out.close();
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }
    public void PokazListePlikow(int nazwaHosta, String IP, int port, String katalog, Socket gniazdo) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(gniazdo.getOutputStream()));
        MD5 md5 = new MD5();
        out.write(String.format("Host nr: %d | katalog: %s |  adres IPv4: %s | port: %d \r\n", nazwaHosta, katalog, IP, port));
        out.write(md5.zwrocMD5(new File(katalog)));
        out.flush();
        out.close();
    }

    public BufferedWriter WyslijKomende(Socket gniazdo, String komenda) throws IOException {

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(gniazdo.getOutputStream()));
        out.write(komenda);
        out.newLine();
        out.flush();
        return out;
    }
    public void WyslijPlik(String katalog, Socket gniazdo, String nazwaPliku) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
        int byteToBeRead = -1;

        File newFile = new File(katalog + "//" + nazwaPliku);
        FileOutputStream fs = new FileOutputStream(newFile);
        while ((byteToBeRead = in.read()) != -1) {
            fs.write(byteToBeRead);
        }
        System.out.println("Pomyślnie zapisano plik: " + nazwaPliku);
        fs.flush();
        fs.close();
    }

    }







