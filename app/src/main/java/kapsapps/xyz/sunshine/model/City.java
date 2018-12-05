package kapsapps.xyz.sunshine.model;

import com.google.gson.annotations.SerializedName;

public class City{

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("coord")
    private Coordinates coord;

    @SerializedName("country")
    private String country;

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

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coord=" + coord +
                ", country='" + country + '\'' +
                '}';
    }
}

