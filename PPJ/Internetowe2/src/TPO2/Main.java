/**
 *
 *  @author Bliżewski Piotr S16710
 *
 */

package TPO2;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;


import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.Currency;
import java.util.Optional;

import static javafx.geometry.Orientation.VERTICAL;


public class Main extends Application {


    private static String weatherJson;
    private static Double rate1;
    private static Double rate2;
    private static String wikipedia;
    private static String country, city, baseCurrency;


    public static void main(String[] args) {

        country="Canada";
        city="Toronto";
        baseCurrency="USD";

        Service s = new Service(country);
        weatherJson = s.getWeather(city);
        rate1 = s.getRateFor(baseCurrency);
        rate2 = s.getNBPRate();
        wikipedia = s.getDescriptionFromWikipedia("Warsaw");

        System.out.println(weatherJson);
        System.out.println(rate1);
        System.out.println(rate2);
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

//================= pierwszy Anchor

        AnchorPane menu = new AnchorPane();
        menu.setPrefSize(1280,280);

        Label weatherLabel = new Label();
        weatherLabel.setLayoutX(100);
        weatherLabel.setText("Weather for "+ city );

        TextArea weather = new TextArea();
        weather.setPrefSize(280,157);
        weather.setLayoutX(35);
        weather.setLayoutY(23);
        weather.setText(formatWeather(weatherJson));
        weather.setEditable(false);

        Label currencyRateLabel = new Label();
        currencyRateLabel.setLayoutX(450);
        currencyRateLabel.setText("Currency Rate");

        TextArea currencyRate = new TextArea();
        currencyRate.setPrefSize(280,125);
        currencyRate.setLayoutX(350);
        currencyRate.setLayoutY(23);
        currencyRate.setText(rate1.toString());
        currencyRate.setEditable(false);

        Label NBPRateLabel = new Label();
        NBPRateLabel.setLayoutX(750);
        NBPRateLabel.setText("PLN Rate");

        TextArea NBPRate = new TextArea();
        NBPRate.setPrefSize(280,125);
        NBPRate.setLayoutX(650);
        NBPRate.setLayoutY(23);
        NBPRate.setText(rate2.toString());
        NBPRate.setEditable(false);

        Button changeData = new Button("Change Data");
        changeData.setLayoutX(1002);
        changeData.setLayoutY(55);
        changeData.setPrefSize(222,66);

        TextInputDialog dialogCountry = new TextInputDialog("Poland");
        dialogCountry.setTitle("Change Data Dialog");
        dialogCountry.setContentText("Please enter country:");


        TextInputDialog dialogCity = new TextInputDialog("Warsaw");
        dialogCity.setTitle("Change Data Dialog");
        dialogCity.setContentText("Please enter city:");

        TextInputDialog dialogbaseCurrency = new TextInputDialog("USD");
        dialogbaseCurrency.setTitle("Change Data Dialog");
        dialogbaseCurrency.setContentText("Please enter base currency:");

        changeData.setOnAction((event) ->
        {

            Optional<String> result = dialogCountry.showAndWait();
            result.ifPresent(country2 -> System.out.println(country2));

            Optional<String> result2 = dialogCity.showAndWait();
            result2.ifPresent(city2 -> city=city2);

            Optional<String> result3 = dialogbaseCurrency.showAndWait();
            result3.ifPresent(currency2 -> baseCurrency=currency2);

        });

        menu.getChildren().add(changeData);
        menu.getChildren().add(weather);
        menu.getChildren().add(currencyRate);
        menu.getChildren().add(NBPRate);
        menu.getChildren().add(weatherLabel);
        menu.getChildren().add(currencyRateLabel);
        menu.getChildren().add(NBPRateLabel);


//================= drugi Anchor

        AnchorPane www = new AnchorPane();
        www.setPrefWidth(1275);
        www.setPrefHeight(478);
        WebView browser = new WebView();
        browser.setPrefWidth(1285);

        WebEngine engine = browser.getEngine();
        www.getChildren().addAll(browser);
        engine.load(wikipedia);

        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(VERTICAL);
        splitPane.setDividerPositions(0.3);

        splitPane.getItems().addAll(menu,www);


        SplitPane.Divider divider = splitPane.getDividers().get(0);
        divider.positionProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldvalue, Number newvalue )
            {
                divider.setPosition(0.275);
            }
        });

// =============== PrimaryStage i scena

        Scene scena = new Scene(splitPane,1280,768);


        primaryStage.setTitle("Aplikacja TPO2");
        primaryStage.setScene(scena);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public String formatWeather(String rawText) {
        String result = "";

        try
        {
            JSONObject jsonObj = new JSONObject(rawText);

            if (!jsonObj.isNull("main"))
            {
                JSONObject section = jsonObj.getJSONObject("main");

                result += "Temperature: " + kelvinToCelcius(section.get("temp")) + " celcius \n";
                result += "Feels like: " + kelvinToCelcius(section.get("feels_like")) + " celcius \n";
                result += "Minimum temperature: " + kelvinToCelcius(section.get("temp_min")) + " celcius \n";
                result += "Maximum temperature: " + kelvinToCelcius(section.get("temp_max")) + " celcius \n";
                result += "Pressure: " + section.get("pressure") + " hPa \n";
                result += "Humidity: " + section.get("humidity") + " %\n";

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private String kelvinToCelcius(Object object) {
        float kelvin = Float.parseFloat(object.toString());
        float celcius = kelvin - 273.15F;
        return String.format("%.2f",celcius);
    }
}