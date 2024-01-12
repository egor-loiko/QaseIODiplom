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

}
