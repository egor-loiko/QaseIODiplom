package tests;

import org.testng.annotations.Test;
import utils.PropertyReader;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ProjectTest extends BaseTest {

    @Test(description = "Create new project")
    public void projectShouldBeCreated() {
        String projectName = faker.name().lastName();
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), System.getProperty("password", PropertyReader.getProperty("sf.password")));
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(projectName, projectName, "test description");
        projectPage.waitTillProjectCreated();
        assertTrue(projectPage.isProjectCreated(projectName), "Project is not created");
    }

    @Test(description = "Remove existing project")
    public void projectShouldBeDeleted() {
        String projectName = faker.name().lastName();
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), System.getProperty("password", PropertyReader.getProperty("sf.password")));
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(projectName, projectName, "test description");
        projectPage.waitTillProjectCreated();
        projectsListPage.openPage();
        projectsListPage.deleteProject(projectName);
        assertFalse(projectsListPage.isProjectInList(projectName), "Project is not removed");
    }

    @Test(description = "Create new Suite for existing project")
    public void suiteShouldBeCreated() {
        String projectName = faker.name().lastName();
        String suiteName = faker.name().firstName();
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), System.getProperty("password", PropertyReader.getProperty("sf.password")));
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(projectName, projectName, "test description");
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suiteName, "test2", "test1");
        assertTrue(projectPage.isSuiteCreated(suiteName), "Suite is not created");
    }

}
