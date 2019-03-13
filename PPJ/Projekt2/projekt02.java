import java.util.Scanner;


public class S16710_p01 {

    public static void main(String args[]) {

        char[][] strzalyGracz1 = new char[10][10];
        char[][] trafieniaGracz1 = new char[10][10];
        int[][] wspolrzedneGracz1 = new int[4][2];
        char[][] strzalyGracz2 = new char[10][10];
        char[][] trafieniaGracz2 = new char[10][10];
        int[][] wspolrzedneGracz2 = new int[4][2];
        int Gracz1_iloscStatkow = 20;
        int Gracz2_iloscStatkow = 20;
        int Gracz1_trafioneStatki = 0;
        int Gracz2_trafioneStatki = 0;
        int Gracz1_iloscStrzalow = 0;
        int Gracz2_iloscStrzalow = 1;
        String gracz1 = "Gracz 1";
        String gracz2 = "Gracz 2";
        boolean rundaGracz1 = false;
        boolean czyTrafiony = false;
        //
        System.out.println("Witaj w grze statki!");
        System.out.println("Na początku każdy z graczy zostanie poproszony o ustawienie na planszy statków według następującej hierarchii:" + "\n" +
                "1. 1 x 4 masztowiec" + "\n" +
                "2. 2 x 3 masztowiec" + "\n" +
                "3. 3 x 2 masztowiec" + "\n" +
                "4. 4 x 1 masztowiec" + "\n" +
                "Po ustawieniu statków przez graczy rozpocznie się gra. Strzały oddawane są naprzemiennie, poprzez podanie współrzędnych." + "\n" +
                "W przypadku strzału trafionego, gracz kontynuuje strzelanie (czyli swój ruch) aż do momentu chybienia. Zatopienie statku ma miejsce wówczas, gdy gracz odgadnie położenie całego statku." + "\n"+
                "Oznaczenia:"+"\n"+
                "T - Trafienie"+"\n"+
                "P - Pudło"+"\n"+
                "~ - Woda"+"\n"+
                "@ - Element statku"+"\n"
        );



        inicjujPlansze(trafieniaGracz1);
        inicjujPlansze(strzalyGracz1);
        inicjujPlansze(trafieniaGracz2);
        inicjujPlansze(strzalyGracz2);


        System.out.println();
        pokazPlansze(trafieniaGracz1);
        wskazStatek(gracz1, 4, wspolrzedneGracz1, trafieniaGracz1);
        pokazPlansze(trafieniaGracz1);
        wskazStatek(gracz1, 3, wspolrzedneGracz1, trafieniaGracz1);
        pokazPlansze(trafieniaGracz1);
        wskazStatek(gracz1,3,wspolrzedneGracz1, trafieniaGracz1);
        pokazPlansze(trafieniaGracz1);
        System.out.println();
        wskazStatek(gracz1,2,wspolrzedneGracz1, trafieniaGracz1);
        pokazPlansze(trafieniaGracz1);
        System.out.println();
        wskazStatek(gracz1,2,wspolrzedneGracz1, trafieniaGracz1);
        pokazPlansze(trafieniaGracz1);
        System.out.println();
        wskazStatek(gracz1,2,wspolrzedneGracz1, trafieniaGracz1);
        pokazPlansze(trafieniaGracz1);
        System.out.println();
        wskazStatek(gracz1,1,wspolrzedneGracz1, trafieniaGracz1);;
        pokazPlansze(trafieniaGracz1);
        System.out.println();
        wskazStatek(gracz1,1,wspolrzedneGracz1, trafieniaGracz1);;
        pokazPlansze(trafieniaGracz1);
        System.out.println();
        wskazStatek(gracz1,1,wspolrzedneGracz1, trafieniaGracz1);;
        pokazPlansze(trafieniaGracz1);
        System.out.println();
        wskazStatek(gracz1,1,wspolrzedneGracz1, trafieniaGracz1);;
        pokazPlansze(trafieniaGracz1);
        System.out.println();

        ///
        wskazStatek(gracz2, 4, wspolrzedneGracz2, trafieniaGracz2);
        System.out.println();
        pokazPlansze(trafieniaGracz2);
        System.out.println();
        wskazStatek(gracz2,3, wspolrzedneGracz2, trafieniaGracz2);
        System.out.println();
        pokazPlansze(trafieniaGracz2);
        wskazStatek(gracz2,3, wspolrzedneGracz2, trafieniaGracz2);
        System.out.println();
        pokazPlansze(trafieniaGracz2);
        wskazStatek(gracz2,2, wspolrzedneGracz2, trafieniaGracz2);
        System.out.println();
        pokazPlansze(trafieniaGracz2);
        wskazStatek(gracz2,2, wspolrzedneGracz2, trafieniaGracz2);
        System.out.println();
        pokazPlansze(trafieniaGracz2);
        wskazStatek(gracz2,2, wspolrzedneGracz2, trafieniaGracz2);
        System.out.println();
        pokazPlansze(trafieniaGracz2);
        wskazStatek(gracz2,1, wspolrzedneGracz2, trafieniaGracz2);
        System.out.println();
        pokazPlansze(trafieniaGracz2);
        wskazStatek(gracz2,1, wspolrzedneGracz2, trafieniaGracz2);
        System.out.println();
        pokazPlansze(trafieniaGracz2);
        wskazStatek(gracz2,1, wspolrzedneGracz2, trafieniaGracz2);
        System.out.println();
        pokazPlansze(trafieniaGracz2);
        wskazStatek(gracz2,1, wspolrzedneGracz2, trafieniaGracz2);
        System.out.println();
        pokazPlansze(trafieniaGracz2);
        System.out.println();
        pokazPlansze(strzalyGracz1);
        System.out.println();
        //czyTrafiony=strzelajGracz1(trafieniaGracz2,trafieniaGracz1);
        strzelaj(trafieniaGracz2,strzalyGracz1);
        pokazPlansze(strzalyGracz1);

        rundaGracz1 = true;
        do {


            if (rundaGracz1 == true) {
                System.out.println("Plansza statków gracza 1:" + "\n");
                pokazPlansze(trafieniaGracz1);
                System.out.println("Plansza strzałów gracza 1:" + "\n");
                pokazPlansze(strzalyGracz1);
                czyTrafiony = strzelaj(strzalyGracz1, trafieniaGracz2);

                if (czyTrafiony == true) {
                    Gracz1_trafioneStatki++;
                    Gracz1_iloscStrzalow++;
                    Gracz2_iloscStatkow--;
                    rundaGracz1 = true;
                } else {
                    rundaGracz1 = false;
                    czyTrafiony = false;

                }
            } else {
                System.out.println("Plansza statków gracza 2:" + "\n");
                pokazPlansze(trafieniaGracz2);
                System.out.println("Plansza strzałów gracza 2:" + "\n");
                pokazPlansze(strzalyGracz2);
                czyTrafiony = strzelaj(strzalyGracz2, trafieniaGracz1);
                if (czyTrafiony == true) {
                    Gracz2_trafioneStatki++;
                    Gracz2_iloscStrzalow++;
                    Gracz1_iloscStatkow--;
                    rundaGracz1 = false;
                } else {
                    rundaGracz1 = true;
                    czyTrafiony = false;
                }
            }

        } while (Gracz1_trafioneStatki != Gracz1_iloscStatkow || Gracz2_trafioneStatki != Gracz2_iloscStatkow);


        System.out.print("Koniec gry!" + " ");
        if (Gracz2_iloscStatkow == 0)
            System.out.print("Gracz 1 wygrywa!" + "\n");
        else if (Gracz1_iloscStatkow == 0)
            System.out.println("Gracz 2 wygrywa!" + "\n");

        System.out.println();
        System.out.println("Statystki gracz 1:" + "\n" +
                "Ilość oddanych strzałów: " + Gracz1_iloscStrzalow + "\n" +
                "Ilość trafionych statków przeciwnika: " + Gracz1_trafioneStatki + "\n" +
                "Ilość posiadanych statków: " + Gracz1_iloscStatkow + "\n");

        System.out.println("Statystki gracz 2:" + "\n" +
                "Ilość oddanych strzałów: " + Gracz2_iloscStrzalow + "\n" +
                "Ilość trafionych statków przeciwnika: " + Gracz2_trafioneStatki + "\n" +
                "Ilość posiadanych statków: " + Gracz2_iloscStatkow + "\n");

    }

