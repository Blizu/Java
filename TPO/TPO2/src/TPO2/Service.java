package TPO2;

import javafx.util.Pair;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {

    private String nazwaKraju;
    private String currency;

    public Service(String string) {
            this.nazwaKraju = string;
            this.currency = getCurrencyForCountry(nazwaKraju);
    }

    public String getCurrencyForCountry(String nazwaKraju){

        String urlAddressCountryCode = "https://supply-xml.booking.com/hotels/xml/countries";


        try {
            Pair<String, Integer> responseCountryCode = getHttpResponse(urlAddressCountryCode);
            String responseStringA = responseCountryCode.getKey();
            Integer returnCodeA = responseCountryCode.getValue();

            checkReturnCode(returnCodeA, responseStringA);

            Pattern p = Pattern.compile("countrycode=\"(\\w+)\" name=\"" + this.nazwaKraju + "\"\\/>");
            Matcher m = p.matcher(responseStringA);
            boolean b = m.find();
            String valueofCountryCode = m.group(1).toLowerCase();

            String urlAddressCountryCurrencies="https://supply-xml.booking.com/hotels/xml/countrycurrencies";
            Pair<String, Integer> responseCurrency = getHttpResponse(urlAddressCountryCurrencies);
            String responseStringB = responseCurrency.getKey();

            Integer returnCodeB = responseCurrency.getValue();
            checkReturnCode(returnCodeB, responseStringB);

            Pattern p2 = Pattern.compile("countrycode=\""+valueofCountryCode+"\" currencycode=\"(\\w+)\"");
            Matcher m2 = p2.matcher(responseStringB);
            boolean b2 = m2.find();

            return m2.group(1);


        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Pair<String, Integer> getHttpResponse(String requestUrl) throws IOException {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);

            int returnCode = con.getResponseCode();
            BufferedReader br;
            if (returnCode <= 206 && returnCode >= 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), StandardCharsets.UTF_8));
            }

            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return new Pair<>(response.toString(), returnCode);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void checkReturnCode (Integer returnCode, String message){
        if (returnCode>206  || returnCode < 200 ){
            throw new RuntimeException("Bad server response:\n" + message);
        }
    }

    public String getWeather(String nazwaMiasta) throws CityNotFoundException{
        try {
            String urlAddres = "http://api.openweathermap.org/data/2.5/weather";
            String apiKey = "accd201c204ec0acc1f89ecd91e01538";
            String requestUrl = urlAddres + "?q=" + nazwaMiasta + "&appid=" + apiKey;
            Pair<String, Integer> response = getHttpResponse(requestUrl);
            String responseString = response.getKey();
            Integer returnCode = response.getValue();

            if (returnCode == 404 && responseString.contains("city not found")){
                throw new CityNotFoundException();
            } else {
                checkReturnCode(returnCode, responseString);
            }

            return responseString;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public Double getRateFor(String base) {
        String requestUrl = "https://api.exchangeratesapi.io/latest?base=" + base + "&symbols=" + this.currency;
        try {
            Pair<String, Integer> response = getHttpResponse(requestUrl);
            String responseString = response.getKey();
            Integer returnCode = response.getValue();

            if (returnCode == 400 && responseString.contains(base)){
                throw new CurrencyNotFoundException();
            } else {
                checkReturnCode(returnCode, responseString);
            }

            Pattern p = Pattern.compile(".*" + this.currency + "\\\":([0-9]+\\.[0-9]+)}.*");
            Matcher m = p.matcher(responseString);
            boolean b = m.matches();
            String value = m.group(1);

            return new Double(value);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double getNBPRate() {
        if (this.currency.equals("PLN")){
            return 1.0;
        }

        String requestUrlTabA = "http://www.nbp.pl/kursy/kursya.html";
        String requestUrlTabB = "http://www.nbp.pl/kursy/kursyb.html";
        try {
            Pair<String, Integer> responseA = getHttpResponse(requestUrlTabA);
            Pair<String, Integer> responseB = getHttpResponse(requestUrlTabB);
            String responseStringA = responseA.getKey();
            String responseStringB = responseB.getKey();
            Integer returnCodeA = responseA.getValue();
            Integer returnCodeB = responseB.getValue();
            String responseString = responseStringA + responseStringB;

            checkReturnCode(returnCodeA, responseStringA);
            checkReturnCode(returnCodeB, responseStringB);

            Pattern p = Pattern.compile("\">([0-9]+) " + this.currency + "<\\/td><td class=\"bgt.{0,2} right\">([0-9,]+)");
            Matcher m = p.matcher(responseString);
            boolean b = m.find();

            String countStr = m.group(1);
            String valueStr = m.group(2).replace(',', '.');
            Double value = new Double(valueStr);
            Integer count = new Integer(countStr);

            return value / count;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}


