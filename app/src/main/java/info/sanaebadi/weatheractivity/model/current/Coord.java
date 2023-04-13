package info.sanaebadi.weatheractivity.model.current;

import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    private Object lon;
    @SerializedName("lat")
    private Object lat;

    public Object getLon() {
        return lon;
    }

    public Object getLat() {
        return lat;
    }
}