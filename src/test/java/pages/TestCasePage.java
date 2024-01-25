package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCasePage extends BasePage {

    private final String TC_TITLE_CSS = "#title";

    @Step("Set Title of test case '{tcTitle}'")
    public void setCaseTitle(String tcTitle) {
        log.info("Setting Title of test case '{}'", tcTitle);
        $(TC_TITLE_CSS).sendKeys(tcTitle);
    }

    @Step("Set value '{value}' in dropdown '{label}' for test case")
    public void setDropdownValue(String label, String value) {
        dropdown.selectValue(label, value);
    }

    @Step("Add Test Case step with Step Action '{stepAction}' Data '{data}' and Expected Result '{expectedResult}'")
    public void addTcStep(String stepAction, String data, String expectedResult) {
        log.info("Adding Test Case step with Step Action '{}' Data '{}' and Expected Result '{}'", stepAction, data, expectedResult);
        button.click("Add step");
        stepField.fill("Step Action", stepAction);
        stepField.fill("Data", data);
        stepField.fill("Expected result", expectedResult);
    }

    @Step("Save Test Case")
    public void saveTestCase() {
        log.info("Saving Test Case");
        button.click("Save");
    }

    @Step("Get test case title")
    public String getTestCaseTitle() {
        $(TC_TITLE_CSS).shouldBe(Condition.visible);
        return $(TC_TITLE_CSS).getValue();
    }

}
