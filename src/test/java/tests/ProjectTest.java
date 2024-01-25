package tests;

import models.project.Project;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.*;
import static org.testng.Assert.*;


public class ProjectTest extends BaseTest {

    @Test(description = "Create Project")
    public void projectShouldBeCreated() {
        Project project = getRandomProject();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        projectPage.waitTillProjectCreated();
        assertTrue(projectPage.isProjectCreated(project.getTitle()), "Project is not created");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Create Project with empty project name")
    public void projectShouldNotBeCreatedEmptyProjectName() {
        Project project = getProjectWithEmptyTitle();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        assertEquals(projectsListPage.gettingProjectNameFieldValidationMessage(), "Please fill out this field.", "Invalid validation message text");
    }

    @Test(description = "Create Project with empty project code")
    public void projectShouldNotBeCreatedEmptyProjectCode() {
        Project project = getProjectWithEmptyCode();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        assertEquals(projectsListPage.gettingProjectCodeFieldValidationMessage(), "Please fill out this field.", "Invalid validation message text");
    }

    @Test(description = "Remove Project")
    public void projectShouldBeDeleted() {
        Project project = getRandomProject();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        projectPage.waitTillProjectCreated();
        projectsListPage.openPage();
        assertTrue(projectsListPage.isProjectInList(project.getTitle()), "Project is not in the list of projects");
        projectsListPage.deleteProject(project.getTitle());
        assertFalse(projectsListPage.isProjectInList(project.getTitle()), "Project is in the list of projects");
    }

}
