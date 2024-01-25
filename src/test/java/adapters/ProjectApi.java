package adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import models.project.Project;
import models.response.ResponseApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class ProjectApi extends MainAdapter {
    ObjectMapper mapper = new ObjectMapper();

    @Step("[API] Create new Project")
    public String create(Project project) {
        log.info("[API] Creating new project with Name '{}', Code '{}' and Description '{}'", project.getTitle(), project.getCode(), project.getDescription());

        ResponseApi<Project> projectResponseApi = mapper.convertValue(
                given()
                        .log().ifValidationFails()
                        .body(project)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(baseApiUrl + "project")
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<ResponseApi<Project>>() {
                });

        return projectResponseApi.getResult().getCode();
    }

    @Step("[API] Remove Project with code '{projectCode}'")
    public boolean delete(String projectCode) {
        log.info("[API] Removing project with Code '{}'", projectCode);
        ResponseApi<Project> projectResponseApi = mapper.convertValue(
                given()
                        .log().ifValidationFails()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(baseApiUrl + "project/" + projectCode)
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<ResponseApi<Project>>() {
                });

        return projectResponseApi.isStatus();
    }

    @Step("[API] Get Project Info with code '{projectCode}'")
    public Project getProjectInfoByCode(String projectCode) {
        log.info("[API] Getting project Info with Code '{}'", projectCode);
        ResponseApi<Project> projectResponseApi = mapper.convertValue(
                given()
                        .log().ifValidationFails()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseApiUrl + "project/" + projectCode)
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<ResponseApi<Project>>() {
                });

        return projectResponseApi.getResult();
    }

}
