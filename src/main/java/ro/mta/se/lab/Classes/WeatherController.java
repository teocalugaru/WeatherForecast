package ro.mta.se.lab.Classes;

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

import java.util.ArrayList;

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
    private Label muie;
    public void set_labels()
    {
        temperature.setText("Temperature:");
        weather.setText("Weather");
        humidity.setText("Humidity:");
        wind_speed.setText("Wind Speed:");
        pressure.setText("Pressure:");
        muie.setText("City,Country");
    }
    public void clear()
    {
        temperature.setText("");
        weather.setText("");
        humidity.setText("");
        wind_speed.setText("");
        pressure.setText("");
        feels_like.setText("");
    }
    @FXML
    private void initialize(){
       ArrayList<String> states=new ArrayList<>();
       for(int i=0;i<Variables.statesList.size();i++) {
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
                                city_box.getSelectionModel().selectedItemProperty()
                                        .addListener(new ChangeListener<String>() {
                                            public void changed(ObservableValue<? extends String> observable,
                                                                String oldValue, String newValue) {
                                                if(city_box.getValue() != null) {
                                                    String loc=city_box.getValue()+","+country_box.getValue();
                                                    muie.setText(city_box.getValue());
                                                    set_labels();
                                                    Server S=new Server();
                                                    JSONObject jsonObject = S.read_from_server(city_box.getValue());
                                                    temperature.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("temp") - 272.15)) + " °C");
                                                    feels_like.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("feels_like") - 272.15)) + " °C");
                                                    humidity.setText(String.format("%.1f", (jsonObject.getJSONObject("main").getDouble("humidity"))) + "%");
                                                    wind_speed.setText(String.format("%.1f km/h", (jsonObject.getJSONObject("wind").getDouble("speed") * 3.6)));
                                                    pressure.setText(String.format("%.1f hPa", (jsonObject.getJSONObject("main").getDouble("pressure"))));
                                                    weather.setText(jsonObject.getJSONArray("weather").getJSONObject(0).getString("main"));
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