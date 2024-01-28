package models.suite;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static utils.DataGenerator.*;

@Log4j2
public class SuiteFactory {

    @Step("Generate Suite test data")
    public static Suite getRandomSuite() {
        log.info("Generating Suite test data");
        return Suite.builder()
                .id(1)
                .title(generateSuiteName())
                .description(generateSuiteDescription())
                .preconditions(generateSuitePreconditions())
                .build();
    }

}
