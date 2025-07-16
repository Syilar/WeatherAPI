import io.qameta.allure.Step;
import org.junit.jupiter.params.provider.Arguments;
import pojo.WeatherResponse;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

public class TestService {

    @Step("Отправка GET запроса к API погоды с координатами: lat={lat}, lon={lon}")
    public WeatherResponse getResponse(double lat, double lon, String token) {
        return given()
                .spec(SpecificationsWeather.requestSpecWeather(EndPointsWeather.URL, EndPointsWeather.PATH))
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", token)
                .when()
                .get()
                .then().log().body()
                .extract().as(WeatherResponse.class);
    }

    public static Stream<Arguments> coordinatesProvider() {
        return Stream.of(
                Arguments.of(44.34, 10.99),
                Arguments.of(50.45, 30.52),
                Arguments.of(35.68, 139.69),
                Arguments.of(48.85, 2.35),
                Arguments.of(55.75, 37.62)
        );
    }

//    public static Stream<Arguments> invalidCoordinatesProvider() {
//        return Stream.of(
//                Arguments.of(null, 10.99),
//                Arguments.of(50.45, null),
//                Arguments.of("", 139.69),
//                Arguments.of(48.85, ""),
//                Arguments.of(55,75, 37,62)
//        );
//    }
}
