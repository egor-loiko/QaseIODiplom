package adapters;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import models.suite.Suite;
import models.suite.SuiteResponseApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class SuiteApi extends MainAdapter {

    @Step("[API] Create Suite for project with code '{projectCode}'")
    public int create(String projectCode, Suite suite) {
        log.info("[API] Creating new suite with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}'",
                suite.getTitle(), suite.getDescription(), suite.getPreconditions(), projectCode);
        SuiteResponseApi suiteResponseApi =
                given()
                        .log().ifValidationFails()
                        .body(suite)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(baseApiUrl + "suite/" + projectCode)
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult().getId();
    }

    @Step("[API] Remove Suite for project with code '{projectCode}'")
    public int delete(String projectCode, Suite suite) {
        log.info("[API] Removing suite with Name '{}', Description '{}' and Preconditions '{}' from project with Code '{}'",
                suite.getTitle(), suite.getDescription(), suite.getPreconditions(), projectCode);
        SuiteResponseApi suiteResponseApi =
                given()
                        .log().ifValidationFails()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(baseApiUrl + "suite/" + projectCode + "/" + suite.getId())
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult().getId();
    }

    @Step("[API] Get Suite Info for project with code '{projectCode}'")
    public Suite getSuiteInfoById(String projectCode, int suiteId) {
        log.info("[API] Getting suite Info with Project code '{}' and Suite ID '{}'",
                projectCode, suiteId);
        SuiteResponseApi suiteResponseApi =
                given()
                        .log().ifValidationFails()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseApiUrl + "suite/" + projectCode + "/" + suiteId)
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult();
    }

    @Step("[API] Update Suite for project with code '{projectCode}'")
    public Suite update(String projectCode, Suite suite) {
        log.info("[API] Updating suite Info with Project code '{}' and Suite ID '{}'",
                projectCode, suite.getId());
        SuiteResponseApi suiteResponseApi =
                given()
                        .log().ifValidationFails()
                        .body(suite)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .patch(baseApiUrl + "suite/" + projectCode + "/" + suite.getId())
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult();
    }

}
