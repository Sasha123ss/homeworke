import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AccountTests extends BaseTest {

    @Test
    void getAccountInfoTest() {
        given()
                .headers("Authorization", token)
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .then()
                .statusCode(200);
    }
    @Test
    void getInfoTest () {
        given()
                .headers("Authorization", token)
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
    @Test
    void getResponseTest () {
        given()
                .headers("Authorization", token)
                .expect()
                .statusCode(200)
                .response()
                .contentType("application/json")
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .prettyPeek();
    }
}

