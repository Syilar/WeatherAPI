import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojo.Coord;
import pojo.WeatherResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

public class WeatherTests {

    private String authorizationToken = "56b12fd7de51ca3ed64805aa3b92a183";

    @Test
    public void test() {
        WeatherResponse response = given()
                .spec(SpecificationsWeather.requestSpecWeather(EndPointsWeather.URL, EndPointsWeather.PATH))
                .queryParam("lat", "44.34")
                .queryParam("lon", "10.99")
                .queryParam("appid", authorizationToken)
                .when()
                .get()
                .then().log().body()
                .extract().as(WeatherResponse.class);


        System.out.println("ldld");
    }
}
