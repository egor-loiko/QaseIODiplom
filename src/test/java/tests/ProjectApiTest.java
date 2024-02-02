package tests;

import models.project.Project;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.getRandomProject;
import static org.testng.Assert.assertEquals;

public class ProjectApiTest extends BaseApiTest {

    @Test(description = "Create Project via API")
    public void projectShouldBeCreatedViaApi() {
        Project project = getRandomProject();
        projectApi.create(project);
        Project projectCreated = projectApi.getProjectInfoByCode(project.getCode());
        assertEquals(projectCreated.getTitle(), project.getTitle(), "Name of Project doesn't match");
        assertEquals(projectCreated.getCode(), project.getCode(), "Code of Project doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Remove Project via API")
    public void projectShouldBeRemovedViaApi() {
        Project project = getRandomProject();
        projectApi.create(project);
        Project projectCreated = projectApi.getProjectInfoByCode(project.getCode());
        assertEquals(projectCreated.getTitle(), project.getTitle(), "Name of Project doesn't match");
        projectApi.delete(project.getCode());
        assertEquals(projectApi.getProjectErrorInfoByCode(project.getCode()), "Project not found", "Project is NOT removed");
    }

}