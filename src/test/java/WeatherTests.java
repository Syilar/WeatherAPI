import DTO.AuthorizationErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import DTO.WeatherAllResponse;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WeatherTests extends TestBase {

    private TestService testService = new TestService();

    private final Double testLat = 44.34;
    private final Double testLon = 10.99;

    private final String wrongAuthorizationToken = "12345";

//    @Step("Проверка получения данных о погоде с валидными координатами и токеном авторизации")
    @ParameterizedTest(name = "Проверка погоды для широты = {0} и долготы {1}")
    @MethodSource("coordinatesProvider")
    public void testGetResponseWeatherWithValidRequiredQueryParam(Double lat, Double lon) {

        WeatherAllResponse response = testService.getResponse(lat, lon, AUTHORIZATION_TOKEN);

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

    public static Stream<Arguments> coordinatesProvider() {
        return Stream.of(
                Arguments.of(44.34, 10.99),
                Arguments.of(50.45, 30.52),
                Arguments.of(35.68, 139.69),
                Arguments.of(48.85, 2.35),
                Arguments.of(55.75, 37.62)
        );
    }

    @DisplayName("Проверка получения данных о погоде с невалидным токеном авторизации")
    @Test
    public void testGetResponseWeatherWithInvalidAuthorizationToken() {
        AuthorizationErrorResponse response = given()
                .spec(SpecificationsWeather.requestSpecWeather(WEATHER_URL, WEATHER_PATH))
                .queryParam("lat", testLat)
                .queryParam("lon", testLon)
                .queryParam("appid", wrongAuthorizationToken)
                .when()
                .get()
                .then().log().body()
                .extract().as(AuthorizationErrorResponse.class);

        assertThat(response.getCod()).isEqualTo(ExpectedAuthorizationError.CODE_401);
        assertThat(response.getMessage()).isEqualTo(ExpectedAuthorizationError.MESSAGE_INVALID_API_KEY);
    }

    @ParameterizedTest(name = "Проверка погоды для широты = {0} и долготы {1}")
    @MethodSource("invalidCoordinatesProvider")
    public void testGetResponseWeatherWithInValidRequiredQueryParam(Double lat, Double lon) {

        given()
                .spec(SpecificationsWeather.requestSpecWeather(WEATHER_URL, WEATHER_PATH))
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", AUTHORIZATION_TOKEN)
                .when()
                .get()
                .then().log().body()
                .statusCode(400)
                .body("message", equalTo("Nothing to geocode"))
                .extract().response();
    }

    public static Stream<Arguments> invalidCoordinatesProvider() {
        return Stream.of(
                Arguments.of(null, 10.99),
                Arguments.of(50.45, null)
//                Arguments.of(0, 0)
//                Arguments.of(48.85, ""),
//                Arguments.of(55,75, 37,62)
        );
    }
}
