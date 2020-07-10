public
    class S16710_p02 {
    public static void main (String [] args){

        Owoc [] koszyk = new Owoc[20];
        wypelnijKoszyk(koszyk);
        System.out.println("Pierwotna Tablica");
        wyswietlKoszyk(koszyk);
        Owoc[][][] tablicaWynikowa = mySort(koszyk);
        wyswietlTabliceWynikowa(tablicaWynikowa);
        System.out.println("KONIEC======================");
    }

    public static void wypelnijKoszyk(Owoc[] tablicaOwocow){

        for (int i=0; i<tablicaOwocow.length; i++){
            tablicaOwocow[i]= new Owoc(generatorKalibru(),generatorWagi());
        }
    }

    public static void wyswietlKoszyk(Owoc [] tablicaOwocow){
        for (Owoc o : tablicaOwocow)
            System.out.println(o.toString());
    }
    public static void wyswietlTabliceWynikowa(Owoc[][][] tablicaWynikowa) {
        int k=1;
        for (Owoc[][] array2D : tablicaWynikowa) {
            System.out.println("\nSORTOWANIE " + k + "======================");
            int i=1;
            Owoc[] array1D = array2D[0];
            while(array1D != null){
                System.out.println("===========Iteracja " + i);
                for (Owoc item : array1D) {
                    System.out.println(item.toString());
                }

                i++;
                array1D = array2D[i];
            }
            k++;
        }
    }

    public static int generatorKalibru(){
        int wynik=0;
        wynik = (int)(Math.random()*10);
        return wynik;
    }

    public static double generatorWagi(){
        double waga=0;
        waga = Math.random()*10;
        return waga;
    }

    public static Owoc[][] sortowanieBabelkowe(Owoc [] tablicaOwocow){
        //Zlozonosc pesymistyczna: n^2
        Owoc[][] tablicaPomocnicza = new Owoc[tablicaOwocow.length * tablicaOwocow.length][];

        boolean zamieniony = true;
        int ostatni = tablicaOwocow.length - 2;
        int j = 0;
        while (zamieniony) {
            zamieniony = false;

            for (int i = 0; i <= ostatni; i++) {
                if (tablicaOwocow[i].compareTo(tablicaOwocow[i + 1]) > 0) {   //porównanie obiektów względem określonych wytycznych
                    Owoc temp = tablicaOwocow[i];
                    tablicaOwocow[i] = tablicaOwocow[i + 1];
                    tablicaOwocow[i + 1] = temp;
                    zamieniony = true;
                }
            }
            ostatni--;

            tablicaPomocnicza[j] = kopiujTablice(tablicaOwocow);
            j++;
        }
        return tablicaPomocnicza;
    }

    public static Owoc[][] sortowaniePrzezWybieranie(Owoc [] tablicaOwocow){
        //Zlozonosc pesymistyczna: n^2
        Owoc[][] tablicaPomocnicza = new Owoc[tablicaOwocow.length * tablicaOwocow.length][];

        for (int i = 0; i < tablicaOwocow.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < tablicaOwocow.length; j++){
                if (tablicaOwocow[index].compareTo(tablicaOwocow[j]) > 0){
                    index = j;
                }
            }
            Owoc mniejszyOwoc = tablicaOwocow[index];
            tablicaOwocow[index] = tablicaOwocow[i];
            tablicaOwocow[i] = mniejszyOwoc;

            tablicaPomocnicza[i] = kopiujTablice(tablicaOwocow);
        }
        return tablicaPomocnicza;

    }
    public static Owoc[][] sortowaniePrzezWstawianie(Owoc[] tablicaOwocow){
        int n = tablicaOwocow.length;
        //Zlozonosc pesymistyczna: n^2
        Owoc[][] tablicaPomocnicza = new Owoc[n * n][];

        for (int j = 1; j < n; j++) {
            Owoc klucz = tablicaOwocow[j];
            int i = j-1;
            while ( (i > -1) && ( tablicaOwocow[i].compareTo(klucz) > 0 ) ) {
                tablicaOwocow [i+1] = tablicaOwocow [i];
                i--;
            }
            tablicaOwocow[i+1] = klucz;

            tablicaPomocnicza[j-1] = kopiujTablice(tablicaOwocow);
        }

        return tablicaPomocnicza;
    }

    public static Owoc[] kopiujTablice(Owoc[] dane){
        Owoc[] tmp = new Owoc[dane.length];
        for (int k=0;k<dane.length;k++){
            tmp[k]=dane[k];
        }
        return tmp;
    }

    public static Owoc [][][] mySort(Owoc[] dane){
        // [metoda sortowania][nr stanu(nr iteracji)][wartosci dla danego stanu(tablica)]

        Owoc [][][] tablicaWynikowa = new Owoc[3][][];

        tablicaWynikowa[0] = sortowanieBabelkowe(kopiujTablice(dane));
        tablicaWynikowa[1] = sortowaniePrzezWybieranie(kopiujTablice(dane));
        tablicaWynikowa[2] = sortowaniePrzezWstawianie(kopiujTablice(dane));

        return tablicaWynikowa;
    }
}
    class  Owoc implements Comparable<Owoc>{
        int kaliber;
        double waga;

        public Owoc(int kaliber, double waga) {
            this.kaliber = kaliber;
            this.waga = waga;
        }
        public int returnKaliber(){
            return this.kaliber;
        }
        public double returnWaga(){
            return this.waga;
        }
        public String toString(){
            return "Kaliber: " + kaliber + " Waga: " + waga;
        }

        @Override
        public int compareTo(Owoc owoc) {
            if (this.returnKaliber() > owoc.kaliber)
                return 1;
            else if (this.returnKaliber() == owoc.kaliber){
                if(this.returnWaga() > owoc.waga)
                    return 1;
            }
            return -1;
            }
        }



