package models.cases;

import java.util.ArrayList;

import static models.cases.StepsFactory.getSetOfRandomSteps;
import static utils.DataGenerator.*;

public class CaseFactory {

    public static Case getRandomCase() {

        return Case.builder()
                .title(generateCaseName())
                .description(generateCaseDescription())
                .preconditions(generateCasePreconditions())
                .postconditions(generateCasePostconditions())
                .severity(generateRandomNumber(0, 6))
                .priority(generateRandomNumber(0, 3))
                .behavior(generateRandomNumber(1, 3))
                .type(generateRandomNumber(1, 10))
                .layer(generateRandomNumber(0, 3))
                .isFlaky(generateRandomNumber(0, 1))
                .milestoneId(null)
                .automation(generateRandomNumber(0, 2))
                .status(generateRandomNumber(0, 2))
                .attachments(new ArrayList<>())
                .steps(getSetOfRandomSteps(5))
                .params(new ArrayList<>())
                .build();
    }
}
