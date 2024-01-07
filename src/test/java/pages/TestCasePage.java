package pages;

import com.sun.source.tree.TryTree;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.security.Key;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCasePage {

    private final String TC_TITLE_CSS = "#title";
    private final String TC_DROPDOWN_CSS = "//label[text()='%s']//following::div";

    @Step
    public void setCaseTitle(String tcTitle){
        $(TC_TITLE_CSS).sendKeys(tcTitle);
    }

    @Step
    public void setDropdownValue(String label){
        $(By.xpath(String.format(TC_DROPDOWN_CSS,label))).click();
        log.info($(By.xpath("//div[@class='pG08Lh'][2]")).getText());
        //$(By.xpath("//div[@class='pG08Lh'][2]")).click();
        $(By.xpath("//div[text()='Draft']")).click();
    }

}
