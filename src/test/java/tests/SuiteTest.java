package tests;

import models.project.Project;
import models.suite.Suite;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.getRandomProject;
import static models.suite.SuiteFactory.getRandomSuite;
import static org.testng.Assert.assertTrue;

public class SuiteTest extends BaseTest {

    @Test(description = "Create new Suite for existing project")
    public void suiteShouldBeCreated() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suite);
        assertTrue(suitesPage.isSuiteCreated(suite.getTitle()), "Suite is not created");
    }

    @Test
    public void suiteShouldBeCreatedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        projectApi.create(project);
        int suiteId = suiteApi.create(project, suite);
        suite.setId(suiteId);
        suiteApi.getSuiteById(project, suite);
        suite.setTitle("UpdatedTitle");
        suite.setDescription("UpdatedDescription");
        suite.setPreconditions("UpdatedPreconditions");
        suiteApi.update(project, suite);
        suiteApi.getSuiteById(project, suite);
        suiteApi.delete(project, suite);

    }
}
