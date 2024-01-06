package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Log4j2
public class ProjectPage {
    private final By PROJECT_NAME_LABEL = By.xpath("//h1");
    private final By CREATE_NEW_SUITE_BUTTON = By.id("create-suite-button");
    private final By SUITE_NAME = By.id("title");
    private final By SUITE_DESCRIPTION = By.xpath("//label[text()='Description']/../..//p");
    private final By SUITE_PRECONDITIONS = By.xpath("//label[text()='Preconditions']/../..//p");
    private final String CONFIRM_CREATE_SUITE_BUTTON_CSS = "[type=submit]";
    private final String SUITE_NAME_IN_LIST = "//a[text()='%s']";
    private final By SUITES_LABEL = By.xpath("//span[text()='Suites']");

    @Step("Checking project with Name '{projectName} is created' ")
    public boolean isProjectCreated(String projectName) {
        if ($(PROJECT_NAME_LABEL).getText().contains(projectName.toUpperCase())) {
            log.info("Project with Name '{}' is created", projectName);
            return true;
        }
        log.error("Project with Name '{}' is NOT created", projectName);
        return false;
    }

    @Step("Waiting for project creation")
    public void waitTillProjectCreated() {
        $(CREATE_NEW_SUITE_BUTTON).shouldBe(Condition.visible);
        log.info("Waiting for Project creation is finished");
    }

    @Step("Creating new suite with Name '{projectName}', Description '{suiteDescription}' and Preconditions '{suitePreconditions}'")
    public void createNewSuite(String suiteName, String suiteDescription, String suitePreconditions) {
        $(CREATE_NEW_SUITE_BUTTON).click();
        $(SUITE_NAME).sendKeys(suiteName);
        $(SUITE_DESCRIPTION).sendKeys(suiteDescription);
        $(SUITE_PRECONDITIONS).sendKeys(suitePreconditions);
        $(CONFIRM_CREATE_SUITE_BUTTON_CSS).click();
        log.info("New suite with Name '{}', Description '{}' and Preconditions '{}' has been created", suiteName, suiteDescription, suitePreconditions);
    }

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
}
