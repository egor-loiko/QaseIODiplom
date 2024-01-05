package tests;

import org.testng.annotations.Test;
import utils.PropertyReader;

public class LoginTest extends BaseTest {

    @Test(description = "user should be logged in")
    public void successfulLogin() {
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), System.getProperty("password", PropertyReader.getProperty("sf.password")));
        projectsListPage.waitTillOpened();
    }
}
