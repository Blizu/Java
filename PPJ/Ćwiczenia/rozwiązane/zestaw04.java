public class S16710_zestaw04 {

    private static int[] tab2;

    public static void main(String args[]) {

        cwiczenie_01();
        cwiczenie_02();
        cwiczenie_03();
        cwiczenie_04();
        cwiczenie_05();
        cwiczenie_06();
        cwiczenie_07();
    }

    private static void cwiczenie_01() {
        int[] tab1 = new int [10];
    }
    private static void cwiczenie_02() {
        System.out.println("Cwiczenie 2:");
        tab2 = new int [10];
        for (int i=0; i<tab2.length; i++){
            tab2[i]= (int) (Math.random() * 2);
            System.out.println(tab2[i]);
        }
        System.out.println();
    }
    private static void cwiczenie_03() {
        int licznik=0;
        System.out.println("Cwiczenie 3:");
        for (int i=0; i<tab2.length; i++){
            if (tab2[i]==1)
                licznik++;
        }
        System.out.println("Ilość 0: "+(tab2.length-licznik)+"\n"+"Ilość 1: "+licznik+"\n");

    }
    private static void cwiczenie_04() {
        System.out.println("Cwiczenie 4:");
        double [] tab4 = new double[10];
        for (int i =0; i<tab4.length; i++){
            tab4[i]=0;
            System.out.println(tab4[i]);
        }
        System.out.println();

        for (int i =0; i<tab4.length; i++){
            tab4[i]=Math.random()*10;
            System.out.println(tab4[i]);
        }
        System.out.println();

        for (int i=0; i<tab4.length; i+=2){

            System.out.println("tab4 ["+i+"]= "+tab4[i]);
        }
        System.out.println();

        for (int i =0; i<tab4.length; i++){

            if((int)(tab4[i])%2 !=0){
                System.out.println("tab4["+i+"]= "+(int)(tab4[i]));
            }

        }
        System.out.println();
    }
    private static void cwiczenie_05() {
        //Komenda nie wypisze żadnej wartości, ponieważ tablica nie jest prawidłowo zainicjonowana.
        // Jeśli utworzymy poprawnie obiekt, to na konsoli powinien się pojawić adres pod jakim znajduje się tablica w pamięci.
    }
    private static void cwiczenie_06() {
        System.out.println("Cwiczenie 6: ");
        int tab [] = { 789 , 678 , 567} ;

        for (int i = 0 ; i < tab.length ; i++) { //Najpierw uruchamia się pętla zewnętrzna i rusza w niej interacja. Następnie pętla zewnętrzna przechodzi do pętli wewnętrznej

            System.out.println("Pętla zewnętrzna ");

            for (int j = i ; j < tab.length ; j++) { //Pętla wykonuje wszystkie swoje interacje i po ich zakończeniu znowu przechodzi do pętli zewnętrznej.

                System.out.println(" Pętla wewnętrzna ");

                System.out.println(" "+" "+tab[i]+" - "+tab[j]+" = "+(tab[i]-tab[j]));
            }
        }
        System.out.println();
    }
    private static void cwiczenie_07() {
        System.out.println("Cwiczenie 7:");
        String [ ] slowa = {"Ala " , " kota " , "ma" , "ma" , "a" , " kot " , " Ale "};
        System.out.println(slowa[0]+slowa[2]+slowa[1]+slowa[4]+slowa[5]+slowa[3]+slowa[6]);
    }
}
