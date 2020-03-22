package TPO2;

/**
 *
 *  @author Bliżewski Piotr S16710
 *
 */




public class Main {
    public static void main(String[] args) {
        Service s = new Service("Uganda");
        String weatherJson = s.getWeather("Kampala");
        System.out.println(weatherJson);
        Double rate1 = s.getRateFor("EUR");
        System.out.println(rate1);
        Double rate2 = s.getNBPRate();
        System.out.println(rate2);



        // ...
        // część uruchamiająca GUI
    }
}
