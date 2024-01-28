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
        projectsListPage.createProject(project);
        projectPage.waitTillProjectCreated();
        assertTrue(projectPage.isProjectCreated(project.getTitle()), "Project is not created");
        projectPage.openProjectSettings();
        projectPage.waitTillSettingsOpened();
        assertEquals(projectPage.getProjectName(), project.getTitle(), "Name of Project doesn't match");
        assertEquals(projectPage.getProjectCode(), project.getCode(), "Code of Project doesn't match");
        assertEquals(projectPage.getProjectDescription(), project.getDescription(), "Description of Project doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Create Project with empty project name")
    public void projectShouldNotBeCreatedEmptyProjectName() {
        Project project = getProjectWithEmptyTitle();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createProject(project);
        assertEquals(projectsListPage.gettingProjectNameFieldValidationMessage(), "Please fill out this field.", "Invalid validation message text");
    }

    @Test(description = "Create Project with empty project code")
    public void projectShouldNotBeCreatedEmptyProjectCode() {
        Project project = getProjectWithEmptyCode();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createProject(project);
        assertEquals(projectsListPage.gettingProjectCodeFieldValidationMessage(), "Please fill out this field.", "Invalid validation message text");
    }

    @Test(description = "Update Project")
    public void projectShouldBeUpdated() {
        Project project = getRandomProject();
        Project projectUpdate = getRandomProject();
        projectApi.create(project);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        projectPage.openProjectSettings();
        projectPage.waitTillSettingsOpened();
        projectPage.updateProject(projectUpdate);
        projectsListPage.openPage();
        projectsListPage.openProject(projectUpdate.getTitle());
        projectPage.openProjectSettings();
        projectPage.waitTillSettingsOpened();
        assertEquals(projectPage.getProjectName(), projectUpdate.getTitle(), "Name of Project doesn't match");
        assertEquals(projectPage.getProjectCode(), projectUpdate.getCode(), "Code of Project doesn't match");
        assertEquals(projectPage.getProjectDescription(), projectUpdate.getDescription(), "Description of Project doesn't match");
        projectApi.delete(projectUpdate.getCode());
    }

    @Test(description = "Remove Project")
    public void projectShouldBeDeleted() {
        Project project = getRandomProject();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createProject(project);
        projectPage.waitTillProjectCreated();
        projectsListPage.openPage();
        assertTrue(projectsListPage.isProjectInList(project.getTitle()), "Project is not in the list of projects");
        projectsListPage.deleteProject(project.getTitle());
        assertFalse(projectsListPage.isProjectInList(project.getTitle()), "Project is in the list of projects");
    }

}
