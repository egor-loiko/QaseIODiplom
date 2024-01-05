package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectPage {
    private final By PROJECT_NAME_LABEL = By.xpath("//h1");
    private final By CREATE_NEW_SUITE_BUTTON = By.id("create-suite-button");
    private final By SUITE_NAME = By.id("title");
    private final By SUITE_DESCRIPTION = By.xpath("//label[text()='Description']/../..//p");
    private final By SUITE_PRECONDITIONS = By.xpath("//label[text()='Preconditions']/../..//p");
    private final String CONFIRM_CREATE_SUITE_BUTTON_CSS = "[type=submit]";
    private final String SUITE_NAME_IN_LIST = "//a[text()='%s']";
    private final By SUITES_LABEL = By.xpath("//span[text()='Suites']");

    public boolean isProjectCreated(String projectName) {
        if ($(PROJECT_NAME_LABEL).getText().contains(projectName.toUpperCase())) {
            return true;
        }
        return false;
    }

    public void waitTillProjectCreated() {
        $(CREATE_NEW_SUITE_BUTTON).shouldBe(Condition.visible);
    }

    public void createNewSuite(String suiteName, String suiteDescription, String suitePreconditions) {
        $(CREATE_NEW_SUITE_BUTTON).click();
        $(SUITE_NAME).sendKeys(suiteName);
        $(SUITE_DESCRIPTION).sendKeys(suiteDescription);
        $(SUITE_PRECONDITIONS).sendKeys(suitePreconditions);
        $(CONFIRM_CREATE_SUITE_BUTTON_CSS).click();
    }

    public boolean isSuiteCreated(String suiteName) {
        $(SUITES_LABEL).shouldBe(Condition.visible);
        if ($$(By.xpath(String.format(SUITE_NAME_IN_LIST, suiteName))).size() != 0) {
            return true;
        }
        return false;
    }
}
