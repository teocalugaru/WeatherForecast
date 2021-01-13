package ro.mta.se.lab.Classes;

import ro.mta.se.lab.Classes.City;

import java.util.ArrayList;

public class State {
    private ArrayList<City> cities;
    private String code;
    public State(ArrayList<City> cities, String code){
        this.cities=cities;
        this.code=code;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public String getCode() {
        return code;
    }
    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public void setCode(String code) {
        this.code = code;
    }
}