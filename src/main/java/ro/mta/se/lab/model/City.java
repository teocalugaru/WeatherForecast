package ro.mta.se.lab.model;
/**
 * This class is used for representing the city
 * */
public class City {
    private int id;
    private String name;
    private double latitudine;
    private double longitudine;
    private String code;
    public City(int id, String name, double lat, double longit,String code){
        this.id=id;
        this.name=name;
        this.latitudine=lat;
        this.longitudine=longit;
        this.code=code;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public String getCode() {
        return code;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
