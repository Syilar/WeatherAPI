package DTO;


import lombok.*;

@Data
public class WeatherResponse {

    private int id;
    private String main;
    private String description;
    private String icon;
}
