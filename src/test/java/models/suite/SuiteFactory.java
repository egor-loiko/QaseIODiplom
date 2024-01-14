package models.suite;

import static utils.DataGenerator.*;

public class SuiteFactory {

    public static Suite getRandomSuite() {
        return Suite.builder()
                .title(generateSuiteName())
                .description(generateSuiteDescritpion())
                .preconditions(generateSuitePreconditions())
                .build();
    }
}
