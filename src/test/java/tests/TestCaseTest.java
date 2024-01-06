package tests;

import org.testng.annotations.Test;
import utils.PropertyReader;

import static utils.DataGenerator.generateProjectName;
import static utils.DataGenerator.generateSuiteName;

public class TestCaseTest extends BaseTest {

    @Test(description = "Create new test case")
    public void testCaseShouldBeCreated() {
        String projectName = generateProjectName();
        String suiteName = generateSuiteName();
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), System.getProperty("password", PropertyReader.getProperty("sf.password")));
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(projectName, projectName, "test description");
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suiteName, "test2", "test1");
        suitesPage.openCreateNewCasePage();
    }
}
