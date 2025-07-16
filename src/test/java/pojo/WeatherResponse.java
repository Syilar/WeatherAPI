package pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class WeatherResponse {

    public Coord coord;
    private ArrayList<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

//    public WeatherResponse() {
//    }
//
//    public WeatherResponse(Coord coord, ArrayList<Weather> weather,
//                           String base, Main main, int visibility,
//                           Wind wind, Rain rain, Clouds clouds,
//                           int dt, Sys sys, int timezone, int id,
//                           String name, int cod) {
//        this.coord = coord;
//        this.weather = weather;
//        this.base = base;
//        this.main = main;
//        this.visibility = visibility;
//        this.wind = wind;
//        this.rain = rain;
//        this.clouds = clouds;
//        this.dt = dt;
//        this.sys = sys;
//        this.timezone = timezone;
//        this.id = id;
//        this.name = name;
//        this.cod = cod;
//    }
//
//    public Coord getCoord() {
//        return coord;
//    }
//
//    public ArrayList<Weather> getWeather() {
//        return weather;
//    }
//
//    public String getBase() {
//        return base;
//    }
//
//    public Main getMain() {
//        return main;
//    }
//
//    public int getVisibility() {
//        return visibility;
//    }
//
//    public Wind getWind() {
//        return wind;
//    }
//
//    public Rain getRain() {
//        return rain;
//    }
//
//    public Clouds getClouds() {
//        return clouds;
//    }
//
//    public int getDt() {
//        return dt;
//    }
//
//    public Sys getSys() {
//        return sys;
//    }
//
//    public int getTimezone() {
//        return timezone;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getCod() {
//        return cod;
//    }
}
