package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.ProjectPage;
import pages.ProjectsListPage;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    Faker faker;
    LoginPage loginPage;
    ProjectsListPage projectsListPage;
    ProjectPage projectPage;

    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = PropertyReader.getProperty("sf.base.url");
        open();
        getWebDriver().manage().window().maximize();

        faker = new Faker();
        loginPage = new LoginPage();
        projectsListPage = new ProjectsListPage();
        projectPage = new ProjectPage();
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        if (getWebDriver()!= null){
            getWebDriver().quit();
        }
    }
}