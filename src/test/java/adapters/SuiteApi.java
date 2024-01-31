package adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.response.ResponseApi;
import models.suite.Suite;

import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class SuiteApi extends MainAdapter {

    ObjectMapper mapper = new ObjectMapper();

    @Step("[API] Create Suite for project with code '{projectCode}'")
    public int create(String projectCode, Suite suite) {
        log.info("[API] Creating new suite with Name '{}', Description '{}' and Preconditions '{}' for project with Code '{}'",
                suite.getTitle(), suite.getDescription(), suite.getPreconditions(), projectCode);
        ResponseApi<Suite> suiteResponseApi = mapper.convertValue(
                request
                        .body(suite)
                        .when()
                        .post(baseApiUrl + "suite/" + projectCode)
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<>() {
                });

        return suiteResponseApi.getResult().getId();
    }

    @Step("[API] Remove Suite for project with code '{projectCode}'")
    public int delete(String projectCode, Suite suite) {
        log.info("[API] Removing suite with Name '{}', Description '{}' and Preconditions '{}' from project with Code '{}'",
                suite.getTitle(), suite.getDescription(), suite.getPreconditions(), projectCode);
        ResponseApi<Suite> suiteResponseApi = mapper.convertValue(
                request
                        .delete(baseApiUrl + "suite/" + projectCode + "/" + suite.getId())
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<>() {
                });

        return suiteResponseApi.getResult().getId();
    }

    @Step("[API] Get Suite Info for project with code '{projectCode}'")
    public Suite getSuiteInfoById(String projectCode, int suiteId) {
        log.info("[API] Getting suite Info with Project code '{}' and Suite ID '{}'",
                projectCode, suiteId);
        ResponseApi<Suite> suiteResponseApi = mapper.convertValue(
                request
                        .get(baseApiUrl + "suite/" + projectCode + "/" + suiteId)
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<>() {
                });

        return suiteResponseApi.getResult();
    }

    @Step("[API] Get Suite error Info for project with code '{projectCode}'")
    public String getSuiteErrorInfoById(String projectCode, int suiteId) {
        log.info("[API] Getting suite error Info with Project code '{}' and Suite ID '{}'",
                projectCode, suiteId);
        String suiteErrorMessage =
                request
                        .get(baseApiUrl + "suite/" + projectCode + "/" + suiteId)
                        .then()
                        .log().ifValidationFails()
                        .body("status", equalTo(false))
                        .extract().path("errorMessage");

        return suiteErrorMessage;
    }

    @Step("[API] Update Suite for project with code '{projectCode}'")
    public Suite update(String projectCode, Suite suite) {
        log.info("[API] Updating suite Info with Project code '{}' and Suite ID '{}'",
                projectCode, suite.getId());
        ResponseApi<Suite> suiteResponseApi = mapper.convertValue(
                request
                        .body(suite)
                        .patch(baseApiUrl + "suite/" + projectCode + "/" + suite.getId())
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<>() {
                });

        return suiteResponseApi.getResult();
    }

}