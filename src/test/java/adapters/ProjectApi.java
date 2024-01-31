package adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.project.Project;
import models.response.ResponseApi;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;

@Log4j2
public class ProjectApi extends MainAdapter {
    ObjectMapper mapper = new ObjectMapper();

    @Step("[API] Create new Project")
    public String create(Project project) {
        log.info("[API] Creating new project with Name '{}', Code '{}' and Description '{}'", project.getTitle(), project.getCode(), project.getDescription());

        ResponseApi<Project> projectResponseApi = mapper.convertValue(
                request
                        .body(project)
                        .post(baseApiUrl + "project")
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<>() {
                });

        return projectResponseApi.getResult().getCode();
    }

    @Step("[API] Remove Project with code '{projectCode}'")
    public boolean delete(String projectCode) {
        log.info("[API] Removing project with Code '{}'", projectCode);
        ResponseApi<Project> projectResponseApi = mapper.convertValue(
                request
                        .delete(baseApiUrl + "project/" + projectCode)
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<>() {
                });

        return projectResponseApi.isStatus();
    }

    @Step("[API] Get Project Info with code '{projectCode}'")
    public Project getProjectInfoByCode(String projectCode) {
        log.info("[API] Getting project Info with Code '{}'", projectCode);
        ResponseApi<Project> projectResponseApi = mapper.convertValue(
                request
                        .get(baseApiUrl + "project/" + projectCode)
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .body("status", equalTo(true))
                        .extract().body().as(ResponseApi.class), new TypeReference<>() {
                });

        return projectResponseApi.getResult();
    }

    @Step("[API] Get Project error Info with code '{projectCode}'")
    public String getProjectErrorInfoByCode(String projectCode) {
        log.info("[API] Getting project error Info with Code '{}'", projectCode);
        String projectErrorMessage =
                request
                        .get(baseApiUrl + "project/" + projectCode)
                        .then()
                        .log().ifValidationFails()
                        .body("status", equalTo(false))
                        .extract().path("errorMessage");

        return projectErrorMessage;
    }

    @Step("[API] Remove all projects")
    public void removeAllProjects() {
        log.info("[API] Removing all projects");
        ArrayList<String> codesList = request
                .get(baseApiUrl + "/project?limit=100&offset=0")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("status", equalTo(true)).extract().path("result.entities.code");

        if (!codesList.isEmpty()) {
            for (String code : codesList) {
                delete(code);
            }
        }
    }

}
