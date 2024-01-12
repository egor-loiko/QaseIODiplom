package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCasePage extends BasePage {

    private final String TC_TITLE_CSS = "#title";

    @Step("Setting Title of test case '{tcTitle}'")
    public void setCaseTitle(String tcTitle) {
        log.info("Setting Title of test case '{}'", tcTitle);
        $(TC_TITLE_CSS).sendKeys(tcTitle);
    }

    @Step("Setting value '{value}' in dropdown '{label}' for test case")
    public void setDropdownValue(String label, String value) {
        dropdown.selectValue(label, value);
    }

    @Step("Adding Test Case step with Step Action '{stepAction}' Data '{data}' and Expected Result '{expectedResult}'")
    public void addTcStep(String stepAction, String data, String expectedResult) {
        log.info("Adding Test Case step with Step Action '{}' Data '{}' and Expected Result '{}'", stepAction, data, expectedResult);
        button.clickButton("Add step");
        stepField.fillInStepField("Step Action", stepAction);
        stepField.fillInStepField("Data", data);
        stepField.fillInStepField("Expected result", expectedResult);
    }

    @Step("Saving Test Case")
    public void saveTestCase() {
        log.info("Saving Test Case");
        button.clickButton("Save");
    }

}
