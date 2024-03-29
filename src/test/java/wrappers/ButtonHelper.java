package wrappers;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ButtonHelper {

    private final String BUTTON_NAME = "//*[normalize-space(text())='%s']";

    public void click(String buttonName) {
        log.info("Click on button '{}'", buttonName);
        $(By.xpath(String.format(BUTTON_NAME, buttonName))).shouldBe(Condition.visible);
        $(By.xpath(String.format(BUTTON_NAME, buttonName))).click();
    }

    public void wait(String buttonName) {
        log.info("Waiting for button '{}' appears", buttonName);
        $(By.xpath(String.format(BUTTON_NAME, buttonName))).shouldBe(Condition.visible);
    }

}
