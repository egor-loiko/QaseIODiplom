package tests;

import models.project.Project;
import models.suite.Suite;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.getRandomProject;
import static models.suite.SuiteFactory.getRandomSuite;
import static org.testng.Assert.*;

public class SuiteApiTest extends BaseTest {

    @Test(description = "Create, update and remove Suite via API")
    public void suiteShouldBeCreatedAndUpdatedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        suite.setId(suite.getId());
        suiteApi.getSuiteInfoById(project.getCode(), suite.getId());
        suite.setTitle("UpdatedTitle");
        suite.setDescription("UpdatedDescription");
        suite.setPreconditions("UpdatedPreconditions");
        suiteApi.update(project.getCode(), suite);
        suiteApi.getSuiteInfoById(project.getCode(), suite.getId());
        suiteApi.delete(project.getCode(), suite);
        projectApi.delete(project.getCode());
    }

    @Test(description = "Create Suite via API")
    public void suiteShouldBeCreatedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        suitesPage.openSuiteToEdit(suite.getTitle());
        assertEquals(suitesPage.getSuiteName(), suite.getTitle(), "Name of Suite doesn't match");
        assertEquals(suitesPage.getSuiteDescription(), suite.getDescription(), "Description of Suite doesn't match");
        assertEquals(suitesPage.getSuitePreconditions(), suite.getPreconditions(), "Preconditions of Suite doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Create and update Suite via API")
    public void suiteShouldBeEditedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        suitesPage.openSuiteToEdit(suite.getTitle());
        assertEquals(suitesPage.getSuiteName(), suite.getTitle(), "Name of Suite doesn't match");
        assertEquals(suitesPage.getSuiteDescription(), suite.getDescription(), "Description of Suite doesn't match");
        assertEquals(suitesPage.getSuitePreconditions(), suite.getPreconditions(), "Preconditions of Suite doesn't match");
        suitesPage.cancelEditSuite();
        Suite newSuite = getRandomSuite();
        suiteApi.update(project.getCode(), newSuite);
        suitesPage.openSuiteToEdit(newSuite.getTitle());
        assertEquals(suitesPage.getSuiteName(), newSuite.getTitle(), "Name of Suite doesn't match");
        assertEquals(suitesPage.getSuiteDescription(), newSuite.getDescription(), "Description of Suite doesn't match");
        assertEquals(suitesPage.getSuitePreconditions(), newSuite.getPreconditions(), "Preconditions of Suite doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Create and remove Suite via API")
    public void suiteShouldBeRemovedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.openProject(project.getTitle());
        assertTrue(suitesPage.isSuitePresentInList(suite.getTitle()), "Suite is NOT present in the list");
        suiteApi.delete(project.getCode(), suite);
        assertFalse(suitesPage.isSuitePresentInList(suite.getTitle()), "Suite is present in the list");
        projectApi.delete(project.getCode());
    }

}