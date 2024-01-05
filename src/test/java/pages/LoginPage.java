package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage {

    final String EMAIL_CSS = "[name=email]";
    final String PASS_CSS = "[name=password]";
    final String SUBMIT_BTN_CSS = "[type=submit]";

    @Step("Open Login page")
    public void openPage() {
        log.info("Open Login page '{}'", PropertyReader.getProperty("sf.base.url") + "/login");
        open("/login");
    }

    @Step("Login by '{user}' with password '{password}'")
    public void login(String user, String password) {
        log.info("Login by '{}' with password '{}'", user, password);
        $(EMAIL_CSS).sendKeys(user);
        $(PASS_CSS).sendKeys(password);
        $(SUBMIT_BTN_CSS).click();

    }
}
