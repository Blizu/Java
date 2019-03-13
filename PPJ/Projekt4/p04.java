import java.io.*;
import java.util.Scanner;

public class S16710_p03 {
    public static void main(String[] args) throws NiewlasciweDaneException {

        int iloscOsob = 0;
        int licznikOsob = 0;
        String imie = null;
        String nazwisko = null;
        short rokUrodzenia = 0;
        boolean plec;
        int kodPocztowy;
        String nazwaPliku = "osoba.bin";


        boolean flaga = false;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ile chcesz stworzyć osób?");
            while (!scanner.hasNextInt()) {
                System.out.println("Wprowadzono inną wartość niż liczba! Wpisz ponownie poprawną wartość");
                scanner.next();
            }
            iloscOsob = scanner.nextInt();
            flaga = true;
        } while (flaga == false);

        Osoba[] tablicaOsob = new Osoba[iloscOsob];

        if (iloscOsob > 1) {
            do {
                System.out.println("Podaj imie osoby nr " + (licznikOsob + 1));
                try {
                    imie = dialog_imie();
                } catch (NiewlasciweDaneException e) {
                    e.printStackTrace();
                    System.out.println("Podaj ponownie imie osoby " + (licznikOsob + 1));
                    imie = dialog_imie();
                }
                System.out.println("Podaj nazwisko osoby nr " + (licznikOsob + 1));
                try {
                    nazwisko = dialog_nazwisko();
                } catch (NiewlasciweDaneException e) {
                    e.printStackTrace();
                    System.out.println("Podaj nazwisko osoby nr " + (licznikOsob + 1));
                    nazwisko = dialog_nazwisko();
                }
                System.out.println("Podaj rok urodzenia osoby nr " + (licznikOsob + 1));
                try {
                    rokUrodzenia = dialog_rok();
                } catch (NiewlasciweDaneException e) {
                    e.printStackTrace();
                    System.out.println("Podaj ponownie rok urodzenia osoby nr " + (licznikOsob + 1));
                    rokUrodzenia = dialog_rok();
                }
                System.out.println("Podaj plec osoby nr " + (licznikOsob + 1));
                try {
                    plec = dialog_plec();
                } catch (NiewlasciweDaneException e) {
                    e.printStackTrace();
                    System.out.println("Podaj ponownie płeć osoby nr " + (licznikOsob + 1) + " Wpisz odpowiednie wartośći (Mężczyzna - true | Kobieta - false)");
                    plec = dialog_plec();
                }
                System.out.println("Podaj kod pocztowy osoby nr " + (licznikOsob + 1) + " pomijająć znak \"-\"");
                try {
                    kodPocztowy = dialog_kod();
                } catch (NiewlasciweDaneException e) {
                    e.printStackTrace();
                    System.out.println("Podaj ponownie kod pocztowy osoby nr " + (licznikOsob + 1));
                    kodPocztowy = dialog_kod();
                }

                dodajOsobedoTablicy(tablicaOsob, licznikOsob, imie, nazwisko, rokUrodzenia, plec, kodPocztowy);
                licznikOsob++;


            } while (iloscOsob != licznikOsob);

            zapiszTabliceOsob(tablicaOsob, nazwaPliku);
            System.out.println("Zapisuje osoby do pliku " + nazwaPliku + " .................");
            //wyswietlTabliceOsob(tablicaOsob);
            System.out.println("Odczytuje osoby z pliku " + nazwaPliku + " ................");
            odczytajTabliceOsob(nazwaPliku);

            //------------------------------ pojedyńcza osoba

        } else if (iloscOsob == 1) {
            System.out.println("Podaj imie osoby");
            try {
                imie = dialog_imie();
            } catch (NiewlasciweDaneException e) {
                e.printStackTrace();
                System.out.println("Podaj ponownie imie osoby");
                imie = dialog_imie();
            }
            System.out.println("Podaj nazwisko osoby");
            try {
                nazwisko = dialog_nazwisko();
            } catch (NiewlasciweDaneException e) {
                e.printStackTrace();
                System.out.println("Podaj nazwisko osoby");
                nazwisko = dialog_nazwisko();
            }
            System.out.println("Podaj rok urodzenia osoby");
            try {
                rokUrodzenia = dialog_rok();
            } catch (NiewlasciweDaneException e) {
                e.printStackTrace();
                System.out.println("Podaj ponownie rok urodzenia osoby");
                rokUrodzenia = dialog_rok();
            }
            System.out.println("Podaj plec osoby");
            try {
                plec = dialog_plec();
            } catch (NiewlasciweDaneException e) {
                e.printStackTrace();
                System.out.println("Podaj ponownie płeć osoby nr. Wpisz odpowiednie wartośći (Mężczyzna - true | Kobieta - false)");
                plec = dialog_plec();
            }
            System.out.println("Podaj kod pocztowy osoby pomijająć znak \"-\"");
            try {
                kodPocztowy = dialog_kod();
            } catch (NiewlasciweDaneException e) {
                e.printStackTrace();
                System.out.println("Podaj ponownie kod pocztowy osoby");
                kodPocztowy = dialog_kod();
            }
            Osoba osoba = new Osoba(imie, nazwisko, rokUrodzenia, plec, kodPocztowy);

            System.out.println("Zapisuje osobe do pliku " + nazwaPliku + " .................");
            zapiszJednaOsobe(nazwaPliku, osoba);
            System.out.println("Odczytuje osobe z pliku " + nazwaPliku + " ................");
            odczytajJednaOsobe(nazwaPliku);

        } else
            System.out.println("Nie można dodać");

    }


    static void dodajOsobedoTablicy(Osoba[] tablica, int licznik, String imie, String nazwisko, short rok, boolean plec, int kod) {
        tablica[licznik] = new Osoba(imie, nazwisko, rok, plec, kod);
    }

    public static void wyswietlTabliceOsob(Osoba[] tablica) {
        for (Osoba p : tablica) {
            System.out.println(p.toString());
        }
    }


    static String dialog_imie() throws NiewlasciweDaneException {
        String imie = null;
        boolean flaga = false;
        Scanner scanner = new Scanner(System.in);

        do {
            // System.out.println("Podaj imie:");
            if (scanner.hasNextInt()) {
                throw new NiewlasciweDaneException();
            } else if (scanner.hasNext()) {
                String input = scanner.nextLine();
                if (input.length() > 1) {
                    flaga = true;
                    imie = input;
                } else {
                    flaga = false;
                    throw new NiewlasciweDaneException();
                }
            } else
                throw new NiewlasciweDaneException();

        } while (flaga == false);
        return imie;
    }

    static String dialog_nazwisko() throws NiewlasciweDaneException {
        String nazwisko = null;
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            throw new NiewlasciweDaneException();
        } else if (scanner.hasNext()) {
            String string = scanner.nextLine();
            if (string.length() > 1) {
                nazwisko = string;
            } else {
                throw new NiewlasciweDaneException();
            }
        }
        return nazwisko;
    }

    static short dialog_rok() throws NiewlasciweDaneException {
        boolean flaga = false;
        short rok = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            //System.out.println("Podaj rok urodzenia");
            while (!scanner.hasNextShort()) {
                scanner.next();
                throw new NiewlasciweDaneException();
            }
            rok = scanner.nextShort();
            if (rok <= 1920 || rok >= 2019) {
                System.out.println("Rok nie może być mniejszy niż 1920 i nie większy niż 2019! Wpisz rok ponownie");
                dialog_rok();
            } else
                flaga = true;
        } while (flaga == false);

        return rok;
    }

    static boolean dialog_plec() throws NiewlasciweDaneException {
        boolean flaga = false;
        boolean odpowiedz;
        Scanner scanner = new Scanner(System.in);
        do {
            // System.out.println("Wpisz odpowiednie wartośći (Mężczyzna - true | Kobieta - false)");
            while (!scanner.hasNextBoolean()) {
                scanner.next();
                throw new NiewlasciweDaneException();
            }
            flaga = true;
            odpowiedz = scanner.nextBoolean();
        } while (flaga == false);
        return odpowiedz;
    }

    static int dialog_kod() throws NiewlasciweDaneException {
        boolean flaga = false;
        int kod;
        Scanner scanner = new Scanner(System.in);
        do {
            //   System.out.println("Podaj kod pocztowy");
            while (!scanner.hasNextInt()) {
                scanner.next();
                throw new NiewlasciweDaneException();
            }
            kod = scanner.nextInt();
            flaga = true;
        } while (flaga == false);
        return kod;
    }

    static void zapiszJednaOsobe(String nazwaPliku, Osoba osoba) {
        //wykorzystuje bufforowanie
        try {

            OutputStream f = new FileOutputStream(nazwaPliku);
            OutputStream buffer = new BufferedOutputStream(f);
            OutputStream o = new ObjectOutputStream(buffer);
            ((ObjectOutputStream) o).writeObject(osoba);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            System.out.println("Błąd inicjonowania strumienia");
        }
    }

    static void odczytajJednaOsobe(String nazwaPliku) {
        try {
            InputStream fi = new FileInputStream(nazwaPliku);
            InputStream buffer = new BufferedInputStream(fi);
            ObjectInputStream oi = new ObjectInputStream(buffer);
            Osoba osobaOdczyt = (Osoba) oi.readObject();
            System.out.println(osobaOdczyt.toString());
            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            System.out.println("Błąd inicjonowania strumienia");
        } catch (ClassNotFoundException e) {
            System.out.println("Klasa nie istnieje");
        }

    }

    static void zapiszTabliceOsob(Osoba[] tablica, String nazwaPliku) {
        //wykorzystuje bufforowanie
        try {
            OutputStream file = new FileOutputStream(nazwaPliku);
            OutputStream buffer = new BufferedOutputStream(file);
            OutputStream o = new ObjectOutputStream(buffer);
            ((ObjectOutputStream) o).writeObject(tablica);
            o.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            System.out.println("Błąd inicjonowania strumienia");
        }
    }

    static void odczytajTabliceOsob(String nazwaPliku) {
        try {
            InputStream fis = new FileInputStream(nazwaPliku);
            InputStream buffer = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(buffer);
            Osoba[] osobyZPliku = (Osoba[]) ois.readObject();
            wyswietlTabliceOsob(osobyZPliku);
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            System.out.println("Błąd inicjonowania strumienia");
        } catch (ClassNotFoundException e) {
            System.out.println("Klasa nie istnieje");
        }
    }

    public static class Osoba implements Serializable {
        String imie;
        String nazwisko;
        short rokUrodzenia;
        boolean plec;
        int kodPocztowy;

        public Osoba(String imie, String naziwsko, short rokUrodzenia, boolean plec, int kodPocztowy) {

            this.imie = imie;
            this.nazwisko = naziwsko;
            this.rokUrodzenia = rokUrodzenia;
            this.plec = plec;
            this.kodPocztowy = kodPocztowy;
        }
        public String toString() {
            return "Imie: " + imie + "\n" +
                    "Nazwisko: " + nazwisko + "\n" +
                    "Rok urodzenia: " + rokUrodzenia + "\n" +
                    "Płeć: " + plec + "\n" +
                    "Kod Pocztowy: " + kodPocztowy + "\n";
        }
    }

    public static class NiewlasciweDaneException extends Exception {
        public NiewlasciweDaneException() {
        }
    }
}

