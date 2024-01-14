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

    @Step("Checking project with Name '{projectName} is created' ")
    public boolean isProjectCreated(String projectName) {
        return !$$(By.xpath(String.format(PROJECT_NAME_LABEL, projectName))).isEmpty();
    }

    @Step("Waiting for project creation")
    public void waitTillProjectCreated() {
        log.info("Waiting for Project creation is finished");
        $(CREATE_NEW_SUITE_BUTTON).shouldBe(Condition.visible);
    }

    @Step("Creating new suite with Name '{suite.getTitle()}', Description '{suite.getDescription()}' and Preconditions '{suite.getPreconditions()}'")
    public void createNewSuite(Suite suite) {
        log.info("Creating new suite with Name '{}', Description '{}' and Preconditions '{}'", suite.getTitle(), suite.getDescription(), suite.getPreconditions());
        button.clickButton("Suite");
        $(SUITE_NAME).sendKeys(suite.getTitle());
        $(SUITE_DESCRIPTION).sendKeys(suite.getDescription());
        $(SUITE_PRECONDITIONS).sendKeys(suite.getPreconditions());
        button.clickButton("Create");
    }

}
