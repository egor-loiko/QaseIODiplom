package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LoginTest extends BaseTest {

    @Test(description = "Successful login")
    public void successfulLogin() {
        loginPage.openPage();
        loginPage.login(validUser, validPassword);
        projectsListPage.waitTillOpened();
        assertTrue(projectsListPage.isProjectsPageOpened(), "User is not logged in");
    }

    @Test(description = "Unsuccessful login with empty input")
    public void emptyInputLogin() {
        loginPage.openPage();
        loginPage.login("", "");
        assertEquals(loginPage.getEmailErrorMessage(), "This field is required", "No error for Email field");
        assertEquals(loginPage.getPasswordErrorMessage(), "This field is required", "No error for Password field");
    }

    @Test(description = "Unsuccessful login with empty password")
    public void emptyPasswordLogin() {
        loginPage.openPage();
        loginPage.login(validUser, "");
        assertEquals(loginPage.getPasswordErrorMessage(), "This field is required", "No error for Password field");
    }

    @Test(description = "Unsuccessful login with empty email")
    public void emptyEmailLogin() {
        loginPage.openPage();
        loginPage.login("", validPassword);
        assertEquals(loginPage.getEmailErrorMessage(), "This field is required", "No error for Email field");
    }

    @Test(description = "Unsuccessful login with invalid password")
    public void invalidPasswordLogin() {
        loginPage.openPage();
        loginPage.login(validUser, "123456789Qase");
        assertEquals(loginPage.getAlertMessageText(), "These credentials do not match our records.", "No Alert for invalid password");
    }

    @Test(description = "Unsuccessful login with invalid Email")
    public void invalidEmailLogin() {
        loginPage.openPage();
        loginPage.login("test@test.com", validPassword);
        assertEquals(loginPage.getAlertMessageText(), "These credentials do not match our records.", "No Alert for invalid email");
    }

}
