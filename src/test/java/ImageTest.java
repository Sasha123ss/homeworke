import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ImageTest extends BaseTest {
    static MultiPartSpecification postMultiPartSpecification;
    private final String PATH_TO_IMAGE = "src/test/java/killerwhale.jpeg";
    static String encodedFile;
    String uploadedImageId;


    @BeforeEach
    void beforeTest() {
        byte[] byteArray = getFileContent();
        encodedFile = Base64.getEncoder().encodeToString(byteArray);

        postMultiPartSpecification = new MultiPartSpecBuilder(encodedFile)
                .controlName("image")
                .build();
    }

    private byte[] getFileContent() {
        byte[] byteArray = new byte[0];
        try {
            byteArray = FileUtils.readFileToByteArray(new File(PATH_TO_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;

    }

    @Test
    void uploadFileTest() {
        uploadedImageId = given()
                .spec(authRequestSpecification)
                .multiPart(postMultiPartSpecification)
                .formParam("title", "ImageTitle")
                .expect()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }
    @Test
    void upFileTest() {
        uploadedImageId = given()
                .spec(authRequestSpecification)
                .multiPart(postMultiPartSpecification)
                .formParam("killerwhale", "KillerWhale")
                .expect()
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");

    }
    @Test
    void uploadImageTest() {
        uploadedImageId = given()
                .spec(authRequestSpecification)
                .multiPart(postMultiPartSpecification)
                .expect()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .statusCode(400)
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }
    @Test
    void upload() {
        uploadedImageId = given()
                .spec(authRequestSpecification)
                .multiPart(postMultiPartSpecification)
                .expect()
                .when()
                .post("/image")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }
    @Test
    void FileTestDir() {
        uploadedImageId = given()
                .spec(authRequestSpecification)
                .multiPart("File", PATH_TO_IMAGE)
                .formParam("killerwhale", "KillerWhale")
                .expect()
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");

    }
    @Test
    void UploadImageFileTest() {
        uploadedImageId = given()
                .spec(authRequestSpecification)
                .multiPart(postMultiPartSpecification)
                .expect()
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");

    }
    @Test
    void UploadImageTest() {
        uploadedImageId = given()
                .spec(authRequestSpecification)
                .multiPart("File", encodedFile)
                .expect()
                .statusCode(200)
                .when()
                .post("https://api.imgur.com/3/upload")
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");

    }
    @Test
    void putFileTest() {
        uploadedImageId = given()
                .spec(authRequestSpecification)
                .multiPart("File", encodedFile)
                .formParam("killerwhale", "KillerWhale")
                .expect()
                .statusCode(200)
                .when()
                .put("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
    }
    @Test
    void postFileTest() {
        uploadedImageId = given()
                .spec(authRequestSpecification)
                .multiPart(postMultiPartSpecification)
                .formParam("killerwhale", "KillerWhale")
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");

    }
    @AfterEach
    void tearDown() {
        given()
                .spec(authRequestSpecification)
                .when()
                .delete("account/{username}/image/{deleteHash}", username, uploadedImageId)
                .prettyPeek()
                .then()
                .spec(bodyResponseSpecification);
    }

}
