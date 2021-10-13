import io.restassured.http.Headers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AccountTests extends BaseTest {

    @Test
    void getAccountInfoTest() {
        given()
                .spec(authRequestSpecification)
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .then()
                .spec(bodyResponseSpecification);
    }
    @Test
    void getInfoTest () {
        given()
                .spec(authRequestSpecification)
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .prettyPeek()
                .then()
                .spec(bodyResponseSpecification);
    }
    @Test
    void getResponseTest () {
        given()
                .spec(authRequestSpecification)
                .expect()
                .response()
                .contentType("application/json")
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .prettyPeek();
    }
}

