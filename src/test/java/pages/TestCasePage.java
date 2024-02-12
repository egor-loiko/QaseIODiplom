package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.cases.Case;
import models.cases.Steps;
import models.cases.fieldEnums.*;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCasePage extends BasePage {

    private final String TC_TITLE_CSS = "#title";

    @Step("Set Title of test case '{tcTitle}'")
    public void setCaseTitle(String tcTitle) {
        log.info("Setting Title of test case '{}'", tcTitle);
        $(TC_TITLE_CSS).clear();
        $(TC_TITLE_CSS).sendKeys(tcTitle);
    }

    @Step("Set value '{value}' in dropdown '{label}' for test case")
    public void setDropdownValue(String label, String value) {
        dropdown.selectValue(label, value);
    }

    @Step("Get value from dropdown '{label}' for test case")
    public String getDropdownValue(String label) {
        return dropdown.getValue(label);
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
        log.info("Getting test case title");
        $(TC_TITLE_CSS).shouldBe(Condition.visible);
        return $(TC_TITLE_CSS).getValue();
    }

    @Step("Create test case '{testCase}'")
    public void createTestCase(Case testCase) {
        log.info("Creating Test Case with name '{}'", testCase.getTitle());

        setCaseTitle(testCase.getTitle());

        setDropdownValue("Status", Status.values()[testCase.getStatus()].getName());
        setDropdownValue("Severity", Severity.values()[testCase.getSeverity()].getName());
        setDropdownValue("Priority", Priority.values()[testCase.getPriority()].getName());
        setDropdownValue("Type", Type.values()[testCase.getType()].getName());
        setDropdownValue("Layer", Layer.values()[testCase.getLayer()].getName());
        setDropdownValue("Is flaky", IsFlaky.values()[testCase.getIsFlaky()].getName());
        setDropdownValue("Behavior", Behavior.values()[testCase.getBehavior()].getName());
        setDropdownValue("Automation status", Automation.values()[testCase.getAutomation()].getName());

        if (!testCase.getSteps().isEmpty()) {
            for (Steps step : testCase.getSteps()) {
                addTcStep(step.getAction(), step.getData(), step.getExpected_result());
            }
        }

        saveTestCase();
    }

    @Step("Update test case '{testCase}'")
    public void updateTestCase(Case testCase) {
        log.info("Updating Test Case with name '{}'", testCase.getTitle());

        setCaseTitle(testCase.getTitle());

        setDropdownValue("Status", Status.values()[testCase.getStatus()].getName());
        setDropdownValue("Severity", Severity.values()[testCase.getSeverity()].getName());
        setDropdownValue("Priority", Priority.values()[testCase.getPriority()].getName());
        setDropdownValue("Type", Type.values()[testCase.getType()].getName());
        setDropdownValue("Layer", Layer.values()[testCase.getLayer()].getName());
        setDropdownValue("Is flaky", IsFlaky.values()[testCase.getIsFlaky()].getName());
        setDropdownValue("Behavior", Behavior.values()[testCase.getBehavior()].getName());
        setDropdownValue("Automation status", Automation.values()[testCase.getAutomation()].getName());

        saveTestCase();
    }

    @Step("Verify test case '{testCase}' filled correctly")
    public boolean isTestCaseVerified(Case testCase) {
        log.info("Verifying if Test Case with name '{}' filled correctly", testCase.getTitle());
        if (!getTestCaseTitle().equals(testCase.getTitle())) {
            log.error("Validation of Test case 'Title' if failed");
            return false;
        }

        if (!getDropdownValue("Status").equals(Status.values()[testCase.getStatus()].getName())) {
            log.error("Validation of Test case 'Status' dropdown if failed");
            return false;
        }

        if (!getDropdownValue("Severity").equals(Severity.values()[testCase.getSeverity()].getName())) {
            log.error("Validation of Test case 'Severity' dropdown if failed");
            return false;
        }

        if (!getDropdownValue("Priority").equals(Priority.values()[testCase.getPriority()].getName())) {
            log.error("Validation of Test case 'Priority' dropdown if failed");
            return false;
        }

        if (!getDropdownValue("Type").equals(Type.values()[testCase.getType()].getName())) {
            log.error("Validation of Test case 'Type' dropdown if failed");
            return false;
        }

        if (!getDropdownValue("Layer").equals(Layer.values()[testCase.getLayer()].getName())) {
            log.error("Validation of Test case 'Layer' dropdown if failed");
            return false;
        }

        if (!getDropdownValue("Is flaky").equals(IsFlaky.values()[testCase.getIsFlaky()].getName())) {
            log.error("Validation of Test case 'Is flaky' dropdown if failed");
            return false;
        }

        if (!getDropdownValue("Behavior").equals(Behavior.values()[testCase.getBehavior()].getName())) {
            log.error("Validation of Test case 'Behavior' dropdown if failed");
            return false;
        }

        if (!getDropdownValue("Automation status").equals(Automation.values()[testCase.getAutomation()].getName())) {
            log.error("Validation of Test case 'Automation status' dropdown if failed");
            return false;
        }

        return true;
    }

}
