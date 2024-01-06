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
        log.info("Waiting for Project creation is finished");
        $(CREATE_NEW_SUITE_BUTTON).shouldBe(Condition.visible);
    }

    @Step("Creating new suite with Name '{projectName}', Description '{suiteDescription}' and Preconditions '{suitePreconditions}'")
    public void createNewSuite(String suiteName, String suiteDescription, String suitePreconditions) {
        log.info("Creating new suite with Name '{}', Description '{}' and Preconditions '{}'", suiteName, suiteDescription, suitePreconditions);
        $(CREATE_NEW_SUITE_BUTTON).click();
        $(SUITE_NAME).sendKeys(suiteName);
        $(SUITE_DESCRIPTION).sendKeys(suiteDescription);
        $(SUITE_PRECONDITIONS).sendKeys(suitePreconditions);
        $(CONFIRM_CREATE_SUITE_BUTTON_CSS).click();
    }

}
