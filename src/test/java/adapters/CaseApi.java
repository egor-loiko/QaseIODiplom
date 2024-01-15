package adapters;

import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import models.cases.Case;
import models.cases.CaseResponseApi;
import models.project.Project;
import models.suite.Suite;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class CaseApi extends MainAdapter {

    public int createForProject(Project project, Case cases) {
        log.info("Creating new case with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                cases.getTitle(), cases.getDescription(), cases.getPreconditions(), project.getCode());
        CaseResponseApi caseResponseApi =
                given()
                        .log().all()
                        .body(cases)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(baseApiUrl + "case/" + project.getCode())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(CaseResponseApi.class);

        return caseResponseApi.getResult().getId();
    }

    public int createForSuite(Project project, Suite suite, Case cases) {
        log.info("Creating new case with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                cases.getTitle(), cases.getDescription(), cases.getPreconditions(), project.getCode());
        cases.setSuiteId(suite.getId());
        CaseResponseApi caseResponseApi =
                given()
                        .log().all()
                        .body(cases)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(baseApiUrl + "case/" + project.getCode())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(CaseResponseApi.class);

        return caseResponseApi.getResult().getId();
    }

    public Case getCaseById(Project project, Case cases) {
        log.info("Getting case Info with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                cases.getTitle(), cases.getDescription(), cases.getPreconditions(), project.getCode());
        CaseResponseApi caseResponseApi =
                given()
                        .log().all()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseApiUrl + "case/" + project.getCode() + "/" + cases.getId())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(CaseResponseApi.class);

        return caseResponseApi.getResult();
    }

    public int delete(Project project, Case cases) {
        log.info("Removing case with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                cases.getTitle(), cases.getDescription(), cases.getPreconditions(), project.getCode());
        CaseResponseApi caseResponseApi =
                given()
                        .log().all()
                        .body(cases)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(baseApiUrl + "case/" + project.getCode() + "/" + cases.getId())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(CaseResponseApi.class);

        return caseResponseApi.getResult().getId();
    }

    public int updateCaseById(Project project, Case cases) {
        log.info("Updating case Info with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                cases.getTitle(), cases.getDescription(), cases.getPreconditions(), project.getCode());
        CaseResponseApi caseResponseApi =
                given()
                        .log().all()
                        .body(cases)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .patch(baseApiUrl + "case/" + project.getCode() + "/" + cases.getId())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(CaseResponseApi.class);

        return caseResponseApi.getResult().getId();
    }

}
