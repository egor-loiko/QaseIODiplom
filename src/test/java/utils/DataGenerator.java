package utils;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DataGenerator {

    private static Faker faker = new Faker();
    private static final int PROJECT_CODE_LIMIT = 10;

    public static String generateProjectName() {
        return faker.name().lastName();
    }

    public static String generateProjectCode() {
        String projectCode = faker.name().lastName().toUpperCase();
        return projectCode.length() > 10 ? projectCode.substring(0, PROJECT_CODE_LIMIT) : projectCode;
    }

    public static String generateProjectDescription() {
        return faker.animal().name() + " " + faker.name().firstName();
    }

    public static String generateSuiteName() {
        return faker.name().lastName();
    }

    public static String generateSuiteDescription() {
        return faker.animal().name() + " " + faker.name().firstName();
    }

    public static String generateSuitePreconditions() {
        return faker.animal().name() + " " + faker.name().firstName();
    }

    public static String generateCaseName() {
        return faker.name().lastName();
    }

    public static String generateCaseDescription() {
        return faker.animal().name() + " " + faker.name().firstName();
    }

    public static String generateCasePreconditions() {
        return faker.animal().name() + " " + faker.name().firstName();
    }

    public static String generateCasePostconditions() {
        return faker.animal().name() + " " + faker.name().firstName();
    }

    public static int generateRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String generateStepAction() {
        return faker.animal().name() + " " + faker.name().firstName();
    }

    public static String generateStepExpectedResult() {
        return faker.animal().name() + " " + faker.name().firstName();
    }

    public static String generateStepData() {
        return faker.animal().name() + " " + faker.name().firstName();
    }

}
