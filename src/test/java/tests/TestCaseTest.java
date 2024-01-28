package tests;

import models.cases.Case;
import models.project.Project;
import models.suite.Suite;
import org.testng.annotations.Test;

import static models.cases.CaseFactory.getRandomCase;
import static models.project.ProjectFactory.getRandomProject;
import static models.suite.SuiteFactory.getRandomSuite;
import static org.testng.Assert.*;

public class TestCaseTest extends BaseTest {

    @Test(description = "Create Test Case")
    public void testCaseShouldBeCreated() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Case testCase = getRandomCase();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        suitesPage.openCreateNewTestCasePage();
        testCasePage.createTestCase(testCase);
        assertEquals(suitesPage.getTestCaseCreationMessageText(), "Test case was created successfully!", "Test case has not been created");
        assertTrue(suitesPage.isTestCasePresentInList(testCase.getTitle()), "Test case is NOT in the list");
        suitesPage.openTestCaseForEdit(testCase.getTitle());
        assertTrue(testCasePage.isTestCaseVerified(testCase), "Verification of Test Case is FAILED");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Update Test Case")
    public void testCaseShouldBeUpdated() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Case testCase = getRandomCase();
        Case testCaseUpdate = getRandomCase();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        suitesPage.openCreateNewTestCasePage();
        testCasePage.createTestCase(testCase);
        suitesPage.openTestCaseForEdit(testCase.getTitle());
        testCasePage.updateTestCase(testCaseUpdate);
        suitesPage.openTestCaseForEdit(testCaseUpdate.getTitle());
        assertTrue(testCasePage.isTestCaseVerified(testCaseUpdate), "Verification of Test Case is FAILED");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Remove Test Case")
    public void testCaseShouldBeRemoved() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Case testCase = getRandomCase();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        suitesPage.openCreateNewTestCasePage();
        testCasePage.createTestCase(testCase);
        assertEquals(suitesPage.getTestCaseCreationMessageText(), "Test case was created successfully!", "Test case has not been created");
        assertTrue(suitesPage.isTestCasePresentInList(testCase.getTitle()), "Test case is NOT in the list");
        suitesPage.deleteTestCase(testCase.getTitle());
        assertFalse(suitesPage.isTestCasePresentInList(testCase.getTitle()), "Test case is NOT in the list");
        projectApi.delete(project.getCode());
    }

}
