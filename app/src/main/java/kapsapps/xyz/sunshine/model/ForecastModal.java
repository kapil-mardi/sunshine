package kapsapps.xyz.sunshine.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastModal {

    @SuppressWarnings("cod")
    private String cod;

    @SerializedName("message")
    private double message;

    @SuppressWarnings("cnt")
    private int cnt;

    @SerializedName("list")
    private List<Weather> weatherData;

    @SerializedName("city")
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<Weather> getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(List<Weather> weatherData) {
        this.weatherData = weatherData;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ForecastModal{" +
                "cod='" + cod + '\'' +
                ", message=" + message +
                ", cnt=" + cnt +
                ", weatherData=" + weatherData +
                ", city=" + city +
                '}';
    }
}

