import io.qameta.allure.Step;
import DTO.WeatherAllResponse;

import static io.restassured.RestAssured.given;

public class TestService {

    @Step("Отправка GET запроса к API погоды с координатами: lat={lat}, lon={lon}")
    public WeatherAllResponse getResponse(double lat, double lon, String token) {
        return given()
                .spec(SpecificationsWeather.requestSpecWeather(TestBase.WEATHER_URL, TestBase.WEATHER_PATH))
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", token)
                .when()
                .get()
                .then().log().body()
                .extract().as(WeatherAllResponse.class);
    }
}
