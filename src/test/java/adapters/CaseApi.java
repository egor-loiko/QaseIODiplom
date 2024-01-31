package adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.cases.Case;
import models.response.ResponseApi;

import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class CaseApi extends MainAdapter {

    ObjectMapper mapper = new ObjectMapper();

    @Step("[API] Create Test Case for Project with code '{projectCode}' and Suite with id '{suiteId}'")
    public int create(String projectCode, int suiteId, Case testCase) {
        log.info("[API] Creating Test Case with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}'",
                testCase.getTitle(), testCase.getDescription(), testCase.getPreconditions(), projectCode);
        testCase.setSuiteId(suiteId);
        ResponseApi<Case> caseResponseApi = mapper.convertValue(
                request
                        .body(testCase)
                        .post(baseApiUrl + "case/" + projectCode)
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<>() {
                });

        return caseResponseApi.getResult().getId();
    }

    @Step("[API] Get Test Case Info for Project with code '{projectCode}'")
    public Case getCaseInfoById(String projectCode, Case testCase) {
        log.info("[API] Getting Test Case Info with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}'",
                testCase.getTitle(), testCase.getDescription(), testCase.getPreconditions(), projectCode);
        ResponseApi<Case> caseResponseApi = mapper.convertValue(
                request
                        .get(baseApiUrl + "case/" + projectCode + "/" + testCase.getId())
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<>() {
                });

        return caseResponseApi.getResult();
    }

    @Step("[API] Get Test Case Error Info for Project with code '{projectCode}'")
    public String getCaseErrorInfoById(String projectCode, Case testCase) {
        log.info("[API] Getting Test Case Error Info with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}'",
                testCase.getTitle(), testCase.getDescription(), testCase.getPreconditions(), projectCode);
        String caseErrorMessage =
                request
                        .get(baseApiUrl + "case/" + projectCode + "/" + testCase.getId())
                        .then()
                        .log().ifValidationFails()
                        .body("status", equalTo(false))
                        .extract().path("errorMessage");

        return caseErrorMessage;
    }

    @Step("[API] Remove Test Case for Project with code '{projectCode}'")
    public int delete(String projectCode, Case testCase) {
        log.info("[API] Removing Test Case with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}'",
                testCase.getTitle(), testCase.getDescription(), testCase.getPreconditions(), projectCode);
        ResponseApi<Case> caseResponseApi = mapper.convertValue(
                request
                        .body(testCase)
                        .delete(baseApiUrl + "case/" + projectCode + "/" + testCase.getId())
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<>() {
                });

        return caseResponseApi.getResult().getId();
    }

    @Step("[API] Update Test Case for Project with code '{projectCode}'")
    public int updateCaseById(String projectCode, Case cases) {
        log.info("[API] Updating Test Case with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}'",
                cases.getTitle(), cases.getDescription(), cases.getPreconditions(), projectCode);
        ResponseApi<Case> caseResponseApi = mapper.convertValue(
                request
                        .body(cases)
                        .patch(baseApiUrl + "case/" + projectCode + "/" + cases.getId())
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<>() {
                });

        return caseResponseApi.getResult().getId();
    }

}
