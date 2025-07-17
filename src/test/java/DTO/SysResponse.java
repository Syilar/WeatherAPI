package DTO;


import lombok.*;

@Data
public class SysResponse {

    private int type;
    private int id;
    private String country;
    private int sunrise;
    private int sunset;
}
