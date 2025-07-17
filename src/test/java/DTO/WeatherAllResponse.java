package DTO;

import lombok.*;

import java.util.ArrayList;

@Data
public class WeatherAllResponse {

    private CoordResponse coord;
    private ArrayList<WeatherResponse> weather;
    private String base;
    private MainResponse main;
    private int visibility;
    private WindResponse wind;
    private RainResponse rain;
    private CloudsResponse clouds;
    private int dt;
    private SysResponse sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
}
