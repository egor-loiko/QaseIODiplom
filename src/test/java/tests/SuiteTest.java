package tests;

import models.project.Project;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.getRandomProject;
import static org.testng.Assert.assertTrue;
import static utils.DataGenerator.generateSuiteName;

public class SuiteTest extends BaseTest {

    @Test(description = "Create new Suite for existing project")
    public void suiteShouldBeCreated() {
        Project project = getRandomProject();
        String suiteName = generateSuiteName();
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        projectsListPage.createNewProject(project);
        projectPage.waitTillProjectCreated();
        projectPage.createNewSuite(suiteName, "test2", "test1");
        assertTrue(suitesPage.isSuiteCreated(suiteName), "Suite is not created");
    }
}
