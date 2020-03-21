package TPO2;

import javafx.util.Pair;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Service {

    private String nazwaKraju;

    public Service(String string) {

            this.nazwaKraju = string;
    }

    public Pair<String, Integer> getHttpResponse(String requestUrl) throws IOException {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
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

    public String getWeather(String nazwaMiasta) throws CityNotFoundException{
        try {
            String url_addres = "http://api.openweathermap.org/data/2.5/weather";
            String api_key = "accd201c204ec0acc1f89ecd91e01538";
            String request_url = url_addres + "?q=" + nazwaMiasta + "&appid=" + api_key;
            Pair<String, Integer> response = getHttpResponse(request_url);
            String responseString = response.getKey();
            Integer returnCode = response.getValue();

            if (returnCode == 404 && responseString.contains("city not found")){
                throw new CityNotFoundException();
            } else if (returnCode > 206 || returnCode < 200 ){
                throw new RuntimeException("Bad server response:\n" + responseString);
            }

            return responseString;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    public Double getRateFor(String usd) {
        return null;
    }

    public Double getNBPRate() {
        return null;

    }
}


