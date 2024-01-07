package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


@Log4j2
public class LoginPage {

    private final String EMAIL_CSS = "[name=email]";
    private final String PASS_CSS = "[name=password]";
    private final String SUBMIT_BTN_CSS = "[type=submit]";
    private final By EMAIL_ERROR_MESSAGE = By.xpath("//input[@name='email']//..//..//small");
    private final By PASSWORD_ERROR_MESSAGE = By.xpath("//input[@name='password']//..//..//small");
    private final By ALERT_MESSAGE = By.xpath("//div[@role='alert']//span/span");

    @Step("Open Login page")
    public void openPage() {
        log.info("Opening Login page '{}'", PropertyReader.getProperty("qaseio.base.url") + "/login");
        open("/login");
    }

    @Step("Login by user '{user}' with password '{password}'")
    public void login(String user, String password) {
        log.info("Login by '{}' with password '{}'", user, password);
        $(EMAIL_CSS).sendKeys(user);
        $(PASS_CSS).sendKeys(password);
        $(SUBMIT_BTN_CSS).click();
    }

    @Step("Getting error message for 'Email' field")
    public String getEmailErrorMessage() {
        String emailFieldErrorMessage = $(EMAIL_ERROR_MESSAGE).getText();
        log.info("Getting error message '{}' for Email field", emailFieldErrorMessage);
        return emailFieldErrorMessage;
    }

    @Step("Getting error message for 'Password' field")
    public String getPasswordErrorMessage() {
        String passwordFieldErrorMessage = $(PASSWORD_ERROR_MESSAGE).getText();
        log.info("Getting error message '{}' for Password", passwordFieldErrorMessage);
        return passwordFieldErrorMessage;
    }

    @Step("Getting alert message text")
    public String getAlertMessageText() {
        String alertMessageText = $(ALERT_MESSAGE).getText();
        log.info("Getting alert message text '{}' for invalid login", alertMessageText);
        return alertMessageText;
    }
}
