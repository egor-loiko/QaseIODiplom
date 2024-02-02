package wrappers;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

@Log4j2
public class DropdownHelper {

    private final String DROPDOWN_LABEL = "//label[text()='%s']//following::div";
    private final String DROPDOWN_VALUE = "//div[text()='%s']";
    private final String GET_DROPDOWN_VALUE = "//label[text()='%s']/following::div/following::div";

    public void selectValue(String label, String value) {
        log.info("Setting value '{}' in dropdown '{}' for test case", value, label);
        $(By.xpath(String.format(DROPDOWN_LABEL, label))).shouldBe(Condition.visible);
        actions().click($(By.xpath(String.format(DROPDOWN_LABEL, label)))).perform();
        $(By.xpath(String.format(DROPDOWN_VALUE, value))).shouldBe(Condition.visible);
        actions().click($(By.xpath(String.format(DROPDOWN_VALUE, value)))).perform();
    }

    public String getValue(String label) {
        log.info("Getting value from dropdown '{}' of test case", label);
        $(By.xpath(String.format(GET_DROPDOWN_VALUE, label))).shouldBe(Condition.visible);
        return $(By.xpath(String.format(GET_DROPDOWN_VALUE, label))).getText();
    }

}
