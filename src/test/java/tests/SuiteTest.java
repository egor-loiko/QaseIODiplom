package tests;

import models.project.Project;
import models.suite.Suite;
import org.testng.annotations.Test;
import utils.Retry;

import static models.project.ProjectFactory.getRandomProject;
import static models.suite.SuiteFactory.getRandomSuite;
import static org.testng.Assert.*;

public class SuiteTest extends BaseTest {

    @Test(description = "Create Suite", retryAnalyzer = Retry.class)
    public void suiteShouldBeCreated() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createProject(project);
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suite);
        assertTrue(suitesPage.isSuitePresentInList(suite.getTitle()), "Suite is not created");
        suitesPage.openSuiteToEdit(suite.getTitle());
        assertEquals(suitesPage.getSuiteName(), suite.getTitle(), "Name of Suite doesn't match");
        assertEquals(suitesPage.getSuiteDescription(), suite.getDescription(), "Description of Suite doesn't match");
        assertEquals(suitesPage.getSuitePreconditions(), suite.getPreconditions(), "Preconditions of Suite doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Update Suite", retryAnalyzer = Retry.class)
    public void suiteShouldBeUpdated() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Suite suiteUpdate = getRandomSuite();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        suitesPage.openSuiteToEdit(suite.getTitle());
        suitesPage.updateSuite(suiteUpdate);
        assertTrue(suitesPage.isSuitePresentInList(suiteUpdate.getTitle()), "Suite is not present in the list");
        suitesPage.openSuiteToEdit(suiteUpdate.getTitle());
        assertEquals(suitesPage.getSuiteName(), suiteUpdate.getTitle(), "Name of Suite doesn't match");
        assertEquals(suitesPage.getSuiteDescription(), suiteUpdate.getDescription(), "Description of Suite doesn't match");
        assertEquals(suitesPage.getSuitePreconditions(), suiteUpdate.getPreconditions(), "Preconditions of Suite doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Remove Suite", retryAnalyzer = Retry.class)
    public void suiteShouldBeRemoved() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        assertTrue(suitesPage.isSuitePresentInList(suite.getTitle()), "Suite is NOT present in the list");
        suitesPage.removeSuite(suite.getTitle());
        assertFalse(suitesPage.isSuitePresentInList(suite.getTitle()), "Suite is present in the list");
        projectApi.delete(project.getCode());
    }

}
