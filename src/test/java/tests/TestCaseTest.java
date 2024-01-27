package tests;

import models.project.Project;
import models.suite.Suite;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.getRandomProject;
import static models.suite.SuiteFactory.getRandomSuite;
import static org.testng.Assert.*;

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
        suitesPage.openTestCaseForEdit("New test Case");
        assertEquals(testCasePage.getTestCaseTitle(), "New test Case", "Test case Name doesn't match");
        assertEquals(testCasePage.getDropdownValue("Status"), "Draft", "Status dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Severity"), "Critical", "Severity dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Priority"), "High", "Priority dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Type"), "Smoke", "Type dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Layer"), "API", "Layer dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Is flaky"), "Yes", "Is flaky dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Behavior"), "Positive", "Behavior dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Automation status"), "Automated", "Automation status dropdown value doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Update Test Case")
    public void testCaseShouldBeUpdated() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
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
        testCasePage.saveTestCase();
        suitesPage.openTestCaseForEdit("New test Case");
        testCasePage.setCaseTitle("Updated test case title");
        testCasePage.setDropdownValue("Status", "Deprecated");
        testCasePage.setDropdownValue("Severity", "Trivial");
        testCasePage.setDropdownValue("Priority", "Low");
        testCasePage.setDropdownValue("Type", "Usability");
        testCasePage.setDropdownValue("Layer", "E2E");
        testCasePage.setDropdownValue("Is flaky", "No");
        testCasePage.setDropdownValue("Behavior", "Negative");
        testCasePage.setDropdownValue("Automation status", "Manual");
        testCasePage.saveTestCase();
        suitesPage.openTestCaseForEdit("Updated test case title");
        assertEquals(testCasePage.getTestCaseTitle(), "Updated test case title", "Test case Name doesn't match");
        assertEquals(testCasePage.getDropdownValue("Status"), "Deprecated", "Status dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Severity"), "Trivial", "Severity dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Priority"), "Low", "Priority dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Type"), "Usability", "Type dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Layer"), "E2E", "Layer dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Is flaky"), "No", "Is flaky dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Behavior"), "Negative", "Behavior dropdown value doesn't match");
        assertEquals(testCasePage.getDropdownValue("Automation status"), "Manual", "Automation status dropdown value doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Remove Test Case")
    public void testCaseShouldBeRemoved() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
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
        testCasePage.saveTestCase();
        assertEquals(suitesPage.getTestCaseCreationMessageText(), "Test case was created successfully!", "Test case has not been created");
        assertTrue(suitesPage.isTestCasePresentInList("New test Case"), "Test case is NOT in the list");
        suitesPage.deleleTestCase("New test Case");
        assertFalse(suitesPage.isTestCasePresentInList("New test Case"), "Test case is NOT in the list");
        projectApi.delete(project.getCode());
    }

}
