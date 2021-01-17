package ro.mta.se.lab.controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import org.json.JSONArray;
import org.json.JSONObject;
import ro.mta.se.lab.model.Logger;
import ro.mta.se.lab.model.Variables;

import java.io.IOException;
import java.util.ArrayList;
/**
 * This class is used for the interface
 * * */
public class WeatherController {
    @FXML
    private ChoiceBox<String> country_box;
    @FXML
    private ChoiceBox<String> city_box;
    @FXML
    private Label temperature;
    @FXML
    private Label weather;
    @FXML
    private Label humidity;
    @FXML
    private Label wind_speed;
    @FXML
    private Label pressure;
    @FXML
    private Label feels_like;
    @FXML
    private Label here;
    public void set_labels()
    {
        temperature.setText("Temperature:");
        weather.setText("Weather");
        humidity.setText("Humidity:");
        wind_speed.setText("Wind Speed:");
        pressure.setText("Pressure:");
        here.setText("City,Country");
    }
    public void clear()
    {
        temperature.setText("");
        weather.setText("");
        humidity.setText("");
        wind_speed.setText("");
        pressure.setText("");
        feels_like.setText("");
        here.setText("");
    }
    @FXML
    private void initialize(){
        Server S=Server.getInstance();
        Logger logger=Logger.getInstance();
       ArrayList<String> states=new ArrayList<>();
       for(int i = 0; i<Variables.statesList.size(); i++) {
            states.add(Variables.statesList.get(i).getCode());
       }
       country_box.getItems().addAll(states);
       country_box.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        clear();
                        city_box.getItems().clear();
                        String state = country_box.getValue();
                        ArrayList<String> cities = new ArrayList<>();
                        for (int i = 0; i < Variables.statesList.size(); i++) {
                            if (Variables.statesList.get(i).getCode().equals(state)) {
                                for (int j = 0; j < Variables.statesList.get(i).getCities().size(); j++) {
                                    cities.add(Variables.statesList.get(i).getCities().get(j).getName());
                                }
                                break;
                            }
                        }
                        city_box.getItems().addAll(cities);
                        city_box.getSelectionModel().selectedItemProperty()
                                .addListener(new ChangeListener<String>() {
                                    public void changed(ObservableValue<? extends String> observable,
                                                        String oldValue, String newValue) {
                                        if (city_box.getValue() != null) {
                                            try {
                                                logger.log_choice(country_box.getValue(), city_box.getValue());
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            set_labels();
                                            String loc = city_box.getValue() + ", " + country_box.getValue();
                                            here.setText(loc);
                                            JSONObject jsonObject = S.read_from_server(city_box.getValue());
                                            String temperature_val= String.format("%.1f",(jsonObject.getJSONObject("main").getDouble("temp") - 272.15));
                                            String feels_like_val=String.format("%.1f",(jsonObject.getJSONObject("main").getDouble("feels_like") - 272.15));
                                            String humidity_val=String.format("%.1f",(jsonObject.getJSONObject("main").getDouble("humidity")));
                                            String wind_speed_val=String.format("%.1f km/h",(jsonObject.getJSONObject("wind").getDouble("speed") * 3.6));
                                            String pressure_val=String.format("%.1f Pa",(jsonObject.getJSONObject("main").getDouble("pressure")));
                                            String weather_val=jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
                                            temperature.setText(temperature_val+ " °C");
                                            feels_like.setText(feels_like_val+ " °C");
                                            humidity.setText(humidity_val + "%");
                                            wind_speed.setText(wind_speed_val);
                                            pressure.setText(pressure_val);
                                            weather.setText(weather_val);
                                            logger.log_information(temperature_val,feels_like_val,humidity_val,wind_speed_val,pressure_val,weather_val);
                                        }
                                    }
                                });
                    }
                });
   }
   public void write_city(ActionEvent event){
        //clear();
        city_box.getItems().clear();
        String state = country_box.getValue();
        ArrayList<String> cities = new ArrayList<>();
        for(int i=0;i<Variables.statesList.size();i++) {
            if(Variables.statesList.get(i).getCode().equals(state)){
                for(int j=0;j<Variables.statesList.get(i).getCities().size();j++)
                {
                    cities.add(Variables.statesList.get(i).getCities().get(j).getName());
                }
                break;
            }
        }
        city_box.getItems().addAll(cities);
   }
   public void change_data(ActionEvent event){
       if(city_box.getValue() != null) {
           Server S=new Server();
           JSONObject jsonObject = S.read_from_server(city_box.getValue());
           temperature.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("temp") - 272.15)) + " \u2103");
           humidity.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("humidity"))) + "%");
           wind_speed.setText(String.format("%.1f Km/H", (jsonObject.getJSONObject("wind").getDouble("speed") * 3.6)));
           pressure.setText(String.format("%.1f hPa", (jsonObject.getJSONObject("main").getDouble("pressure"))));
           weather.setText(jsonObject.getJSONArray("weather").getJSONObject(0).getString("main"));
       }
   }
}