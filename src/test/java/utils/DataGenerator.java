package utils;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DataGenerator {

    private static Faker faker = new Faker();
    private static int projectCodeLimit = 10;

    public static String generateProjectName() {
        return faker.name().lastName();
    }

    public static String generateProjectCode() {
        String projectCode = faker.name().lastName().toUpperCase();
        return projectCode.length() > 10 ? projectCode.substring(0, projectCodeLimit) : projectCode;
    }

    public static String generateProjectDescription() {
        return faker.animal().name() + " " + faker.name().firstName();
    }

    public static String generateSuiteName() {
        return faker.name().lastName();
    }
}
