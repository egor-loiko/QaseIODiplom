package adapters;

import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import models.cases.Case;
import models.cases.CaseResponseApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class CaseApi extends MainAdapter {

    public int createForProject(String projectCode, Case testCase) {
        log.info("Creating new case with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                testCase.getTitle(), testCase.getDescription(), testCase.getPreconditions(), projectCode);
        CaseResponseApi caseResponseApi =
                given()
                        .log().all()
                        .body(testCase)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(baseApiUrl + "case/" + projectCode)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(CaseResponseApi.class);

        return caseResponseApi.getResult().getId();
    }

    public int createForSuite(String projectCode, int suiteId, Case testCase) {
        log.info("Creating new case with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                testCase.getTitle(), testCase.getDescription(), testCase.getPreconditions(), projectCode);
        testCase.setSuiteId(suiteId);
        CaseResponseApi caseResponseApi =
                given()
                        .log().all()
                        .body(testCase)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(baseApiUrl + "case/" + projectCode)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(CaseResponseApi.class);

        return caseResponseApi.getResult().getId();
    }

    public Case getCaseById(String projectCode, Case testCase) {
        log.info("Getting case Info with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                testCase.getTitle(), testCase.getDescription(), testCase.getPreconditions(), projectCode);
        CaseResponseApi caseResponseApi =
                given()
                        .log().all()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseApiUrl + "case/" + projectCode + "/" + testCase.getId())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(CaseResponseApi.class);

        return caseResponseApi.getResult();
    }

    public int delete(String projectCode, Case cases) {
        log.info("Removing case with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                cases.getTitle(), cases.getDescription(), cases.getPreconditions(), projectCode);
        CaseResponseApi caseResponseApi =
                given()
                        .log().all()
                        .body(cases)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(baseApiUrl + "case/" + projectCode + "/" + cases.getId())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(CaseResponseApi.class);

        return caseResponseApi.getResult().getId();
    }

    public int updateCaseById(String projectCode, Case cases) {
        log.info("Updating case Info with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}' via API",
                cases.getTitle(), cases.getDescription(), cases.getPreconditions(), projectCode);
        CaseResponseApi caseResponseApi =
                given()
                        .log().all()
                        .body(cases)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .patch(baseApiUrl + "case/" + projectCode + "/" + cases.getId())
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(CaseResponseApi.class);

        return caseResponseApi.getResult().getId();
    }

}
