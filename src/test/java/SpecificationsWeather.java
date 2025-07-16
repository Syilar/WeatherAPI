import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.containsString;

public class SpecificationsWeather {

    public static RequestSpecification requestSpecWeather(String url, String path) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
//                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpecBadAuthorization() {
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .expectBody(containsString("{\"cod\":401, \"message\": \"Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.\"}"))
                .build();
    }

    public static ResponseSpecification responseErrorSpecUnique(int status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .expectBody(containsString("{\"cod\":\"400\",\"message\":\"Nothing to geocode\"}"))
                .build();
    }
}
