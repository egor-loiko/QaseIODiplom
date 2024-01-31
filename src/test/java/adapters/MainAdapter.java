package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class MainAdapter {

    Gson gson;
    String token;
    String baseApiUrl;
    RequestSpecification request;

    public MainAdapter() {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        token = System.getProperty("token", PropertyReader.getProperty("qaseio.token"));
        baseApiUrl = PropertyReader.getProperty("qaseio.base.apiUrl");

        request = given()
                .log().ifValidationFails()
                .header("Token", token)
                .contentType(ContentType.JSON).when();
    }

}
