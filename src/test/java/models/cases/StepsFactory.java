package models.cases;

import java.util.ArrayList;

import static utils.DataGenerator.*;

public class StepsFactory {

    public static ArrayList<Steps> getSetOfRandomSteps(int stepsQuantity) {
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