    public static void inicjujPlansze(char[][] plansza) { //Metoda inicjuje całą tablice 10 x 10 wartościami '0'
        for (int rzad = 0; rzad < plansza.length; rzad++)
            for (int kolumna = 0; kolumna < plansza.length; kolumna++)
                plansza[rzad][kolumna] = '0';
    }

    public static void pokazPlansze(char[][] plansza) { //Metoda wyświetla plansze
        System.out.println("\t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9 \t10");
        for (int rzad = 0; rzad < plansza.length; rzad++) {
            System.out.print((rzad + 1) + "");
            for (int kolumna = 0; kolumna < plansza.length; kolumna++) {
                //Jeżeli w danym miejscu tablicy znajduje się '0' to pokazuj znak wody "~"
                if (plansza[rzad][kolumna] == '0')
                    System.out.print("\t" + "~");
                    //W przypadku nie trafienia w cel zamień wartość wskazanego miejsca na znak spudłowania "P"
                else if (plansza[rzad][kolumna] == '1')
                    System.out.print("\t" + "P");
                    //Jeżeli strzał został prawidłowo oddany zamień wskazane miejsce na znak trafienia "T"
                else if (plansza[rzad][kolumna] == '2')
                    System.out.print("\t" + "T");
                else if (plansza[rzad][kolumna] == '3')
                    System.out.print("\t" + "@");

            }
            System.out.println();
        }
    }


