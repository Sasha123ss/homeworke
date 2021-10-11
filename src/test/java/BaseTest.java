import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {
    static Properties properties = new Properties();
    static String token;
    static String username;

    @BeforeAll
    static void beforeAll() {
        getProperties();
        token = properties.getProperty("token");
        username = properties.getProperty("username");
    }

    private static void getProperties() {
        try (InputStream output = new FileInputStream("src/test/java/properties.properties")) {
            properties.load(output);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
