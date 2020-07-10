public class S16710_zestaw02 {

    public static void main(String[] args) {

        cwiczenie01();
        cwiczenie02();
        cwiczenie03();
        cwiczenie04();
    }

    public static void cwiczenie01(){
        System.out.println("Zadanie 1");
        System.out.println("0b10100101 = "+0b10100101);
        System.out.println("0245 = "+ 0245);
        System.out.println("0xa5 = "+ 0xa5 +"\n");
    }

    public static void cwiczenie02(){
        System.out.println("Zadanie 2");
        System.out.println(0b01110>>0);
        System.out.println(0b01110<<2);
        System.out.println((0b00111000>>4)+"\n");

    }

    public static void cwiczenie03(){
        System.out.println("Zadanie 3");

        int n = (int) (Math.random()*(64-255)+255);

        System.out.println("Wylosowana wartość: "+ n +", binarnie: "+Integer.toBinaryString(n));
        byte maska1 = 0b11111;
        byte nWynikowe = (byte) (n & maska1);
        System.out.println("Wartość reprezentowana przez ostatnie 5 bitów = "+ nWynikowe +", binarnie: "+Integer.toBinaryString(nWynikowe));

        byte maska2 = 0b1000000;
        byte nWynikowe2 = (byte)(n&maska2);
        if (nWynikowe2 == maska2)
            System.out.println(1);
        else
            System.out.println(0+"\n");
    }
    public static void cwiczenie04(){
        System.out.println("Zadanie 4");
            int a1 = 0x6D, a2 = (a1 >> 3), a = a1 & a2,
            b1 = 0xA, b2 =0b1111, b = b1 | b2,
            c1 = 11 << 2, c2 = 0b000110, c = c1 ^ c2;

            System.out.println(a+" "+b+" "+c);
    }

}
