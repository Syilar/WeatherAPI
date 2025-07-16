import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    protected String authorizationToken = "56b12fd7de51ca3ed64805aa3b92a183";

    @BeforeEach
    public void setUp() throws IOException {
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream("src/test/resources/application.properties");
        properties.load(in);
        authorizationToken = properties.getProperty("authorizationToken");
    }
}
