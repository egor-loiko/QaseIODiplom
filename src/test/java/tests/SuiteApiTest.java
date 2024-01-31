package tests;

import models.project.Project;
import models.suite.Suite;
import org.testng.annotations.Test;

import static models.project.ProjectFactory.getRandomProject;
import static models.suite.SuiteFactory.getRandomSuite;
import static org.testng.Assert.assertEquals;

public class SuiteApiTest extends BaseApiTest {

    @Test(description = "Create Suite via API")
    public void suiteShouldBeCreatedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        Suite suiteCreated = suiteApi.getSuiteInfoById(project.getCode(), suite.getId());
        assertEquals(suiteCreated.getTitle(), suite.getTitle(), "Name of Suite doesn't match");
        assertEquals(suiteCreated.getDescription(), suite.getDescription(), "Description of Suite doesn't match");
        assertEquals(suiteCreated.getPreconditions(), suite.getPreconditions(), "Preconditions of Suite doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Update Suite via API")
    public void suiteShouldBeUpdatedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        Suite suiteToUpdate = getRandomSuite();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        Suite suiteCreated = suiteApi.getSuiteInfoById(project.getCode(), suite.getId());
        assertEquals(suiteCreated.getTitle(), suite.getTitle(), "Name of Suite doesn't match");
        assertEquals(suiteCreated.getDescription(), suite.getDescription(), "Description of Suite doesn't match");
        assertEquals(suiteCreated.getPreconditions(), suite.getPreconditions(), "Preconditions of Suite doesn't match");
        suiteApi.update(project.getCode(), suiteToUpdate);
        Suite suiteUpdated = suiteApi.getSuiteInfoById(project.getCode(), suiteToUpdate.getId());
        assertEquals(suiteUpdated.getTitle(), suiteToUpdate.getTitle(), "Name of Suite doesn't match");
        assertEquals(suiteUpdated.getDescription(), suiteToUpdate.getDescription(), "Description of Suite doesn't match");
        assertEquals(suiteUpdated.getPreconditions(), suiteToUpdate.getPreconditions(), "Preconditions of Suite doesn't match");
        projectApi.delete(project.getCode());
    }

    @Test(description = "Remove Suite via API")
    public void suiteShouldBeRemovedViaApi() {
        Project project = getRandomProject();
        Suite suite = getRandomSuite();
        projectApi.create(project);
        suiteApi.create(project.getCode(), suite);
        Suite suiteCreated = suiteApi.getSuiteInfoById(project.getCode(), suite.getId());
        assertEquals(suiteCreated.getTitle(), suite.getTitle(), "Name of Suite doesn't match");
        assertEquals(suiteCreated.getDescription(), suite.getDescription(), "Description of Suite doesn't match");
        assertEquals(suiteCreated.getPreconditions(), suite.getPreconditions(), "Preconditions of Suite doesn't match");
        suiteApi.delete(project.getCode(), suite);
        assertEquals(suiteApi.getSuiteErrorInfoById(project.getCode(), suite.getId()), "Suite not found", "Suite is NOT removed");
        projectApi.delete(project.getCode());
    }

}