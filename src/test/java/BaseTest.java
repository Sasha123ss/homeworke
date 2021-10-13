import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.beans.PropertyEditorSupport;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {
    static Properties properties = new Properties();
    static String token;
    static String username;
    static RequestSpecification authRequestSpecification;
    static ResponseSpecification bodyResponseSpecification;


    @BeforeAll
    static void beforeAll() {
        getProperties();
        token = properties.getProperty("token");
        username = properties.getProperty("username");


        authRequestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .build();

        bodyResponseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
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
