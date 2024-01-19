package tests;

import models.cases.Case;
import models.project.Project;
import models.suite.Suite;
import org.testng.annotations.Test;

import static models.cases.CaseFactory.getRandomCase;
import static models.project.ProjectFactory.getRandomProject;
import static models.suite.SuiteFactory.getRandomSuite;
import static org.testng.Assert.assertEquals;

public class TestCaseTest extends BaseTest {

    @Test(description = "Create new test case")
    public void testCaseShouldBeCreated() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suite);
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

    @Test
    public void testCaseShouldBeCreatedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Case testCase = getRandomCase();
        projectApi.create(project);
        int suiteId = suiteApi.create(project.getCode(), suite);
        suite.setId(suiteId);
        int testCaseId = caseApi.createForSuite(project.getCode(),suiteId, testCase);
        testCase.setId(testCaseId);
        System.out.println(caseApi.getCaseById(project.getCode(), testCase));
        Case newTestCase = getRandomCase();
        newTestCase.setId(testCaseId);
        caseApi.updateCaseById(project.getCode(), newTestCase);
        System.out.println(caseApi.getCaseById(project.getCode(), newTestCase));
        caseApi.delete(project.getCode(), testCase);
        suiteApi.delete(project.getCode(), suite);
        projectApi.delete(project.getCode());
    }
}
