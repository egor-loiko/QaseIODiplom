package models.cases;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

import static models.cases.StepsFactory.getSetOfRandomSteps;
import static utils.DataGenerator.*;

@Log4j2
public class CaseFactory {

    @Step("Generate Test Case test data")
    public static Case getRandomCase() {
        log.info("Generating Test Case test data");
        return Case.builder()
                .id(1)
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
