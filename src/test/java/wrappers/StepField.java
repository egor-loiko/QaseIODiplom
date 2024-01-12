package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

@Log4j2
public class StepField {

    private final String STEP_FIELDS = "//p[@data-placeholder='%s']";

    public void fillInStepField(String fieldName, String value) {
        log.info("Filling TC Step field '{}' with value '{}'", fieldName, value);
        actions().click($(By.xpath(String.format(STEP_FIELDS, fieldName)))).perform();
        $(By.xpath(String.format(STEP_FIELDS, fieldName))).sendKeys(value);
    }

}
