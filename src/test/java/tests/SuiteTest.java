package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static utils.DataGenerator.*;

public class SuiteTest extends BaseTest {

    @Test(description = "Create new Suite for existing project")
    public void suiteShouldBeCreated() {
        String projectName = generateProjectName();
        String projectCode = generateProjectCode();
        String suiteName = generateSuiteName();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(projectName, projectCode, "test description");
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suiteName, "test2", "test1");
        assertTrue(suitesPage.isSuiteCreated(suiteName), "Suite is not created");
    }
}
