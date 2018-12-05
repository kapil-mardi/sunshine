package kapsapps.xyz.sunshine.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    /*@SerializedName("coord")
    private Coordinates coordinates;*/

    @SerializedName("weather")
    private List<WeatherData> weatherData;

    @SerializedName("base")
    private String base;

    @SerializedName("main")
    private MainInfo main;

    @SerializedName("wind")
    private WindInfo wind;

    @SerializedName("clouds")
    private CloudInfo clouds;

    @SerializedName("sys")
    private SystemInfo sys;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private int cod;

    @SerializedName("dt")
    private long dateTime;


    /*public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }*/

    public List<WeatherData> getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(List<WeatherData> weatherData) {
        this.weatherData = weatherData;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public WindInfo getWind() {
        return wind;
    }

    public void setWind(WindInfo wind) {
        this.wind = wind;
    }

    public CloudInfo getClouds() {
        return clouds;
    }

    public void setClouds(CloudInfo clouds) {
        this.clouds = clouds;
    }

    public SystemInfo getSys() {
        return sys;
    }

    public void setSys(SystemInfo sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weatherData=" + weatherData +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", sys=" + sys +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                ", dateTime=" + dateTime +
                '}';
    }
}










