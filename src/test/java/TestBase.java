import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

public class TestBase {

    protected static String WEATHER_URL;
    protected static String WEATHER_PATH;
//    private static final Dotenv dotenv = Dotenv
//            .configure()
//            .filename(".env")
//            .load();
//
//    public static final String AUTHORIZATION_TOKEN = dotenv.get("AUTHORIZATION_TOKEN");

    public static final String AUTHORIZATION_TOKEN;
    static {
        // Сначала пытаемся взять из переменной окружения
        String token = System.getenv("AUTHORIZATION_TOKEN");
        if (token == null || token.isEmpty()) {
            // Если не нашли — пытаемся загрузить из .env
            try {
                Dotenv dotenv = Dotenv.configure()
                        .filename(".env")
                        .load();
                token = dotenv.get("AUTHORIZATION_TOKEN");
            } catch (Exception e) {
                // Логируем или обрабатываем ошибку
                token = null;
            }
        }
        AUTHORIZATION_TOKEN = token;
    }

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
