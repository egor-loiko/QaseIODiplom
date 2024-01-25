package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class SuitesPage extends BasePage {

    private final String SUITE_NAME_IN_LIST = "//a[text()='%s']";
    private final By SUITES_LABEL = By.xpath("//span[text()='Suites']");
    private final String SUITE_PLUS_BUTTON_CSS = ".fas.fa-plus";
    private final By ALERT_MESSAGE = By.xpath("//div[@role='alert']//span/span");
    private final String REMOVE_SUITE_ICON_CSS = ".far.fa-trash";
    private final String EDIT_SUITE_ICON_CSS = ".far.fa-pencil";
    private final By SUITE_NAME = By.id("title");
    private final By SUITE_DESCRIPTION = By.xpath("//label[text()='Description']/../..//p[@data-nodeid]");
    private final By SUITE_PRECONDITIONS = By.xpath("//label[text()='Preconditions']/../..//p[@data-nodeid]");
    private final String TEST_CASE_NAME_CSS = "//div[@data-suite-body-id]//div[text()='%s']";
    private final By TEST_CASES_LIST = By.xpath("//div[@data-suite-body-id]/div[5]");


    @Step("Check suite with Name '{suiteName}' is created")
    public boolean isSuitePresentInList(String suiteName) {
        getWebDriver().navigate().refresh();
        button.wait("Suite");

        if ($$(By.xpath(String.format(SUITE_NAME_IN_LIST, suiteName))).size() != 0) {
            log.info("Suite with Name '{}' is present in the list", suiteName);
            return true;
        }
        log.info("Suite with Name '{}' is NOT present in the list", suiteName);
        return false;
    }

    @Step("Open create new test case page")
    public void openCreateNewTestCasePage() {
        $(SUITE_PLUS_BUTTON_CSS).click();
        button.click("Create case");
    }

    @Step("Get successful test case creation message text")
    public String getTestCaseCreationMessageText() {
        String alertMessageText = $(ALERT_MESSAGE).getText();
        log.info("Getting message text '{}' for successful Test Case creation", alertMessageText);
        return alertMessageText;
    }

    @Step("Remove suite")
    public void removeSuite() {
        log.info("Removing suite");
        $(REMOVE_SUITE_ICON_CSS).click();
        button.click("Delete");
    }

    @Step("Open suite for Editing")
    public void openSuiteToEdit() {
        log.info("Opening suite for Editing");
        getWebDriver().navigate().refresh();
        $(EDIT_SUITE_ICON_CSS).shouldBe(Condition.visible);
        $(EDIT_SUITE_ICON_CSS).click();
    }

    @Step("Save and close suite Edit window")
    public void saveEditSuite() {
        log.info("Save and close suite Edit window");
        button.click("Save");
    }

    @Step("Cancel and close suite Edit window")
    public void cancelEditSuite() {
        log.info("Cancel and close suite Edit window");
        button.click("Cancel");
    }

    @Step("Get name of Suite")
    public String getSuiteName() {
        log.info("Getting name of Suite");
        return $(SUITE_NAME).getValue();
    }

    @Step("Get description of Suite")
    public String getSuiteDescription() {
        log.info("Getting description of Suite");
        return $(SUITE_DESCRIPTION).getOwnText();
    }

    @Step("Get preconditions of Suite")
    public String getSuitePreconditions() {
        log.info("Getting preconditions of Suite");
        return $(SUITE_PRECONDITIONS).getOwnText();
    }

    @Step("Open test case with name '{testCaseName}' for Editing")
    public void openTestCaseForEdit(String testCaseName) {
        log.info("Opening test case with name '{}' for editing", testCaseName);
        $(SUITES_LABEL).shouldBe(Condition.visible);
        $(By.xpath(String.format(TEST_CASE_NAME_CSS, testCaseName))).click();
        button.click("Edit");
    }

    @Step("Check test case with Name '{testCaseName}' is created")
    public boolean isTestCasePresentInList(String testCaseName) {
        getWebDriver().navigate().refresh();
        button.wait("Suite");

        for (WebElement element : $$(TEST_CASES_LIST)) {
            if (element.getText().equals(testCaseName)) {
                log.info("Test Case with Name '{}' is present in the list", testCaseName);
                return true;
            }
        }
        log.info("Test Case with Name '{}' is NOT present in the list", testCaseName);
        return false;
    }

}
