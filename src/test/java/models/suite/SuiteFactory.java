package models.suite;

import static utils.DataGenerator.*;

public class SuiteFactory {

    public static Suite getRandomSuite() {
        return Suite.builder()
                .id(1)
                .title(generateSuiteName())
                .description(generateSuiteDescription())
                .preconditions(generateSuitePreconditions())
                .build();
    }
}
