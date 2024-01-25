package adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import models.cases.Case;
import models.response.ResponseApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class CaseApi extends MainAdapter {

    ObjectMapper mapper = new ObjectMapper();

    @Step("[API] Create Test Case for Project with code '{projectCode}' and Suite with id '{suiteId}'")
    public int createForSuite(String projectCode, int suiteId, Case testCase) {
        log.info("[API] Creating new case with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}'",
                testCase.getTitle(), testCase.getDescription(), testCase.getPreconditions(), projectCode);
        testCase.setSuiteId(suiteId);
        ResponseApi<Case> caseResponseApi = mapper.convertValue(
                given()
                        .body(testCase)
                        .log().ifValidationFails()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(baseApiUrl + "case/" + projectCode)
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<ResponseApi<Case>>() {
                });

        return caseResponseApi.getResult().getId();
    }

    @Step("[API] Get Test Case Info for Project with code '{projectCode}'")
    public Case getCaseById(String projectCode, Case testCase) {
        log.info("[API] Getting case Info with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}'",
                testCase.getTitle(), testCase.getDescription(), testCase.getPreconditions(), projectCode);
        ResponseApi<Case> caseResponseApi = mapper.convertValue(
                given()
                        .log().ifValidationFails()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseApiUrl + "case/" + projectCode + "/" + testCase.getId())
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<ResponseApi<Case>>() {
                });

        return caseResponseApi.getResult();
    }

    @Step("[API] Remove Test Case for Project with code '{projectCode}'")
    public int delete(String projectCode, Case testCase) {
        log.info("[API] Removing case with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}'",
                testCase.getTitle(), testCase.getDescription(), testCase.getPreconditions(), projectCode);
        ResponseApi<Case> caseResponseApi = mapper.convertValue(
                given()
                        .log().ifValidationFails()
                        .body(testCase)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(baseApiUrl + "case/" + projectCode + "/" + testCase.getId())
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<ResponseApi<Case>>() {
                });

        return caseResponseApi.getResult().getId();
    }

    @Step("[API] Update Test Case for Project with code '{projectCode}'")
    public int updateCaseById(String projectCode, Case cases) {
        log.info("[API] Updating case Info with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}'",
                cases.getTitle(), cases.getDescription(), cases.getPreconditions(), projectCode);
        ResponseApi<Case> caseResponseApi = mapper.convertValue(
                given()
                        .log().ifValidationFails()
                        .body(cases)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .patch(baseApiUrl + "case/" + projectCode + "/" + cases.getId())
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<ResponseApi<Case>>() {
                });

        return caseResponseApi.getResult().getId();
    }

}
