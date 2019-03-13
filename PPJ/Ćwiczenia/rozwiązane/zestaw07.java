public class S16710_zestaw07 {
    public static void main(String args[]) {

        cwiczenie_1();
        cwiczenie_2();
        cwiczenie_3();
        cwiczenie_4();

    }

    private static void cwiczenie_1() {
        System.out.println("Zadanie 1");
        int wrt = 5;
        modifyValue(5);
        System.out.println();
    }

    private static void cwiczenie_2() {
        System.out.println("Zadanie 2");
        char[] zdanie = "Ala ma kota".toCharArray();
        sprawdz(zdanie,'a');
    }

    private static void cwiczenie_3(){
        System.out.println("Zadanie 3");
        int[] tab1 = new int [(int)(Math.random()*10)+1];
        int[] tab2 = new int [(int)(Math.random()*10)+1];
        System.out.print("tab1:");
        for(int i=0; i< tab1.length; i++){
            tab1[i]=(int)(Math.random()*10)+1;
            System.out.print(tab1[i]+",");
        }
        System.out.println();
        System.out.print("tab2:");
        for(int i=0; i< tab2.length; i++){
            tab2[i]=(int)(Math.random()*10)+1;
            System.out.print(tab2[i]+",");
        }
        System.out.println();
        zadanie3(tab1,tab2,1);
        System.out.println();
    }

    private static void cwiczenie_4(){
        System.out.println("Zadanie 4");
        char[] slowo = "kajak".toCharArray();
        for (int i=0; i<slowo.length; i++){
            System.out.println(slowo[i]);
        }
        palindrom(slowo,0,4);
        //i - wartość początkowa
        //j - długość słowa
    }

    private static void modifyValue(int liczba) {
        System.out.println("Podany argumenty to: " + liczba);
        int iloczyn = liczba * 5;
        System.out.println("Zmodyfikowana wartość: " + iloczyn);
    }

    private static void sprawdz(char[] tablica, char znak) {
        int licznik = 0;
        for (int i = 0; i < tablica.length; i++) {
            if (tablica[i] == znak) {
                licznik++;
            }
        }
        System.out.println("Ilość "+znak+" w tablicy: " + licznik);
    }
    private static void zadanie3(int[] tab1, int[] tab2, int var) {
        int wieksza;
        int mniejsza;
        int[] tab_wieksza;

        if (var < 0 && tab1.length != tab2.length) {
            if(tab1.length>tab2.length) {
                wieksza = tab1.length;
                mniejsza = tab2.length;
            }
            else {
                wieksza = tab2.length;
                mniejsza = tab1.length;
            }
            int[] wynikowa = new int[wieksza];
                System.out.print("wynikowa:");
                for (int i = 0; i <wynikowa.length-(wieksza-mniejsza); i++) {
                    wynikowa[i] = tab1[i] + tab2[i];
                    System.out.print(wynikowa[i] + ",");
                }
            } else if (var > 0 && tab1.length != tab2.length) {
            if(tab1.length>tab2.length) {
                wieksza = tab1.length;
                mniejsza = tab2.length;
                tab_wieksza=tab1;
            }
            else {
                wieksza = tab2.length;
                mniejsza = tab1.length;
                tab_wieksza=tab2;
            }
            int[] wynikowa = new int[wieksza];
            System.out.print("wynikowa:");
            for (int i=mniejsza ; i <wynikowa.length; i++) {
                System.out.print(tab_wieksza[i] + ",");
            }

            } else if (tab1.length == tab2.length) {
                System.out.println("Tablice o tych samych długościach!");
            }
        }
		
        private static void palindrom(char[] tab, int i, int j){
            if (i < j)
            {
                if (tab[i] == tab[j])
                    palindrom(tab, i + 1, j - 1);
                else
                    System.out.println("Wyraz nie jest palindromem");
            }
            else System.out.println("Wyraz jest palindromem");
        }
    }


