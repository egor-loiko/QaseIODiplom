package tests;

import models.cases.Case;
import models.project.Project;
import models.suite.Suite;
import org.testng.annotations.Test;

import static models.cases.CaseFactory.getRandomCase;
import static models.project.ProjectFactory.getRandomProject;
import static models.suite.SuiteFactory.getRandomSuite;
import static org.testng.Assert.*;

public class TestCaseApiTest extends BaseTest {

    @Test(description = "Create, update and remove Test Case via API")
    public void testCaseShouldBeCreatedUpdatedRemovedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Case testCase = getRandomCase();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        caseApi.createForSuite(project.getCode(), suite.getId(), testCase);
        caseApi.getCaseById(project.getCode(), testCase);
        Case newTestCase = getRandomCase();
        caseApi.updateCaseById(project.getCode(), newTestCase);
        caseApi.getCaseById(project.getCode(), newTestCase);
        caseApi.delete(project.getCode(), testCase);
        suiteApi.delete(project.getCode(), suite);
        projectApi.delete(project.getCode());
    }

    @Test(description = "Create Test Case via API")
    public void testCaseShouldBeCreatedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Case testCase = getRandomCase();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        caseApi.createForSuite(project.getCode(), suite.getId(), testCase);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        suitesPage.openTestCaseForEdit(testCase.getTitle());
        assertEquals(testCasePage.getTestCaseTitle(), testCase.getTitle(), "Test case Name doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Remove Test Case via API")
    public void testCaseShouldBeRemovedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Case testCase = getRandomCase();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        caseApi.createForSuite(project.getCode(), suite.getId(), testCase);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        assertTrue(suitesPage.isTestCasePresentInList(testCase.getTitle()), "Test case is NOT in the list");
        caseApi.delete(project.getCode(), testCase);
        assertFalse(suitesPage.isTestCasePresentInList(testCase.getTitle()), "Test case is in the list");
        projectApi.delete(project.getCode());
    }

}