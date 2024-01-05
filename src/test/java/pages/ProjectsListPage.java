package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProjectsListPage {

    private final By CREATE_NEW_PROJECT_BUTTON = By.id("createButton");
    private final String PROJECT_NAME_CSS = "[id=project-name]";
    private final String PROJECT_CODE_CSS = "[id=project-code]";
    private final String DESCRIPTION_CSS = "[id=description-area]";
    private final String CONFIRM_CREATE_PROJECT_BUTTON_CSS = "[type=submit]";
    private final String ACTION_MENU_FOR_PROJECT = "//a[text()='%s']//ancestor::tr//span";
    private final By DELETE_BUTTON = By.xpath("//button[@role='menuitem']");
    private final By CONFIRM_DELETE_BUTTON = By.xpath("//span[text()='Delete project']");
    private final By PROJECTS_LIST = By.xpath("//div[@id='application-content']//tbody//tr//td[3]//a");


    public void openPage() {
        open("/projects");
    }

    public void waitTillOpened() {
        $(CREATE_NEW_PROJECT_BUTTON).shouldBe(Condition.visible);
    }

    public void createNewProject(String projectName, String projectCode, String description) {
        $(CREATE_NEW_PROJECT_BUTTON).click();
        $(PROJECT_NAME_CSS).sendKeys(projectName);
        $(PROJECT_CODE_CSS).clear();
        $(PROJECT_CODE_CSS).sendKeys(projectCode);
        $(DESCRIPTION_CSS).sendKeys(description);
        $(CONFIRM_CREATE_PROJECT_BUTTON_CSS).click();
    }

    public boolean isProjectInList(String projectName) {
        getWebDriver().navigate().refresh();
        for (WebElement element : $$(PROJECTS_LIST)) {
            System.out.println(element.getText());
            if (element.getText().equals(projectName))
                return true;
        }
        return false;
    }

    public void openProject(String projectName) {
        $(byText(projectName)).click();
    }

    public void deleteProject(String projectName) {
        $(By.xpath(String.format(ACTION_MENU_FOR_PROJECT, projectName))).click();
        $(DELETE_BUTTON).click();
        $(CONFIRM_DELETE_BUTTON).click();
    }

}
