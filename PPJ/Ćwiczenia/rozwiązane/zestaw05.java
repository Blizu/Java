import java.util.Arrays;

public class S16710_zestaw05 {

    public static void main(String args[]) {

        cwiczenie_01();
        cwiczenie_02();
        cwiczenie_03();
        cwiczenie_04();
    }

    private static int losuj(int min_range, int max_range) {
        int wynikowa;
        double losowana;
        losowana = (Math.random() * (max_range - min_range)) + min_range;
        wynikowa = (int) losowana;

        return wynikowa;
    }

    private static void cwiczenie_01() {
        System.out.println("Cwiczenie 1:" + "\n" + "Tablica pierwotna:");
        char[] pierwotna = new char[(90 - 65)];
        for (int i = 0; i < pierwotna.length; i++) {
            pierwotna[i] = (char) losuj(65, 90);
            System.out.print(pierwotna[i] + ",");
        }
        System.out.println();
        char[] lustrzana = new char[pierwotna.length];
        System.out.println("Tablica lustrzana:");
        for (int i = 0; i < lustrzana.length; i++) {
            lustrzana[i] = pierwotna[i];
            System.out.print(lustrzana[i] + ",");
        }
        System.out.println();
    }

    private static void cwiczenie_02() {
        System.out.println("Cwiczenie 2: ");
        char[] tab = {'a', 'b', 'c', 'e', 'f'};
        for (int i = 0; i < tab.length; i++) {
            if (i == 0) {
                System.out.println("Sąsiedzi dla " + tab[i] + " to: " + tab[tab.length - 1] + " " + tab[i + 1]);
            } else if (i == (tab.length - 1)) {
                System.out.println("Sąsiedzi dla " + tab[i] + " to: " + tab[i - 1] + " " + tab[i - i]);
            } else
                System.out.println("Sąsiedzi dla " + tab[i] + " to: " + tab[i - 1] + " " + tab[i + 1]);
        }
        System.out.println();
    }

    private static void cwiczenie_03() {
        System.out.println("Cwiczenie 3: ");
        int[] arr1 = new int[10];
        int[] arr2 = new int[arr1.length];
        System.out.print("Arr1: ");
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = losuj(-10, 10);
            System.out.print(arr1[i] + ",");
        }
        System.out.println();
        System.out.print("Arr2: ");
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = losuj(-10, 10);
            System.out.print(arr2[i] + ",");
        }
        System.out.println();
        int[][] suma = new int[2][10];
        System.out.println();
        System.out.println("Suma elementów tablic");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0) {
                    suma[i][j] = arr1[j];
                    System.out.print(suma[i][j] + " ");
                } else {
                    suma[i][j] = arr2[j];
                    System.out.print(suma[i][j] + " ");
                }
            }
        }
        System.out.println();


        System.out.print("Te same elementy:");
        int licznik=0;
        for (int i=0; i<arr1.length; i++){
            for(int j=0; j< arr1.length; j++)
                if (arr1[i] == arr2 [j] ){
                    int [] wspolna = new int [arr1.length];
                        wspolna[j]=arr2[j];
                        System.out.print(wspolna[j]+",");
                }
        }
        System.out.println( );
    }
    private static void cwiczenie_04(){
        System.out.println("Cwiczenie 4: ");

        double [] tab_double = new double[5];

        for(int i=0; i<tab_double.length; i++){
            tab_double[i]=(Math.random()*10)+5;
            System.out.println(tab_double[i]);
        }
        System.out.println();

        int [] tab_int = new int[5];

        for(int i=0; i<tab_int.length; i++){
            tab_int[i]=(int)(Math.random()*10)+5;
            System.out.println(tab_int[i]);
        }

        System.out.println();
        int [] wynikowa = new int [tab_double.length];

        for(int i=0; i<tab_double.length; i++){
            wynikowa[i]=tab_int[i]+(int)tab_double[i];
            System.out.println(wynikowa[i]);
        }
        System.out.println();
        System.out.println("Posortowane:");
        Arrays.sort(wynikowa);
        for (int i=0; i<wynikowa.length; i++){
            System.out.println(wynikowa[i]);
        }
    }
 }


