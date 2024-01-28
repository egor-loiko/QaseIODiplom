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

    @Test(description = "Create Test Case via API")
    public void testCaseShouldBeCreatedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Case testCase = getRandomCase();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        caseApi.create(project.getCode(), suite.getId(), testCase);
        Case testCaseCreated = caseApi.getCaseInfoById(project.getCode(), testCase);
        assertEquals(testCaseCreated.getTitle(), testCase.getTitle(), "Test case Name doesn't match");
        assertEquals(testCaseCreated.getPreconditions(), testCase.getPreconditions(), "Test case Preconditions doesn't match");
        assertEquals(testCaseCreated.getPostconditions(), testCase.getPostconditions(), "Test case Postconditions doesn't match");
        assertEquals(testCaseCreated.getStatus(), testCase.getStatus(), "Test case Status doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Update Test Case via API")
    public void testCaseShouldBeUpdatedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Case testCase = getRandomCase();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        caseApi.create(project.getCode(), suite.getId(), testCase);
        Case newTestCase = getRandomCase();
        caseApi.updateCaseById(project.getCode(), newTestCase);
        Case testCaseUpdated = caseApi.getCaseInfoById(project.getCode(), newTestCase);
        assertEquals(testCaseUpdated.getTitle(), newTestCase.getTitle(), "Test case Name doesn't match");
        assertEquals(testCaseUpdated.getPreconditions(), newTestCase.getPreconditions(), "Test case Preconditions doesn't match");
        assertEquals(testCaseUpdated.getPostconditions(), newTestCase.getPostconditions(), "Test case Postconditions doesn't match");
        assertEquals(testCaseUpdated.getStatus(), newTestCase.getStatus(), "Test case Status doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Remove Test Case via API")
    public void testCaseShouldBeRemovedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Case testCase = getRandomCase();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        caseApi.create(project.getCode(), suite.getId(), testCase);
        Case testCaseCreated = caseApi.getCaseInfoById(project.getCode(), testCase);
        assertEquals(testCaseCreated.getTitle(), testCase.getTitle(), "Test case Name doesn't match");
        caseApi.delete(project.getCode(), testCase);
        assertEquals(caseApi.getCaseErrorInfoById(project.getCode(), testCase), "TestCase not found", "Test Case is NOT removed");
        projectApi.delete(project.getCode());
    }

}