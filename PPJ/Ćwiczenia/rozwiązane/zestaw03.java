public class S16710_zestaw03 {

    public static void main(String args[]) {
        cwiczenie_01();
        cwiczenie_02();
        cwiczenie_03();
        cwiczenie_04();
        cwiczenie_05();
        cwiczenie_06();
        cwiczenie_07();
    }

    public static void cwiczenie_01() {
        System.out.println("Zadanie 1"+"\n");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
        System.out.println();
    }

    public static void cwiczenie_02() {
        System.out.println("Zadanie 2"+"\n");
        int s = 0;
        int i = 1;

        while (i <= 10) {
            s = s + 1;
            i++;
            System.out.println(s);
        }
        System.out.println();
    }

    public static void cwiczenie_03() {
        System.out.println("Zadanie 3"+"\n");
        //Pętla while sprawdza warunek przed wykonaniem iteracji, do-while po wykonaniu iteracji.

        // Pętla while sprawdza warunek przed wykonaniem interacji.
        int i = 0;
        while (i <= 5) {
            System.out.println(i);
            i++;
        }
        // Pętla do-while najpierw wykonuje instrukcje, a następnie sprawdza czy po jej wykonaniu warunek jest prawdziwy.
        // Tak jak widzimy na konsoli pętla do-while wykonała się raz, po czym się zatrzymała ponieważ nie został spełniony warunek.
        do { //
            System.out.println("Do while: " + i);
            i++;
        } while (i <= 5);
        System.out.println();
    }

    public static void cwiczenie_04() {
        System.out.println("Zadanie 4"+"\n");
        int it = 0;
        double n = 0;
        while (it <= 10) {
            double wynik = 1 / Math.pow(2, n);
            System.out.println("n" + "(" + it + ")" + " = " + wynik);
            n++;
            it++;
        }
        System.out.println();
    }

    public static void cwiczenie_05() {
        System.out.println("Zadanie 5:"+"\n");

        // an(0)=an(n+1)*wrt ??

        int it = 0;
        double n = 0;
        int wrt=3;
        while (it <= 10) {
            if(wrt!=2) {
                double wynik = (1 / Math.pow(2, n + 1)) * wrt;
                System.out.println("n" + "(" + it + ")" + " = " + wynik);
                n++;
                it++;
            }
        }
        System.out.println();

    }

    public static void cwiczenie_06() {
        System.out.println("Zadanie 6:"+"\n");
        int licznik = 0;
        for (int i = -1500; i <= 1500; i++) {
            if (i % 3 == 0 && i % 2 == 0)
                System.out.println(i);
            licznik++;
        }
        System.out.println("Ilość liczb z przedziału -1500 do 1500 podzielnych jednocześnie przez 3 i 2 bez reszty = " + licznik);
        System.out.println();
    }

    public static void cwiczenie_07() {
        System.out.println("Zadanie 7:"+"\n");
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
           System.out.print("\n");
        }
    }
}
