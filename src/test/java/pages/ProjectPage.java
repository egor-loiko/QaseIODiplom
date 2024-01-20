package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.suite.Suite;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


@Log4j2
public class ProjectPage extends BasePage {

    private final String PROJECT_NAME_LABEL = "//div[text()='%s']";
    private final By CREATE_NEW_SUITE_BUTTON = By.id("create-suite-button");
    private final By SUITE_NAME = By.id("title");
    private final By SUITE_DESCRIPTION = By.xpath("//label[text()='Description']/../..//p");
    private final By SUITE_PRECONDITIONS = By.xpath("//label[text()='Preconditions']/../..//p");
    private final String PROJECT_NAME_CSS = "[id=project-name]";
    private final String PROJECT_CODE_CSS = "[id=project-code]";
    private final String PROJECT_DESCRIPTION_CSS = "[id=description-area]";
    private final By PROJECT_SETTINGS_LABEL = By.xpath("//h1[text()='Project settings']");

    @Step("Check project with Name '{projectName} is created' ")
    public boolean isProjectCreated(String projectName) {
        return !$$(By.xpath(String.format(PROJECT_NAME_LABEL, projectName))).isEmpty();
    }

    @Step("Wait for project creation")
    public void waitTillProjectCreated() {
        log.info("Waiting for Project creation is finished");
        $(CREATE_NEW_SUITE_BUTTON).shouldBe(Condition.visible);
    }

    @Step("Create new suite '{suite}'")
    public void createNewSuite(Suite suite) {
        log.info("Creating new suite with Name '{}', Description '{}' and Preconditions '{}'", suite.getTitle(), suite.getDescription(), suite.getPreconditions());
        button.clickButton("Suite");
        $(SUITE_NAME).sendKeys(suite.getTitle());
        $(SUITE_DESCRIPTION).sendKeys(suite.getDescription());
        $(SUITE_PRECONDITIONS).sendKeys(suite.getPreconditions());
        button.clickButton("Create");
    }

    @Step("Open project settings")
    public void openProjectSettings() {
        log.info("Opening project settings");
        button.clickButton("Settings");
    }

    @Step("Get name of Project")
    public String getProjectName() {
        log.info("Getting name of Project");
        return $(PROJECT_NAME_CSS).getValue();
    }

    @Step("Get code of Project")
    public String getProjectCode() {
        log.info("Getting code of Project");
        return $(PROJECT_CODE_CSS).getValue();
    }

    @Step("Get description of Project")
    public String getProjectDescription() {
        log.info("Getting description of Project");
        return $(PROJECT_DESCRIPTION_CSS).getValue();
    }

    @Step("Wait for Project settings page is opened")
    public void waitTillSettingsOpened() {
        log.info("Waiting for Project settings page is opened");
        $(PROJECT_SETTINGS_LABEL).shouldBe(Condition.visible);
    }

}
