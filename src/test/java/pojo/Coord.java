package pojo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Data
public class Coord {

    private double lon;
    private double lat;

//    public Coord() {
//    }
//
//    public Coord(double lon, double lat) {
//        this.lon = lon;
//        this.lat = lat;
//    }
//
//    public double getLon() {
//        return lon;
//    }
//
//    public double getLat() {
//        return lat;
//    }
}
