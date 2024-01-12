package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Dropdown {

    private final String DROPDOWN_LABEL = "//label[text()='%s']//following::div";
    private final String DROPDOWN_VALUE = "//div[text()='%s']";

    public void selectValue(String label, String value) {
        log.info("Setting value '{}' in dropdown '{}' for test case", value, label);
        $(By.xpath(String.format(DROPDOWN_LABEL, label))).click();
        $(By.xpath(String.format(DROPDOWN_VALUE, value))).click();
    }

}
