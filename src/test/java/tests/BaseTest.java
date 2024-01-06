package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProjectPage;
import pages.ProjectsListPage;
import pages.SuitesPage;
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

    @Parameters({"browser"})
    @BeforeMethod(description = "Browser setup")
    public void setup(@Optional("chrome") String browser, ITestContext iTestContext) {
        log.info("Setup '{}' browser", browser);
        if (browser.equalsIgnoreCase("chrome")) {
            Configuration.browser = "chrome";

        } else if (browser.equalsIgnoreCase("firefox")) {
            Configuration.browser = "firefox";
        }
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = PropertyReader.getProperty("sf.base.url");
        open();
        getWebDriver().manage().window().maximize();
        iTestContext.setAttribute("driver", getWebDriver());

        faker = new Faker();
        loginPage = new LoginPage();
        projectsListPage = new ProjectsListPage();
        projectPage = new ProjectPage();
        suitesPage = new SuitesPage();
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }
}