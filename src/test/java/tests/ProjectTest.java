package tests;

import org.testng.annotations.Test;
import utils.PropertyReader;

import static org.testng.Assert.*;
import static utils.DataGenerator.generateProjectName;


public class ProjectTest extends BaseTest {

    @Test(description = "Create new project")
    public void projectShouldBeCreated() {
        String projectName = generateProjectName();
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), System.getProperty("password", PropertyReader.getProperty("sf.password")));
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(projectName, projectName, "test description");
        projectPage.waitTillProjectCreated();
        assertTrue(projectPage.isProjectCreated(projectName), "Project is not created");
    }

    @Test(description = "Create new project with Empty Project name")
    public void projectShouldNotBeCreatedEmptyProjectName() {
        String projectName = generateProjectName();
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), System.getProperty("password", PropertyReader.getProperty("sf.password")));
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject("", projectName, "test description");
        assertEquals(projectsListPage.gettingProjectNameFieldValidationMessage(), "Заполните это поле.", "Invalid validation message text");
    }

    @Test(description = "Create new project with Empty Project Code")
    public void projectShouldNotBeCreatedEmptyProjectCode() {
        String projectName = generateProjectName();
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), System.getProperty("password", PropertyReader.getProperty("sf.password")));
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(projectName, "", "test description");
        assertEquals(projectsListPage.gettingProjectCodeFieldValidationMessage(), "Заполните это поле.", "Invalid validation message text");
    }

    @Test(description = "Remove existing project")
    public void projectShouldBeDeleted() {
        String projectName = generateProjectName();
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), System.getProperty("password", PropertyReader.getProperty("sf.password")));
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(projectName, projectName, "test description");
        projectPage.waitTillProjectCreated();
        projectsListPage.openPage();
        projectsListPage.deleteProject(projectName);
        assertFalse(projectsListPage.isProjectInList(projectName), "Project is not removed");
    }

}
