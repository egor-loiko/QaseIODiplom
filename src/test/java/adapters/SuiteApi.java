package adapters;

import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import models.suite.Suite;
import models.suite.SuiteResponseApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class SuiteApi extends MainAdapter {

    public int create(String projectCode, Suite suite) {
        log.info("Creating new suite with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                suite.getTitle(), suite.getDescription(), suite.getPreconditions(), projectCode);
        SuiteResponseApi suiteResponseApi =
                given()
                        .log().all()
                        .body(suite)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(baseApiUrl + "suite/" + projectCode)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult().getId();
    }

    public int delete(String projectCode, Suite suite) {
        log.info("Removing suite with Name '{}', Description '{}' and Preconditions '{}' from project with Code '{}' via API",
                suite.getTitle(), suite.getDescription(), suite.getPreconditions(), projectCode);
        SuiteResponseApi suiteResponseApi =
                given()
                        .log().all()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(baseApiUrl + "suite/" + projectCode + "/" + suite.getId())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult().getId();
    }

    public Suite getSuiteInfoById(String projectCode, int suiteId) {
        log.info("Getting suite Info with Project code '{}' and Suite ID '{}' via API",
                projectCode, suiteId);
        SuiteResponseApi suiteResponseApi =
                given()
                        .log().all()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseApiUrl + "suite/" + projectCode + "/" + suiteId)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult();
    }

    public Suite update(String projectCode, Suite suite) {
        log.info("Updating suite Info with Project code '{}' and Suite ID '{}' via API",
                projectCode, suite.getId());
        SuiteResponseApi suiteResponseApi =
                given()
                        .log().all()
                        .body(suite)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .patch(baseApiUrl + "suite/" + projectCode + "/" + suite.getId())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult();
    }
}
