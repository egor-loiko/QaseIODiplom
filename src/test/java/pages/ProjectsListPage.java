package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
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
    private final String DESCRIPTION_CSS = "[id=description-area]";
    private final String CONFIRM_CREATE_PROJECT_BUTTON_CSS = "[type=submit]";
    private final String ACTION_MENU_FOR_PROJECT = "//a[text()='%s']//ancestor::tr//span";
    private final By DELETE_BUTTON = By.xpath("//button[@role='menuitem']");
    private final By CONFIRM_DELETE_BUTTON = By.xpath("//span[text()='Delete project']");
    private final By PROJECTS_LIST = By.xpath("//div[@id='application-content']//tbody//tr//td[3]//a");

    public void openPage() {
        log.info("Opening Login page '{}'", PropertyReader.getProperty("qaseio.base.url") + "/projects");
        open("/projects");
    }

    @Step("Wait for possibility to create new project")
    public void waitTillOpened() {
        log.info("Checking possibility to create new project");
        $(CREATE_NEW_PROJECT_BUTTON).shouldBe(Condition.visible);
    }

    @Step("Is Projects page opened")
    public boolean isProjectsPageOpened() {
        log.info("Checking is Projects page opened");
        return $(PROJECTS_LABEL).isDisplayed();
    }

    @Step("Creating new project with Name '{projectName}', Code '{projectCode}' and Description '{description}'")
    public void createNewProject(String projectName, String projectCode, String description) {
        log.info("Creating new project with Name '{}', Code '{}' and Description '{}'", projectName, projectCode, description);
        //$(CREATE_NEW_PROJECT_BUTTON).click();
        button.clickButton("Create new project");
        $(PROJECT_NAME_CSS).sendKeys(projectName);
        $(DESCRIPTION_CSS).sendKeys(description);
        $(PROJECT_CODE_CSS).shouldBe(Condition.visible).click();
        $(PROJECT_CODE_CSS).clear();
        $(PROJECT_CODE_CSS).sendKeys(projectCode);
        //$(CONFIRM_CREATE_PROJECT_BUTTON_CSS).click();
        button.clickButton("Create project");
    }

    @Step("Is project with Name '{projectName} in the list of projects'")
    public boolean isProjectInList(String projectName) {
        getWebDriver().navigate().refresh();
        for (WebElement element : $$(PROJECTS_LIST)) {
            System.out.println(element.getText());
            if (element.getText().equals(projectName))
                log.info("Project with Name '{}' is in the list of projects", projectName);
            return true;
        }
        log.error("Project with Name '{}' is NOT in the list of projects", projectName);
        return false;
    }

    @Step("Opening project with Name '{projectName}'")
    public void openProject(String projectName) {
        log.info("Opening project with Name '{}'", projectName);
        $(byText(projectName)).click();
    }

    @Step("Delete project with Name '{projectName}'")
    public void deleteProject(String projectName) {
        log.info("Removing project with Name '{}'", projectName);
        $(By.xpath(String.format(ACTION_MENU_FOR_PROJECT, projectName))).click();
        //$(DELETE_BUTTON).click();
        button.clickButton("Remove");
        button.clickButton("Delete project");
        //$(CONFIRM_DELETE_BUTTON).click();
    }

    @Step("Getting validation message text for Project Name field")
    public String gettingProjectNameFieldValidationMessage() {
        String validationMessageText = $(PROJECT_NAME_CSS).getAttribute("validationMessage");
        log.info("Getting validation message text '{}' for Project Name field", validationMessageText);
        return validationMessageText;
    }

    @Step("Getting validation message text for Project Code field")
    public String gettingProjectCodeFieldValidationMessage() {
        String validationMessageText = $(PROJECT_CODE_CSS).getAttribute("validationMessage");
        log.info("Getting validation message text '{}' for Project Code field", validationMessageText);
        return validationMessageText;
    }

}
