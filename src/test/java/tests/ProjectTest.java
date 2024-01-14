package tests;

import models.project.Project;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.*;
import static org.testng.Assert.*;


public class ProjectTest extends BaseTest {

    @Test(description = "Create new project")
    public void projectShouldBeCreated() {
        Project project = getRandomProject();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        projectPage.waitTillProjectCreated();
        assertTrue(projectPage.isProjectCreated(project.getTitle()), "Project is not created");
    }

    @Test(description = "Create new project with Empty Project name")
    public void projectShouldNotBeCreatedEmptyProjectName() {
        Project project = getProjectWithEmptyTitle();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        assertEquals(projectsListPage.gettingProjectNameFieldValidationMessage(), "Заполните это поле.", "Invalid validation message text");
    }

    @Test(description = "Create new project with Empty Project Code")
    public void projectShouldNotBeCreatedEmptyProjectCode() {
        Project project = getProjectWithEmptyCode();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        assertEquals(projectsListPage.gettingProjectCodeFieldValidationMessage(), "Заполните это поле.", "Invalid validation message text");
    }

    @Test(description = "Remove existing project")
    public void projectShouldBeDeleted() {
        Project project = getRandomProject();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        projectPage.waitTillProjectCreated();
        projectsListPage.openPage();
        projectsListPage.deleteProject(project.getTitle());
        assertFalse(projectsListPage.isProjectInList(project.getTitle()), "Project is not removed");
    }

    @Test
    public void projectShouldBeCreatedViaApi() {
        Project project = getRandomProject();
        assertEquals(projectApi.create(project), project.getCode(), "Project code in not matched");
        System.out.println(projectApi.getProjectByCode(project.getCode()));
        assertTrue(projectApi.delete(project.getCode()), "Project is not removed");

    }

}
