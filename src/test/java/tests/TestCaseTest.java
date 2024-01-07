package tests;

import org.testng.annotations.Test;

import static utils.DataGenerator.*;

public class TestCaseTest extends BaseTest {

    @Test(description = "Create new test case")
    public void testCaseShouldBeCreated() {
        String projectName = generateProjectName();
        String projectCode = generateProjectCode();
        String suiteName = generateSuiteName();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(projectName, projectCode, "test description");
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suiteName, "test2", "test1");
        suitesPage.openCreateNewCasePage();
        testCasePage.setCaseTitle("New test Case");
        testCasePage.setDropdownValue("Status");


    }
}
