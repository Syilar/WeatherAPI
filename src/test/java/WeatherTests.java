import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pojo.WeatherResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class WeatherTests extends TestBase {

    private TestService testService = new TestService();

    private final double testLat = 44.34;
    private final double testLon = 10.99;

    private final String wrongAuthorizationToken = "12345";

    @Step("Проверка получения данных о погоде с валидными координатами и токеном авторизации")
    @ParameterizedTest(name = "Проверка погоды для широты = {0} и долготы {1}")
    @MethodSource("TestService#coordinatesProvider")
    public void testGetResponseWeatherWithValidRequiredQueryParam(double lat, double lon) {

        WeatherResponse response = testService.getResponse(lat, lon, authorizationToken);

        assertThat(response.getWeather())
                .isNotNull()
                .isNotEmpty()
                .describedAs("Список погоды (Weather) не должен быт NULL или пустым!");

        assertThat(response.getCoord().getLat())
                .isEqualTo(lat)
                .describedAs("Широта (latitude) должна соответствовать ожидаемому значению " + lat);

        assertThat(response.getCoord().getLon())
                .isEqualTo(lon)
                .describedAs("Долгота (longitude) должна соответствовать ожидаемому значению " + lon);
    }



    @Step("Проверка получения данных о погоде с невалидным токеном авторизации")
    @Test
    public void testGetResponseWeatherWithInvalidAuthorizationToken() {
        given()
                .spec(SpecificationsWeather.requestSpecWeather(EndPointsWeather.URL, EndPointsWeather.PATH))
                .queryParam("lat", testLat)
                .queryParam("lon", testLon)
                .queryParam("appid", wrongAuthorizationToken)
                .when()
                .get()
                .then()
                .spec(SpecificationsWeather.responseSpecBadAuthorization())
                .log().body()
                .extract().response();
    }

    @Step("Проверка получения данных о погоде с невалидными координатами")
//    @ParameterizedTest(name = "Проверка погоды для широты = {0} и долготы {1}")
//    @MethodSource("TestService#invalidCoordinatesProvider")
    @Test
    public void testGetResponseWeatherWithInValidRequiredQueryParam() {

        given()
                .spec(SpecificationsWeather.requestSpecWeather(EndPointsWeather.URL, EndPointsWeather.PATH))
                .queryParam("lat", "")
                .queryParam("lon", "")
                .queryParam("appid", authorizationToken)
                .when()
                .get()
                .then()
                .spec(SpecificationsWeather.responseErrorSpecUnique(400))
                .log().body()
                .extract().response();

//        WeatherResponse response = getResponse(lat, lon, authorizationToken);

//        assertThat(response.getWeather())
//                .isNotNull()
//                .isNotEmpty()
//                .describedAs("Список погоды (Weather) не должен быт NULL или пустым!");
//
//        assertThat(response.getCoord().getLat())
//                .isEqualTo(lat)
//                .describedAs("Широта (latitude) должна соответствовать ожидаемому значению " + lat);
//
//        assertThat(response.getCoord().getLon())
//                .isEqualTo(lon)
//                .describedAs("Долгота (longitude) должна соответствовать ожидаемому значению " + lon);
    }
}
