package tests;

import org.testng.annotations.Test;
import utils.PropertyReader;

import static org.testng.Assert.assertTrue;
import static utils.DataGenerator.generateProjectName;
import static utils.DataGenerator.generateSuiteName;

public class SuiteTest extends BaseTest {

    @Test(description = "Create new Suite for existing project")
    public void suiteShouldBeCreated() {
        String projectName = generateProjectName();
        String suiteName = generateSuiteName();
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), System.getProperty("password", PropertyReader.getProperty("sf.password")));
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(projectName, projectName, "test description");
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suiteName, "test2", "test1");
        assertTrue(suitesPage.isSuiteCreated(suiteName), "Suite is not created");
    }
}
