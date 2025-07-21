import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

public class TestBase {

    protected static String WEATHER_URL;
    protected static String WEATHER_PATH;
    private static final Dotenv dotenv = Dotenv
            .configure()
            .filename(".env")
            .load();

    public static final String AUTHORIZATION_TOKEN = dotenv.get("AUTHORIZATION_TOKEN");

    @BeforeAll
    public static void setUp() throws IOException {
        Yaml yaml = new Yaml();
        InputStream inputStream = TestBase.class
                .getClassLoader()
                .getResourceAsStream("application.yml");
        if (inputStream == null) {
            throw new IOException("application.yml not found");
        }

        WeatherEndpoints weatherEndpoints = yaml.loadAs(inputStream, WeatherEndpoints.class);

        WEATHER_URL = System.getProperty("api.url", weatherEndpoints.getUrl());
        WEATHER_PATH = System.getProperty("api.path", weatherEndpoints.getPath());
    }
}
