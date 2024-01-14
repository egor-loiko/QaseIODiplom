package adapters;

import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import models.project.Project;
import models.project.ProjectResponseApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class ProjectApi extends MainAdapter {
    public String create(Project project) {
        log.info("Creating new project with Name '{}', Code '{}' and Description '{}' via API", project.getTitle(), project.getCode(), project.getDescription());
        ProjectResponseApi projectCreateResponseApi =
                given()
                        .body(project)
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(baseApiUrl + "project")
                        .then()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ProjectResponseApi.class);

        return projectCreateResponseApi.getResult().getCode();
    }

    public boolean delete(String code) {
        log.info("Removing project with Code '{}' via API", code);
        ProjectResponseApi projectResponseApi =
                given()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(baseApiUrl + "project/" + code)
                        .then()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ProjectResponseApi.class);
        return projectResponseApi.isStatus();
    }

    public Project getProjectByCode(String code) {
        log.info("Getting project Info with Code '{}' via API", code);
        ProjectResponseApi projectResponseApi =
                given()
                        .header("Token", token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(baseApiUrl + "project/" + code)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ProjectResponseApi.class);
        log.info("Project is found {}", projectResponseApi.getResult());
        return projectResponseApi.getResult();
    }
}
