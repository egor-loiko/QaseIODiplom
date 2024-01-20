package adapters;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import models.project.Project;
import models.project.ProjectResponseApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class ProjectApi extends MainAdapter {

    @Step("[API] Create new Project")
    public String create(Project project) {
        log.info("[API] Creating new project with Name '{}', Code '{}' and Description '{}'", project.getTitle(), project.getCode(), project.getDescription());
        ProjectResponseApi projectCreateResponseApi =
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
                        .extract().body().as(ProjectResponseApi.class);

        return projectCreateResponseApi.getResult().getCode();
    }

    @Step("[API] Remove Project with code '{projectCode}'")
    public boolean delete(String projectCode) {
        log.info("[API] Removing project with Code '{}'", projectCode);
        ProjectResponseApi projectResponseApi =
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
                        .extract().body().as(ProjectResponseApi.class);

        return projectResponseApi.isStatus();
    }

    @Step("[API] Get Project Info with code '{projectCode}'")
    public Project getProjectInfoByCode(String projectCode) {
        log.info("[API] Getting project Info with Code '{}'", projectCode);
        ProjectResponseApi projectResponseApi =
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
                        .extract().body().as(ProjectResponseApi.class);

        return projectResponseApi.getResult();
    }

}
