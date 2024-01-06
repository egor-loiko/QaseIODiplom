package tests;

import org.testng.annotations.Test;
import utils.PropertyReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(description = "user should be logged in")
    public void successfulLogin() {
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), System.getProperty("password", PropertyReader.getProperty("sf.password")));
        projectsListPage.waitTillOpened();
        assertTrue(projectsListPage.isProjectsPageOpened(), "User is not logged in");
    }

    @Test(description = "unsuccessful login with empty input")
    public void emptyInputLogin() {
        loginPage.openPage();
        loginPage.login("", "");
        assertEquals(loginPage.getEmailErrorMessage(), "This field is required", "No error for Email field");
        assertEquals(loginPage.getPasswordErrorMessage(), "This field is required", "No error for Password field");
    }

    @Test(description = "unsuccessful login with empty password")
    public void emptyPasswordLogin() {
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), "");
        assertEquals(loginPage.getPasswordErrorMessage(), "This field is required", "No error for Password field");
    }

    @Test(description = "unsuccessful login with empty email")
    public void emptyEmailLogin() {
        loginPage.openPage();
        loginPage.login("", System.getProperty("password", PropertyReader.getProperty("sf.password")));
        assertEquals(loginPage.getEmailErrorMessage(), "This field is required", "No error for Email field");
    }

    @Test(description = "unsuccessful login with invalid password")
    public void invalidPasswordLogin() {
        loginPage.openPage();
        loginPage.login(System.getProperty("user", PropertyReader.getProperty("sf.user")), "123456789Qase");
        assertEquals(loginPage.getAlertMessageText(), "These credentials do not match our records.", "No Alert for invalid password");
    }

    @Test(description = "unsuccessful login with invalid Email")
    public void invalidEmailLogin() {
        loginPage.openPage();
        loginPage.login("test@test.com", System.getProperty("password", PropertyReader.getProperty("sf.password")));
        assertEquals(loginPage.getAlertMessageText(), "These credentials do not match our records.", "No Alert for invalid email");
    }
}
