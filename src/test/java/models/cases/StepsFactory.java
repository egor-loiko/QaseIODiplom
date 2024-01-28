package models.cases;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

import static utils.DataGenerator.*;

@Log4j2
public class StepsFactory {

    @Step("Generate set of Test Case steps ( {stepsQuantity} steps in set)")
    public static ArrayList<Steps> getSetOfRandomSteps(int stepsQuantity) {
        log.info("Generating set of Test Case steps ('{}' steps in set)", stepsQuantity);
        ArrayList<Steps> stepsList = new ArrayList<>();

        for (int i = 0; i < stepsQuantity; i++) {
            stepsList.add(Steps.builder()
                    .action(generateStepAction())
                    .expected_result(generateStepExpectedResult())
                    .data(generateStepData())
                    .build());
        }

        return stepsList;
    }

}
