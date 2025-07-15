import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecificationsWeather {

    public static RequestSpecification requestSpecWeather(String url, String path) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
//                .log(LogDetail.ALL)
                .build();
    }
}
