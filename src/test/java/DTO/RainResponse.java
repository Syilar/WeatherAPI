package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class RainResponse {

    @JsonProperty("1h")
    private double oneHour;
}
