package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.*;
import pages.*;
import utils.PropertyReader;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    Faker faker;
    LoginPage loginPage;
    ProjectsListPage projectsListPage;
    ProjectPage projectPage;
    SuitesPage suitesPage;
    TestCasePage testCasePage;

    String validUser;
    String validPassword;

    @Parameters({"browser"})
    @BeforeMethod(description = "Browser setup")
    public void setup(@Optional("chrome") String browser) {
        log.info("Setup '{}' browser", browser);
        if (browser.equalsIgnoreCase("chrome")) {
            Configuration.browser = "chrome";

        } else if (browser.equalsIgnoreCase("firefox")) {
            Configuration.browser = "firefox";
        }
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = PropertyReader.getProperty("qaseio.base.url");
        open();
        getWebDriver().manage().window().maximize();

        faker = new Faker();
        loginPage = new LoginPage();
        projectsListPage = new ProjectsListPage();
        projectPage = new ProjectPage();
        suitesPage = new SuitesPage();
        testCasePage = new TestCasePage();

        validUser = System.getProperty("user", PropertyReader.getProperty("qaseio.user"));
        validPassword = System.getProperty("password", PropertyReader.getProperty("qaseio.password"));
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }
}