package pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Sys {

    private int type;
    private int id;
    private String country;
    private int sunrise;
    private int sunset;
}
