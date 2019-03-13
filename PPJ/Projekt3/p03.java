public class S16710_p02 {

    public static void main(String args[]) {

        String toyota = "Toyota_";
        String opel = "Opel_";
        String lexus = "Lexus_";
        String fiat = "Fiat_";
        int wartoscPoczatkowaMoc = 59;


        int[] tablicaMiejsc = {2, 4, 5, 7}; //Tablica przechowująca ilość miejsc dostępnych w każdym pojeździe
        //wyswietlTablice(tablicaMiejsc);
        System.out.println();

        int[] tablicaMocy = new int[350 - 59]; //Tablica przechowująca ilość zakres mocy silników (od 60 KM do 350 KM)
        for (int i = 0; i < tablicaMocy.length; i++) {
            tablicaMocy[i] = wartoscPoczatkowaMoc + i;
        }
        //wyswietlTablice(tablicaMocy);

        int[] tablicaPojemnosci = {1200, 1400, 1600, 1800, 2000, 2400, 2500, 3000, 3500, 4000, 4500}; //Tablica przechowująca pojemności silników
        //wyswietlTablice(tablicaPojemnosci);

//============================

        PojazdMechaniczny[] tablicaToyota = new PojazdMechaniczny[250];
        wypelnijTablicePojazdu(tablicaToyota, toyota, tablicaMiejsc, tablicaMocy, tablicaPojemnosci);
        // wyswietlTablicePojazdow(tablicaToyota);

        PojazdMechaniczny[] tablicaOpel = new PojazdMechaniczny[250];
        wypelnijTablicePojazdu(tablicaOpel, opel, tablicaMiejsc, tablicaMocy, tablicaPojemnosci);

        //wyswietlTablicePojazdow(tablicaOpel);

        PojazdMechaniczny[] tablicaLexus = new PojazdMechaniczny[250];
        wypelnijTablicePojazdu(tablicaLexus, lexus, tablicaMiejsc, tablicaMocy, tablicaPojemnosci);
        //wyswietlTablicePojazdow(tablicaLexus);

        PojazdMechaniczny[] tablicaFiat = new PojazdMechaniczny[250];
        wypelnijTablicePojazdu(tablicaFiat, fiat, tablicaMiejsc, tablicaMocy, tablicaPojemnosci);
        //wyswietlTablicePojazdow(tablicaFiat);

        PojazdMechaniczny[] tablicaWszystkichPojazdow = new PojazdMechaniczny[tablicaToyota.length + tablicaOpel.length + tablicaLexus.length + tablicaFiat.length];

        wypelnijTabliceWszystkichPojazdow(tablicaWszystkichPojazdow, tablicaToyota, tablicaOpel, tablicaLexus, tablicaFiat);
        System.out.println("--------------------------------------------------------------------------------------------------------------" + " Tablica przed sortowaniem " + "---------------------------------------------------------------------------------" + "\n");
        wyswietlTablicePojazdow(tablicaWszystkichPojazdow);

        sortuj(tablicaWszystkichPojazdow);
        System.out.println("--------------------------------------------------------------------------------------------------------------" + " Tablica po sortowaniu " + "-------------------------------------------------------------------------------------" + "\n");
        wyswietlTablicePojazdow(tablicaWszystkichPojazdow);

// ======================
    }

    public static void wyswietlTablice(int[] tablica) {

        for (int i = 0; i < tablica.length; i++) {
            System.out.println("[" + i + "]" + (tablica[i]));
        }
    }

    public static void wyswietlTablicePojazdow(PojazdMechaniczny[] tablica) {
        for (PojazdMechaniczny p : tablica) {
            System.out.println(p.toString());
        }
    }

    //Metoda wypełniająca parametrami tablice dla poszczególnych marek
    public static void wypelnijTablicePojazdu(PojazdMechaniczny[] tablicaPojazdow, String nazwaModelu, int[] tablicaMiejsc, int[] tablicaMocy, int[] tablicaPojemnosci) {
        String nazwa = nazwaModelu;
        int iloscMiejsc = 0;
        int mocSilnika = 0;
        int pojemnoscSilnika = 0;

        for (int i = 0; i < tablicaPojazdow.length; i++) {
            iloscMiejsc = tablicaMiejsc[(int) (Math.random() * 4)];

            if (iloscMiejsc == 2) {
                mocSilnika = tablicaMocy[(int) (Math.random() * (290 - 130) + 130)];
                pojemnoscSilnika = tablicaPojemnosci[(int) (Math.random() * (11 - 7) + 7)];
            } else if (iloscMiejsc == 4) {
                mocSilnika = tablicaMocy[(int) (Math.random() * (90))];
                if (mocSilnika < 100)
                    pojemnoscSilnika = tablicaPojemnosci[(int) (Math.random() * (2))];
                else
                    pojemnoscSilnika = tablicaPojemnosci[(int) (Math.random() * (4 - 1) + 1)];
            } else if (iloscMiejsc == 5) {
                mocSilnika = tablicaMocy[(int) (Math.random() * (250 - 90) + 90)];
                if (mocSilnika < 140)
                    pojemnoscSilnika = tablicaPojemnosci[(int) (Math.random() * (1))];
                else if (mocSilnika > 140 && mocSilnika < 200)
                    pojemnoscSilnika = tablicaPojemnosci[(int) (Math.random() * (6 - 4) + 4)];
                else
                    pojemnoscSilnika = tablicaPojemnosci[(int) (Math.random() * (8 - 6) + 6)];
            } else {

                mocSilnika = tablicaMocy[(int) (Math.random() * (140 - 40) + 40)];
                if (mocSilnika < 140)
                    pojemnoscSilnika = tablicaPojemnosci[(int) (Math.random() * (2))];
                else
                    pojemnoscSilnika = tablicaPojemnosci[(int) (Math.random() * (6 - 3) + 3)];
            }

            tablicaPojazdow[i] = new PojazdMechaniczny(nazwa, (i + 1), iloscMiejsc, mocSilnika, pojemnoscSilnika);

        }
    }

    //Metoda, która scala cztery tablice jednowymiarowe w jedną
    public static void wypelnijTabliceWszystkichPojazdow(PojazdMechaniczny[] calaTablica, PojazdMechaniczny[] tab1, PojazdMechaniczny[] tab2, PojazdMechaniczny[] tab3, PojazdMechaniczny[] tab4) {
        int licznik = 0;
        for (int i = 0; i < tab1.length; i++) {
            calaTablica[i] = tab1[i];
            licznik++;
        }

        for (int i = 0; i < tab2.length; i++) {
            calaTablica[licznik++] = tab2[i];
        }

        for (int i = 0; i < tab3.length; i++) {
            calaTablica[licznik++] = tab3[i];
        }

        for (int i = 0; i < tab4.length; i++) {
            calaTablica[licznik++] = tab4[i];
        }
    }

    public static void sortuj(PojazdMechaniczny[] tab) { //sortowanie babelkowe
        boolean zamieniony = true;
        int ostatni = tab.length - 2;

        while (zamieniony) {
            zamieniony = false;

            for (int i = 0; i <= ostatni; i++) {
                if (tab[i].compareTo(tab[i + 1]) > 0) { //porównanie obiektów względem określonych wytycznych
                    zamien(tab, i, i + 1);
                    zamieniony = true;
                }
            }
            ostatni--;
        }
    }

    public static void zamien(PojazdMechaniczny[] tab, int from, int to) { //metoda wykorzystywana na rzecz sortowania. Zamienia wartości pod odpowiednimi indeksami
        PojazdMechaniczny temp = tab[from];
        tab[from] = tab[to];
        tab[to] = temp;
    }


}

