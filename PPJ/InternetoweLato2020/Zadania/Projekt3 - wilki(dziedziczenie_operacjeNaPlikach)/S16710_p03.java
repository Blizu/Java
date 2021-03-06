import javax.net.ssl.SSLContext;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class S16710_p03 {

    public static void main (String args[]){

        final String ścieżkaDoPliku = System.getProperty("user.dir")+"/wilki.txt";
        System.out.println("Rozpoczynam pracę");

        System.out.println("Sprawdzam czy plik wilki.txt istnieje...");


        File file = new File(ścieżkaDoPliku);

        if(file.exists()){
            System.out.println("Znaleziono plik");
            //czytajPlik(ścieżkaDoPliku);
            czytaj(zaladuj(ścieżkaDoPliku));
        } else {
            System.out.println("Nie znaleziono pliku... Tworzę nowe stada");
            //Tworzenie pierwszego stada

            Wilk wilkA = new Wilk("WilkAlfa", (short) 2013, false, "wataha1", 1);
            Wilk wilkB = new Wilk("WilkBeta", (short) 2016, false, "wataha1", 2);

            Ssak[] tablicaSzczeniatWadera1 = utworzTabliceSzczeniat(2);
            Wadera wadera1 = new Wadera("Wadera1", (short) 2015, false, 2, tablicaSzczeniatWadera1);
            Ssak[] tablicaSzczeniatWadera2 = utworzTabliceSzczeniat(2);
            Wadera wadera2 = new Wadera("Wadera2", (short) 2016, false, 2, tablicaSzczeniatWadera2);

            //Tworzenie drugiego stada

            Wilk wilkC = new Wilk("WilkAlfa", (short) 2010, false, "wataha2", 1);
            Wilk wilkD = new Wilk("WilkBeta", (short) 2013, false, "wataha2", 2);

            Ssak[] tablicaSzczeniatWadera3 = utworzTabliceSzczeniat(2);
            Wadera wadera3 = new Wadera("Wadera3", (short) 2016, false, 2, tablicaSzczeniatWadera3);
            Ssak[] tablicaSzczeniatWadera4 = utworzTabliceSzczeniat(2);
            Wadera wadera4 = new Wadera("Wadera2", (short) 2014, false, 2, tablicaSzczeniatWadera4);

            System.out.println("Zapisuję stada do pliku wilki.txt");

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                wilkA.zapisz(fileOutputStream);
                wilkB.zapisz(fileOutputStream);
                wilkC.zapisz(fileOutputStream);
                wilkD.zapisz(fileOutputStream);
                wadera1.zapisz(fileOutputStream);
                wadera2.zapisz(fileOutputStream);
                wadera3.zapisz(fileOutputStream);
                wadera4.zapisz(fileOutputStream);
                fileOutputStream.close();

                System.out.println("Odczytuje z pliku");
                czytaj(zaladuj(ścieżkaDoPliku));
                //czytajPlik(ścieżkaDoPliku);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static Ssak[] zaladuj(String sciezka){

        Ssak[] tablicaPomocnicza = new Ssak[4]; //rozmiar tablicy powinien być zgodny z liczbą wszystkich ssaków
        int licznik=0;
        String nazwaWilka = "wilkZaladuj";


        FileInputStream fin = null;
        try {
            fin = new FileInputStream(sciezka);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));

            String strLine;

            while ((strLine = br.readLine()) != null && licznik<=tablicaPomocnicza.length)   {


                String [] wartosci = strLine.split(",");

                if (wartosci[0].contains("Wilk")) {

                    Wilk wilkZaladuj = new Wilk(wartosci[0], Short.parseShort(wartosci[1]), Boolean.parseBoolean(wartosci[2]), wartosci[3], Integer.parseInt(wartosci[4]));

                    tablicaPomocnicza[licznik] = wilkZaladuj;
                    licznik++;


                } else{

                    //Tutaj powinna zostać stworzona Wadera
                    //Wadera waderaZaladuj = new Wadera();
                }

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return tablicaPomocnicza;
    }

    public static void czytajPlik(String sciezka){

        FileInputStream fin = null;
        try {
            fin = new FileInputStream(sciezka);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));

            String strLine;

            while ((strLine = br.readLine()) != null)   {

                String [] values = strLine.split(",");
                for (String str : values) {
                    System.out.println(str);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void czytaj(Ssak[] tablicaSsakow) {
        for (Ssak s : tablicaSsakow) {
            System.out.print(s.przedstawSie());
        }
    }

    public static Ssak [] utworzTabliceSzczeniat(int iloscSzczeniat){

        Ssak [] tablicaSzczeniat = new Ssak[iloscSzczeniat];
        int przedzialWieku = (2020-2018)+1;

        for (int i=0; i<tablicaSzczeniat.length; i++){
            tablicaSzczeniat[i] = new Ssak("szczenie"+i, (short) ((short) (Math.random()*przedzialWieku) + 2018), true);
        }

        return tablicaSzczeniat;
    }
}

class Ssak implements Serializable {

    private String imie;
    private short rokUrodzenia;
    private boolean mlody;

    public Ssak(String imie, short rokUrodzenia, boolean mlody) {
        this.imie = imie;
        this.rokUrodzenia = rokUrodzenia;
        this.mlody = mlody;
    }

    String przedstawSie(){
        return imie +"," + rokUrodzenia +","+ mlody+",";
    }

    void zapisz(FileOutputStream zapis) throws IOException {

        byte[] przedstawSieByte = przedstawSie().getBytes(StandardCharsets.UTF_8);
        zapis.write(przedstawSieByte);
        System.out.println("Zapisano ssaka: "+przedstawSie());
    }
}

class Wadera extends Ssak implements Serializable {

    private int iloscSzczeniat;
    private Ssak[] szczenieta;


    public Wadera(String imie, short rokUrodzenia, boolean mlody, int iloscSzczeniat, Ssak[] szczenieta) {
        super(imie, rokUrodzenia,mlody);
        this.iloscSzczeniat = iloscSzczeniat;
        this.szczenieta = szczenieta;
    }

    String przedstawSie(){ //przesłonięta metoda z klasy bazowej

        return "\n"+super.przedstawSie()+ iloscSzczeniat +";"+"\n";
    }

    void zapisz(FileOutputStream zapis) throws IOException {
        String [] tablicaSzczeniatString = szczeniataToString(iloscSzczeniat);
        byte[] przedstawSieByte = przedstawSie().getBytes();
        byte[] szczenieta = tablicaStringToByte(tablicaSzczeniatString);

        zapis.write(przedstawSieByte);
        zapis.write(szczenieta);
        System.out.println("Zapisano wadere: "+przedstawSie());

    }

    private void  wyswietlTabliceSzczeniat(){
        for (Ssak s : szczenieta) {
            System.out.print(s.przedstawSie());
        }
    }
    private String[] szczeniataToString(int iloscSzczeniat){
        int i=0;
        String [] tablicaSzczeniat = new String[iloscSzczeniat];


        while (i < iloscSzczeniat){
            for (Ssak s : szczenieta) {
                tablicaSzczeniat[i]=s.przedstawSie();
                i++;
            }
        }
        i=0;
       return tablicaSzczeniat;
    }

    private byte[] tablicaStringToByte(String[] strs) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(strs);
            objectOutputStream.flush();
            objectOutputStream.close();

            final byte[] tablicaByte = byteArrayOutputStream.toByteArray();

            return tablicaByte;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        }
}

class Wilk extends Ssak implements Serializable {

    private String nazwaStada;
    private int pozycja;


    public Wilk(String imie, short rokUrodzenia, boolean mlody, String nazwaStada, int pozycja) {
        super(imie,rokUrodzenia,mlody);
        this.nazwaStada = nazwaStada;
        this.pozycja = pozycja;
    }

    String przedstawSie(){ //przesłonięta metoda z klasy bazowej

        return super.przedstawSie()+ nazwaStada +","+ pozycja+"\n";
    }

    void zapisz(FileOutputStream zapis) throws IOException {

        byte[] przedstawSieByte = przedstawSie().getBytes();
        zapis.write(przedstawSieByte);
        System.out.print("Zapisano wilka: "+przedstawSie());
    }
}
