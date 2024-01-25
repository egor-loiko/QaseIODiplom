package tests;

import models.project.Project;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.getRandomProject;
import static org.testng.Assert.*;

public class ProjectApiTest extends BaseTest {

    @Test(description = "Create Project via API")
    public void projectShouldBeCreatedViaApi() {
        Project project = getRandomProject();
        projectApi.create(project);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        projectPage.openProjectSettings();
        projectPage.waitTillSettingsOpened();
        assertEquals(projectPage.getProjectName(), project.getTitle(), "Name of Project doesn't match");
        assertEquals(projectPage.getProjectCode(), project.getCode(), "Code of Project doesn't match");
        assertEquals(projectPage.getProjectDescription(), project.getDescription(), "Description of Project doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Remove Project via API")
    public void projectShouldBeRemovedViaApi() {
        Project project = getRandomProject();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createProject(project);
        projectPage.waitTillProjectCreated();
        projectsListPage.openPage();
        assertTrue(projectsListPage.isProjectInList(project.getTitle()), "Project is not in the list of Projects");
        projectApi.delete(project.getCode());
        assertFalse(projectsListPage.isProjectInList(project.getTitle()), "Project is still in the list of Projects");
    }

}