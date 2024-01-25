package tests;

import models.project.Project;
import models.suite.Suite;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.getRandomProject;
import static models.suite.SuiteFactory.getRandomSuite;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestCaseTest extends BaseTest {

    @Test(description = "Create Test Case")
    public void testCaseShouldBeCreated() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createProject(project);
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suite);
        suitesPage.openCreateNewTestCasePage();
        testCasePage.setCaseTitle("New test Case");
        testCasePage.setDropdownValue("Status", "Draft");
        testCasePage.setDropdownValue("Severity", "Critical");
        testCasePage.setDropdownValue("Priority", "High");
        testCasePage.setDropdownValue("Type", "Smoke");
        testCasePage.setDropdownValue("Layer", "API");
        testCasePage.setDropdownValue("Is flaky", "Yes");
        testCasePage.setDropdownValue("Behavior", "Positive");
        testCasePage.setDropdownValue("Automation status", "Automated");
        testCasePage.addTcStep("FIrst step name", "First step DATA", "First step EXP RESULT");
        testCasePage.addTcStep("Second step name", "Second step DATA", "Second step EXP RESULT");
        testCasePage.addTcStep("Third step name", "Third step DATA", "Third step EXP RESULT");
        testCasePage.addTcStep("Fourth step name", "Fourth step DATA", "Fourth step EXP RESULT");
        testCasePage.addTcStep("Fifth step name", "Fifth step DATA", "Fifth step EXP RESULT");
        testCasePage.addTcStep("Sixth step name", "Sixth step DATA", "Sixth step EXP RESULT");
        testCasePage.addTcStep("Seventh step name", "Seventh step DATA", "Seventh step EXP RESULT");
        testCasePage.addTcStep("Eighth step name", "Eighth step DATA", "Eighth step EXP RESULT");
        testCasePage.addTcStep("Ninth step name", "Ninth step DATA", "Ninth step EXP RESULT");
        testCasePage.addTcStep("Tenth step name", "Tenth step DATA", "Tenth step EXP RESULT");
        testCasePage.saveTestCase();
        assertEquals(suitesPage.getTestCaseCreationMessageText(), "Test case was created successfully!", "Test case has not been created");
        assertTrue(suitesPage.isTestCasePresentInList("New test Case"), "Test case is NOT in the list");
        projectApi.delete(project.getCode());
    }

}