    private static void wyswietlTabliceWsporzednych(int[][] tablicaWsporzednych) {
        for (int i = 0; i < tablicaWsporzednych.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(tablicaWsporzednych[i][j] + ",");
            }
        }
    }

    public static boolean sprawdzajWspolrzednePionowo(int ilosc_masztow, int wspolrzedna1, int wspolrzedna2, char[][] tablicaTafien) {
        boolean sprawdz = false;

        for (int i = 1; i < ilosc_masztow; i++, wspolrzedna2++) { //sprawdza w tablicy trafien, czy nie ma statku na wskazanych pozycjach
            if (tablicaTafien[(wspolrzedna2 - 1)][(wspolrzedna1 - 1)] == '0') {
                sprawdz = true;
            } else {
                System.out.println("We wskazanym miesjcu " + "(" + wspolrzedna1 + "," + wspolrzedna2 + ")" + " znajduje się inny statek!");
                System.out.println();
                return false;
            }
        }
        if (sprawdz == true) {
            return true;
        }
        return false;
    }

    public static boolean sprawdzajWspolrzedneLewo(int ilosc_masztow, int wspolrzedna1, int wspolrzedna2, char[][] tablicaTafien) {
        boolean sprawdz = false;

        for (int i = ilosc_masztow; i > 0; i--, wspolrzedna1--) { //sprawdza w tablicy trafien, czy nie ma statku na wskazanych pozycjach
            if (tablicaTafien[(wspolrzedna2 - 1)][(wspolrzedna1 - 1)] == '0') {
                sprawdz = true;
            } else {
                System.out.println("We wskazanym miesjcu " + "(" + wspolrzedna1 + "," + wspolrzedna2 + ")" + " znajduje się inny statek!");
                System.out.println();
                return false;
            }
        }
        if (sprawdz == true) {
            return true;
        }
        return false;
    }

    public static boolean sprawdzajWspolrzednePrawo(int ilosc_masztow, int wspolrzedna1, int wspolrzedna2, char[][] tablicaTafien) {
        boolean sprawdz = false;

        for (int i = 1; i < ilosc_masztow; i++, wspolrzedna1++) { //sprawdza w tablicy trafien, czy nie ma statku na wskazanych pozycjach
            if (tablicaTafien[(wspolrzedna2 - 1)][(wspolrzedna1 - 1)] == '0') {
                sprawdz = true;
            } else {
                System.out.println("We wskazanym miesjcu " + "(" + wspolrzedna1 + "," + wspolrzedna2 + ")" + " znajduje się inny statek!");
                System.out.println();
                return false;
            }
        }
        if (sprawdz == true) {
            return true;
        }
        return false;
    }


    public static void umiescStatek(int ilosc_masztow, int pozycja, int[][] tablicaWsporzednych, char[][] tablicaTrafien) {

        //0 - pionowo
        //1 - poziomo
        if (pozycja == 0) {
            for (int i = 0; i < ilosc_masztow; i++) {
                for (int j = 0; j < 1; j++) {
                    tablicaTrafien[(tablicaWsporzednych[i][j + 1]) - 1][(tablicaWsporzednych[i][j]) - 1] = '3';
                }
            }
        }
        if (pozycja == 1) {
            for (int i = 0; i < ilosc_masztow; i++) {
                for (int j = 0; j < 1; j++) {
                    tablicaTrafien[(tablicaWsporzednych[i][j + 1] - 1)][(tablicaWsporzednych[i][j]) - 1] = '3';
                }
            }
        }
    }


    public static void wskazStatek(String gracz, int ilosc_masztow, int[][] tablicaWsporzednych, char[][] tablicaTafien) {

        int wspolrzedna1, wspolrzedna2;
        int odpowiedz1;//odpowiedź na pytanie poziomo/pionowo
        int odpowiedz2;//odpowiedź na pytanie lewo prawo;
        boolean pionowo = false;
        boolean poziomo = false;
        boolean ustaw = false; //zmienna pomocnicza
        Scanner daneWejsciowe = new Scanner(System.in);


        if (ilosc_masztow == 1) { // Jeżeli 1 masztowiec, to sprawdz tylko jedna wspolrzedna
            System.out.println(gracz + ":");
            System.out.println("Podaj pierwszą współrzędną " + ilosc_masztow + " masztowca:");
            System.out.println("Rząd:");
            wspolrzedna1 = daneWejsciowe.nextInt();
            System.out.println("Kolumna:");
            wspolrzedna2 = daneWejsciowe.nextInt();

            if (tablicaTafien[wspolrzedna2 - 1][wspolrzedna1 - 1] == '0') {
                tablicaWsporzednych[0][0] = wspolrzedna1;
                tablicaWsporzednych[0][1] = wspolrzedna2;
                umiescStatek(ilosc_masztow, 0, tablicaWsporzednych, tablicaTafien);
            } else {
                System.out.println("Nie możesz tutaj postawić statku!");
                wskazStatek(gracz, ilosc_masztow, tablicaWsporzednych, tablicaTafien);
            }
        } else {
            System.out.println(gracz + ":");
            System.out.println("Podaj pierwszą współrzędną " + ilosc_masztow + " masztowca:");
            System.out.println("Rząd:");
            wspolrzedna1 = daneWejsciowe.nextInt();
            System.out.println("Kolumna:");
            wspolrzedna2 = daneWejsciowe.nextInt();
            System.out.println("Jak ma być ustawiony statek? (wpisz: 0 (poziomo) lub 1 (pionowo))");
            odpowiedz1 = daneWejsciowe.nextInt();


            //Jeżeli gracz wskazał pozycje pionowo, to sprawdza pola poniżej wskazanej współrzędnej, czy nie ma tam żadnego statku. Jeśli nie ma to umieszcza statek w tablicy trafień
            if (odpowiedz1==1) {  //dopisać metodę porównująca
                pionowo = true;
                poziomo = false;
                if (tablicaTafien[wspolrzedna2 - 1][wspolrzedna1 - 1] == '0') {
                    tablicaWsporzednych[0][0] = wspolrzedna1;
                    tablicaWsporzednych[0][1] = wspolrzedna2;
                } else
                    System.out.println("Nie możesz tutaj postawić statku!");
            } else {
                poziomo = true;
                pionowo = false;
            }

            if (pionowo == true) {
                ustaw = sprawdzajWspolrzednePionowo(ilosc_masztow, wspolrzedna1, wspolrzedna2, tablicaTafien);
                if (ustaw == true) {
                    for (int i = 0; i < ilosc_masztow; i++, wspolrzedna2++) {
                        tablicaWsporzednych[i][0] = wspolrzedna1;
                        tablicaWsporzednych[i][1] = wspolrzedna2;
                    }
                    umiescStatek(ilosc_masztow, 0, tablicaWsporzednych, tablicaTafien);
                } else {
                    System.out.println("Wprowadź ponownie współrzędne");
                    System.out.println();
                    wskazStatek(gracz, ilosc_masztow, tablicaWsporzednych, tablicaTafien);
                }

            } else if (poziomo == true) {
                //Sprawdzam, czy na wskazanej pozycji jest fragment innego statku
                //Jeśli nie to dodaje do tablicy wskazaną współrzędną
                if (tablicaTafien[wspolrzedna2 - 1][wspolrzedna1 - 1] == '0') {
                    tablicaWsporzednych[0][0] = wspolrzedna1;
                    tablicaWsporzednych[0][1] = wspolrzedna2;
                    System.out.println("W którym kierunku mam umieścić statek? (wpisz 2 (lewo) lub 3 (prawo))");
                    odpowiedz2 = daneWejsciowe.nextInt();

                    if (odpowiedz2==2) {
                        ustaw = sprawdzajWspolrzedneLewo(ilosc_masztow, wspolrzedna1, wspolrzedna2, tablicaTafien);
                        if (ustaw == true) {
                            for (int i = ilosc_masztow; i > 0; i--, wspolrzedna1--) {
                                tablicaWsporzednych[i][0] = wspolrzedna1;
                                tablicaWsporzednych[i][1] = wspolrzedna2;
                            }
                            umiescStatek(ilosc_masztow, 1, tablicaWsporzednych, tablicaTafien);
                        } else {
                            System.out.println("Wprowadź ponownie współrzędne");
                            System.out.println();
                            wskazStatek(gracz, ilosc_masztow, tablicaWsporzednych, tablicaTafien);
                        }
                    } else if (odpowiedz2==3) {
                        ustaw = sprawdzajWspolrzednePrawo(ilosc_masztow, wspolrzedna1, wspolrzedna2, tablicaTafien);
                        if (ustaw == true) {
                            for (int i = 0; i < ilosc_masztow; i++, wspolrzedna1++) {
                                tablicaWsporzednych[i][0] = wspolrzedna1;
                                tablicaWsporzednych[i][1] = wspolrzedna2;
                            }
                            umiescStatek(ilosc_masztow, 1, tablicaWsporzednych, tablicaTafien);
                        } else {
                            System.out.println("Wprowadź ponownie współrzędne");
                            System.out.println();
                            wskazStatek(gracz, ilosc_masztow, tablicaWsporzednych, tablicaTafien);
                        }
                    }
                } else {
                    System.out.println("Nie możesz tutaj postawić statku!");
                    wskazStatek(gracz, ilosc_masztow, tablicaWsporzednych, tablicaTafien);
                }
            }
        }
    }

    public static boolean strzelaj(char[][] tablicaStrzalowGracz, char[][] tablicaTrafien_Gracz) {
        int wspolrzedna1, wspolrzedna2;
        Scanner strzal = new Scanner(System.in);
        System.out.println("Podaj pierwszą współrzędną do strzału (rząd)");
        wspolrzedna1 = strzal.nextInt();
        System.out.println("Podaj drugą współrzędną do strzału (kolumna)");
        wspolrzedna2 = strzal.nextInt();

        if (tablicaTrafien_Gracz[wspolrzedna2 - 1][wspolrzedna1 - 1] == '3') {
            System.out.println("Trafiłeś statek zlokalizowany na (" + wspolrzedna1 + "," + wspolrzedna2 + ")" + "\n");
            tablicaStrzalowGracz[wspolrzedna2 - 1][wspolrzedna1 - 1] = '2';
            tablicaTrafien_Gracz[wspolrzedna2 - 1][wspolrzedna1 - 1] = '2';
            return true;
        } else if (tablicaTrafien_Gracz[wspolrzedna2 - 1][wspolrzedna1 - 1] == '1' || tablicaTrafien_Gracz[wspolrzedna2 - 1][wspolrzedna1 - 1] == '0') {
            System.out.println("Pudło! Teraz kolej na twojego przeciwnika...");
            tablicaStrzalowGracz[wspolrzedna2 - 1][wspolrzedna1 - 1] = '1';
            return false;
        }
        return false;
    }

}



