package TPO2;

/**
 *
 *  @author Bliżewski Piotr S16710
 *
 */




public class Main {
    public static void main(String[] args) {
        Service s = new Service("Poland");
        String weatherJson = s.getWeather("berlin");
        System.out.println(weatherJson);
        Double rate1 = s.getRateFor("EUR");
        System.out.println(rate1);
        Double rate2 = s.getNBPRate();



        // ...
        // część uruchamiająca GUI
    }
}
