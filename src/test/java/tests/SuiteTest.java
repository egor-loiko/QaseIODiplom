package tests;

import models.project.Project;
import models.suite.Suite;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.getRandomProject;
import static models.suite.SuiteFactory.getRandomSuite;
import static org.testng.Assert.*;

public class SuiteTest extends BaseTest {

    @Test(description = "Create Suite")
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
        projectApi.delete(project.getCode());
    }

    @Test(description = "Remove Suite")
    public void suiteShouldBeRemoved() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createProject(project);
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suite);
        assertTrue(suitesPage.isSuitePresentInList(suite.getTitle()), "Suite is NOT present in the list");
        suitesPage.removeSuite();
        assertFalse(suitesPage.isSuitePresentInList(suite.getTitle()), "Suite is present in the list");
        projectApi.delete(project.getCode());
    }

}
