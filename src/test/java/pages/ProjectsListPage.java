package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.project.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.PropertyReader;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


@Log4j2
public class ProjectsListPage extends BasePage {

    private final By CREATE_NEW_PROJECT_BUTTON = By.id("createButton");
    private final String PROJECT_NAME_CSS = "[id=project-name]";
    private final By PROJECTS_LABEL = By.xpath("//h1[text()='Projects']");
    private final String PROJECT_CODE_CSS = "[id=project-code]";
    private final String PROJECT_DESCRIPTION_CSS = "[id=description-area]";
    private final String ACTION_MENU_FOR_PROJECT = "//a[text()='%s']//ancestor::tr//span";
    private final By PROJECTS_LIST = By.xpath("//div[@id='application-content']//tbody//tr//td[3]//a");
    private final By ROWS_PER_PAGE = By.xpath("//label[text()='Rows per page:']");

    @Step("Open Project list page")
    public void openPage() {
        log.info("Opening Projects page '{}'", PropertyReader.getProperty("qaseio.base.url") + "/projects");
        open("/projects");
    }

    @Step("Wait for page with list of Projects is opened")
    public void waitTillOpened() {
        log.info("Waiting for page with list of Projects is opened");
        $(CREATE_NEW_PROJECT_BUTTON).shouldBe(Condition.visible);
    }

    @Step("Is Projects page opened")
    public boolean isProjectsPageOpened() {
        log.info("Checking is Projects page opened");
        return $(PROJECTS_LABEL).isDisplayed();
    }

    @Step("Create new project '{project}'")
    public void createNewProject(Project project) {
        log.info("Creating new project with Name '{}', Code '{}' and Description '{}'", project.getTitle(), project.getCode(), project.getDescription());
        button.clickButton("Create new project");
        $(PROJECT_NAME_CSS).sendKeys(project.getTitle());
        $(PROJECT_DESCRIPTION_CSS).sendKeys(project.getDescription());
        $(PROJECT_CODE_CSS).shouldBe(Condition.visible).click();
        $(PROJECT_CODE_CSS).clear();
        $(PROJECT_CODE_CSS).clear();
        $(PROJECT_CODE_CSS).sendKeys(project.getCode());
        button.clickButton("Create project");
    }

    @Step("Is project with Name '{projectName} in the list of projects'")
    public boolean isProjectInList(String projectName) {
        log.info("Starting of checking is project with Name '{}' in the list of projects", projectName);
        getWebDriver().navigate().refresh();
        $(ROWS_PER_PAGE).shouldBe(Condition.visible);
        for (WebElement element : $$(PROJECTS_LIST)) {
            if (element.getText().equals(projectName)) {
                log.info("Project with Name '{}' is in the list of projects", projectName);
                return true;
            }
        }
        log.info("Project with Name '{}' is NOT in the list of projects", projectName);
        return false;
    }

    @Step("Open project with Name '{projectName}'")
    public void openProject(String projectName) {
        log.info("Opening project with Name '{}'", projectName);
        $(byText(projectName)).click();
    }

    @Step("Delete project with Name '{projectName}'")
    public void deleteProject(String projectName) {
        log.info("Removing project with Name '{}'", projectName);
        $(By.xpath(String.format(ACTION_MENU_FOR_PROJECT, projectName))).click();
        button.clickButton("Remove");
        button.clickButton("Delete project");
    }

    @Step("Get validation message text for Project Name field")
    public String gettingProjectNameFieldValidationMessage() {
        String validationMessageText = $(PROJECT_NAME_CSS).getAttribute("validationMessage");
        log.info("Getting validation message text '{}' for Project Name field", validationMessageText);
        return validationMessageText;
    }

    @Step("Get validation message text for Project Code field")
    public String gettingProjectCodeFieldValidationMessage() {
        String validationMessageText = $(PROJECT_CODE_CSS).getAttribute("validationMessage");
        log.info("Getting validation message text '{}' for Project Code field", validationMessageText);
        return validationMessageText;
    }

}
