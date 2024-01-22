package tests;

import adapters.CaseApi;
import adapters.ProjectApi;
import adapters.SuiteApi;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
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
    ProjectApi projectApi;
    SuiteApi suiteApi;
    CaseApi caseApi;

    String validUser;
    String validPassword;

    @Parameters({"browser"})
    @BeforeMethod(description = "Browser setup")
    public void setup(@Optional("chrome") String browser) {
        log.info("Setup '{}' browser", browser);
        if (browser.equalsIgnoreCase("chrome")) {
            Configuration.browser = "chrome";
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=en_US");
            Configuration.browserCapabilities = options;
        } else if (browser.equalsIgnoreCase("firefox")) {
            Configuration.browser = "firefox";
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("intl.locale.requested","en-GB");
            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(profile);
            Configuration.browserCapabilities = options;
        }
        Configuration.headless = true;
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
        projectApi = new ProjectApi();
        suiteApi = new SuiteApi();
        caseApi = new CaseApi();

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