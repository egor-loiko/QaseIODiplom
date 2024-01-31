package tests;

import adapters.CaseApi;
import adapters.ProjectApi;
import adapters.SuiteApi;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.*;
import utils.TestListener;


@Log4j2
@Listeners(TestListener.class)
public class BaseApiTest {

    Faker faker;
    ProjectApi projectApi;
    SuiteApi suiteApi;
    CaseApi caseApi;

    @BeforeMethod(description = "Prepare API test data")
    public void setup() {
        log.info("Preparation API test data");
        faker = new Faker();
        projectApi = new ProjectApi();
        suiteApi = new SuiteApi();
        caseApi = new CaseApi();
    }

}