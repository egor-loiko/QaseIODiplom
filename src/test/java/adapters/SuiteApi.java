package adapters;

import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import models.project.Project;
import models.suite.Suite;
import models.suite.SuiteResponseApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class SuiteApi extends MainAdapter {

    public int create(Project project, Suite suite) {
        log.info("Creating new suite with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                suite.getTitle(), suite.getDescription(), suite.getPreconditions(), project.getCode());
        SuiteResponseApi suiteResponseApi =
                given()
                        .body(suite)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(baseApiUrl + "suite/" + project.getCode())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult().getId();
    }

    public int delete(Project project, Suite suite) {
        log.info("Removing suite with Name '{}', Description '{}' and Preconditions '{}' from project with Code '{}' via API",
                suite.getTitle(), suite.getDescription(), suite.getPreconditions(), project.getCode());
        SuiteResponseApi suiteResponseApi =
                given()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(baseApiUrl + "suite/" + project.getCode() + "/" + suite.getId())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult().getId();
    }

    public Suite getSuiteById(Project project, Suite suite) {
        log.info("Getting suite Info with Project code '{}' and Suite ID '{}' via API",
                project.getCode(), suite.getId());
        SuiteResponseApi suiteResponseApi =
                given()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseApiUrl + "suite/" + project.getCode() + "/" + suite.getId())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult();
    }

    public Suite update(Project project, Suite suite) {
        log.info("Updating suite Info with Project code '{}' and Suite ID '{}' via API",
                project.getCode(), suite.getId());
        SuiteResponseApi suiteResponseApi =
                given()
                        .body(suite)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .patch(baseApiUrl + "suite/" + project.getCode() + "/" + suite.getId())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(SuiteResponseApi.class);

        return suiteResponseApi.getResult();
    }
}
