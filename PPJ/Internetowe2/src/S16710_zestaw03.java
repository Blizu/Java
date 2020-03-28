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

    public static void cwiczenie_01(){

        boolean byte1 = true; //1 bajt
        boolean byte2 = false;
        short short_min=Short.MIN_VALUE; //2bajty
        short short_max=Short.MAX_VALUE;
        int int_min= Integer.MIN_VALUE; //4bajty
        int int_max=Integer.MAX_VALUE;
        String int_byte1=Integer.toBinaryString(int_min);
        String int_byte2=Integer.toBinaryString(int_max);
        String int_oct1=Integer.toOctalString(int_min);
        String int_oct2=Integer.toOctalString(int_max);
        String int_hex1=Integer.toHexString(int_min);
        String int_hex2=Integer.toHexString(int_max);
        long long_min=Long.MIN_VALUE; //8 bajtów
        long long_max=Long.MAX_VALUE;
        String long_byte1=Long.toBinaryString(long_min);
        String long_byte2=Long.toBinaryString(long_max);
        String long_oct1=Long.toOctalString(long_min);
        String long_oct2=Long.toOctalString(long_max);
        String long_hex1=Long.toHexString(long_min);
        String long_hex2=Long.toHexString(long_max);
        float float_min= Float.MAX_VALUE; //4 bajty
        float float_max= Float.MIN_VALUE;
        String float_hex1= Float.toHexString(float_min);
        String float_hex2= Float.toHexString(float_max);
        double double_min=Double.MIN_VALUE; //8 bjatów
        double double_max=Double.MAX_VALUE;
        String double_hex1=Double.toHexString(double_min);
        String double_hex2=Double.toHexString(double_max);
        char char_min= Character.MIN_VALUE; //2 bajty
        char char_max=Character.MAX_VALUE;

        System.out.println(
         "ćwiczenie 1: "+"\n"+
         byte1+"\n"+byte2+"\n"+
         "Short_min: "+short_min+"\n"+"Short_max: "+short_max+"\n"+
         "Int_min: "+int_min+"\n"+"Int_max: "+int_max+"\n"+"Int_byte_min: "+int_byte1+"\n"+"Int_bye_max: "+int_byte2+"\n"+"Int_oct_min: "+int_oct1+"\n"+"Int_oct_max: "+int_oct2+"\n"+"Int_hex_min: "+int_hex1+"\n"+"Int_hex_max:"+int_hex2+"\n"+
         "Long_min: "+long_min+"\n"+"Long_max: "+long_max+"\n"+"Long_byte_min: "+long_byte1+"\n"+"Long_byte_max: "+long_byte2+"\n"+"Long_oct_min: "+long_oct1+"\n"+"Long_oct_max: "+long_oct2+"\n"+"Long_hex_min: "+long_hex1+"\n"+"Long_hex_max: "+long_hex2+"\n"+
         "Float_min: "+float_min+"\n"+"Float_max: "+float_max+"\n"+"Float_min_hex: "+float_hex1+"\n"+"Float_max_hex:"+float_hex2+"\n"+
         "Double_min:"+double_min+"\n"+"Double_max: "+double_max+"\n"+"Double_min_hex: "+double_hex1+"\n"+"Double_max_hex: "+double_hex2+"\n"+
         "Char_min: "+char_min+"\n"+"Char_max: "+char_max+"\n"
        );
    }

    public static void cwiczenie_02(){
        boolean boolean1 = true;
        int int1=20;
        double double1=21.37;
        char char1=87;

        System.out.println("ćwiczenie 2: "+"\n");
        System.out.println(boolean1==boolean1);
        //System.out.println(boolean1==int1);
        //System.out.println(boolean1==double1);
        //System.out.println(boolean1==char1);
        //System.out.println(int1==boolean1);
        System.out.println(int1==int1);
        System.out.println(int1==double1);
        System.out.println(int1==char1);
        //System.out.println(double1==boolean1);
        System.out.println(double1==int1);
        System.out.println(double1==double1);
        System.out.println(double1==char1);
        //System.out.println(char1=boolean1);
        System.out.println(char1==int1);
        System.out.println(char1==double1);
        System.out.println(char1==char1);

    }

    public static void cwiczenie_03(){

        int a123;
//      int 3a123;
//      int @abc;
//      int public;
//      int static;
//      int void;
//      int null;

    }

    public static void cwiczenie_04(){
        System.out.println("ćwiczenie 4");
        int cwiczenie04 = 5;

        System.out.println(cwiczenie04);
    }

    public static void cwiczenie_05(){
        char charValue= 'a';
        char charvalue = 'b';

        //Program się skompiluje
    }

    public static void cwiczenie_06(){
        System.out.println("ćwiczenie 6");
        int a=21;
        double b=15;
        System.out.println("a="+a+"\n"+"b="+b);
        //a=b;
        b=a;
        System.out.println("a="+a+"\n"+"b="+b);
        System.out.println();

        //nie można konwertować z mniejszego typu (int) na większy (double), dlatego kopilator wyświetla błąd kompilacji

    }

    public static void cwiczenie_07(){
        System.out.println("ćwiczenie 7");
        char a=87;
        int  b=12;
        //char char_wynik= a+b;
        char char_wynik= (char) (a+b);
        int int_wynik=a+b;
        System.out.println(char_wynik);
        System.out.println(int_wynik);
        float c = 2.0f;
        double d = 21.37;
        //float float_wynik=c+d;
        float float_wynik= (float) (c+d);
        System.out.println(float_wynik);
        byte e = 2;
        // byte byte_wynik=b+e;
        byte byte_wynik= (byte) (b+e);
        System.out.println(byte_wynik);
        System.out.println();

    }

}
