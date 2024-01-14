package tests;

import models.project.Project;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.getRandomProject;
import static org.testng.Assert.assertEquals;
import static utils.DataGenerator.generateSuiteName;

public class TestCaseTest extends BaseTest {

    @Test(description = "Create new test case")
    public void testCaseShouldBeCreated() {
        Project project = getRandomProject();
        String suiteName = generateSuiteName();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suiteName, "test2", "test1");
        suitesPage.openCreateNewCasePage();
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
    }
}
