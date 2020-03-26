/**
 *
 *  @author Bliżewski Piotr S16710
 *
 */

package TPO2;


import javafx.application.Application;
import javafx.stage.Stage;
import org.json.JSONObject;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.security.auth.login.Configuration;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;


public class Main extends Application {

    private static String wikipedia;
    private static String weatherJson;
    private static Double rate1;
    private static Double rate2;

    public static void main(String[] args) {



        Service s = new Service("Canada");
        weatherJson = s.getWeather("Toronto");
        rate1 = s.getRateFor("USD");
        rate2 = s.getNBPRate();
        wikipedia = s.getDescriptionFromWikipedia("Warsaw");


        System.out.println(weatherJson);
        System.out.println(rate1);
        System.out.println(rate2);
       //launch(args);
    }

    private Button Pogoda, Kurs_Waluty, Kurs_NBP, Opis, Exit;
    private TextArea Wyszukiwarka;
    private Scene Scena;
    private Pane kontener;
    private WebView browser;
    private WebEngine engine;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scena(primaryStage);
        primaryStage.setTitle("Pogoda");
        primaryStage.setScene(Scena);
        primaryStage.show();
    }

    private void Scena (Stage primaryStage){

        Pogoda = new Button("Pokaż pogodę");
        Pogoda.setOnAction((event) ->{
            if(weatherJson != null){
                show_it(formatWeather(weatherJson));
            }
        });

        Kurs_Waluty = new Button("Pokaż kurs waluty");
        Kurs_Waluty.setOnAction((event ->{
            if(rate1 != null){
                show_it("Kurs Waluty:\n" + rate1);
            }
        }));

        Kurs_NBP = new Button("Pokaż kurs NBP");
        Kurs_NBP.setOnAction((event ->{
            if(rate2 != null){
                show_it("Kurs NBP: \n" + rate2);
            }
        }));

        Opis = new Button("Opis miasta");
        Opis.setOnAction((event ->{
            if(wikipedia != null){
                engine.load(wikipedia);
            }
        }));

        Exit = new Button("Wyjście");
        Exit.setOnAction((event ->{
            System.exit(0);
            System.out.println("Poprawnie zamknięto program");
        }));

        Wyszukiwarka = new TextArea();

        kontener = new Pane();
        kontener.setPadding(new Insets(20, 20, 20, 20));

        Pogoda.setLayoutX(20);
        Pogoda.setLayoutY(20);
        Pogoda.setMinSize(160, 50);

        Kurs_Waluty.setLayoutX(180);
        Kurs_Waluty.setLayoutY(20);
        Kurs_Waluty.setMinSize(160, 50);

        Kurs_NBP.setLayoutX(340);
        Kurs_NBP.setLayoutY(20);
        Kurs_NBP.setMinSize(160, 50);

        Opis.setLayoutX(500);
        Opis.setLayoutY(20);
        Opis.setMinSize(160, 50);

        Exit.setLayoutX(660);
        Exit.setLayoutY(20);
        Exit.setMinSize(160, 50);

        Wyszukiwarka.setLayoutX(20);
        Wyszukiwarka.setLayoutY(90);
        Wyszukiwarka.setMinSize(800, 10);

        kontener.getChildren().add(Pogoda);
        kontener.getChildren().add(Kurs_Waluty);
        kontener.getChildren().add(Kurs_NBP);
        kontener.getChildren().add(Opis);
        kontener.getChildren().add(Exit);
        kontener.getChildren().add(Wyszukiwarka);

        browser = new WebView();
        engine = browser.getEngine();

        kontener.getChildren().add(browser);
        browser.setLayoutX(20);
        browser.setLayoutY(290);

        Scena = new Scene(kontener, 840, 910);

    }

    public void show_it(String text){
        Wyszukiwarka.clear();
        Wyszukiwarka.appendText(text);
    }

    public String formatWeather(String raw_text){
        String formatted_text ="";

        try {
            JSONObject obj = new JSONObject(raw_text);

            if(!obj.isNull("main")){
                formatted_text += "Miasto: " + obj.getString("name") +"\n";
                formatted_text += "Kraj: " + obj.getJSONObject("sys").get("country") + "\n";
                formatted_text += "Temperatura: " + obj.getJSONObject("main").get("temp") + "\n";
                formatted_text += "Temperatura min: " + obj.getJSONObject("main").get("temp_min") + "\n";
                formatted_text += "Temperatura max: " + obj.getJSONObject("main").get("temp_max") + "\n";
                formatted_text += "Ciśnienie: " + obj.getJSONObject("main").get("pressure") + "\n";
                formatted_text += "Wilgotność: " + obj.getJSONObject("main").get("humidity") + "\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return formatted_text;
    }

}