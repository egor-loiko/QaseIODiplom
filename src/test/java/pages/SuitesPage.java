package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Log4j2
public class SuitesPage {

    private final String SUITE_NAME_IN_LIST = "//a[text()='%s']";
    private final By SUITES_LABEL = By.xpath("//span[text()='Suites']");

    private final String SUITE_PLUS_BUTTON_CSS = "[class='fas fa-plus']";
    private final By CREATE_TEST_CASE_BUTTON = By.xpath("//a[text()='Create case']");


    @Step("Checking suite with Name '{suiteName}' is created")
    public boolean isSuiteCreated(String suiteName) {
        $(SUITES_LABEL).shouldBe(Condition.visible);
        if ($$(By.xpath(String.format(SUITE_NAME_IN_LIST, suiteName))).size() != 0) {
            log.info("Suite with Name '{}' is created", suiteName);
            return true;
        }
        log.info("Suite with Name '{}' is NOT created", suiteName);
        return false;
    }

    @Step("Opening create new test case page")
    public void openCreateNewCasePage() {
        $(SUITE_PLUS_BUTTON_CSS).click();
        $(CREATE_TEST_CASE_BUTTON).click();
    }
}