class Silnik {
    private int moc;
    private long pojemnosc;

    public Silnik(int moc, long pojemnosc) {
        this.moc = moc;
        this.pojemnosc = pojemnosc;
    }

    public int returnMoc() {
        return moc;
    }

    public long returnPojemnosc() {
        return pojemnosc;
    }
}

class PojazdMechaniczny implements Comparable<PojazdMechaniczny> {
    private int iloscMiejsc;
    private String nazwa;
    private int numerPojazdu;
    private Silnik silnik;

    public PojazdMechaniczny(String nazwa, int numerPojazdu, int iloscMiejsc, int moc, long pojemnosc) {
        this.nazwa = nazwa;
        this.numerPojazdu = numerPojazdu;
        this.iloscMiejsc = iloscMiejsc;
        this.silnik = new Silnik(moc, pojemnosc);
    }

    public String returnNazwa() {
        return nazwa;
    }

    public int returnNumerPojazdu() {
        return numerPojazdu;
    }

    public int returnIloscMiejsc() {
        return iloscMiejsc;
    }

    public int returnMocSilnika() {
        return silnik.returnMoc();
    }

    public long returnPojemnoscSilnika() {
        return silnik.returnPojemnosc();
    }

    public String toString() {
        return "Nazwa pojazdu: " + returnNazwa() + "" + returnNumerPojazdu() + "\n" +
                "Moc silnika: " + returnMocSilnika() + " KM" + "\n" +
                "Pojemność silnika: " + returnPojemnoscSilnika() + " cm^3" + "\n" +
                "Ilość miejsc: " + returnIloscMiejsc() + "\n" +
                "=================================" + "\n";

    }

    @Override
    public int compareTo(PojazdMechaniczny pojazd) { //metoda porównująca dwa obiekty ze sobą
        if (this.returnMocSilnika() > pojazd.returnMocSilnika())
            return 1;
        else if (this.returnMocSilnika() == pojazd.returnMocSilnika()) {
            if (this.returnPojemnoscSilnika() > pojazd.returnPojemnoscSilnika())
                return 1;
            else if (this.returnPojemnoscSilnika() == pojazd.returnPojemnoscSilnika())
                if (this.returnIloscMiejsc() > pojazd.returnIloscMiejsc())
                    return 1;
                else if (this.returnIloscMiejsc() == pojazd.returnIloscMiejsc())
                    if (stringCompare(this.returnNazwa(), pojazd.returnNazwa()) > 0)
                        return 1;
                    else if (stringCompare(this.returnNazwa(), pojazd.returnNazwa()) == 0)
                        if (this.returnNumerPojazdu() > pojazd.returnNumerPojazdu())
                            return 1;
        }
        return -1;
    }

    public int stringCompare(String string1, String string2) //metoda porównująca dwa napisy ze sobą.
    {
        int slowo1 = string1.length();
        int slowo2 = string2.length();

        for (int i = 0; i < slowo1 && i < slowo2; i++) {
            int str1_char = (int) string1.charAt(i);
            int str2_char = (int) string2.charAt(i);

            if (str1_char == str2_char) {
                continue;
            } else {
                return str1_char - str2_char;
            }
        }
        if (slowo1 < slowo2) {
            return slowo1 - slowo2;
        } else if (slowo1 > slowo2) {
            return slowo1 - slowo2;
        } else {
            return 0;
        }
    }
}


