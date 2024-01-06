package utils;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DataGenerator {

    private static Faker faker = new Faker();
    private static int projectNameLimit = 10;

    public static String generateProjectName() {
        String projectName = faker.name().lastName();
        return projectName.length() > 10 ? projectName.substring(0, projectNameLimit) : projectName;
    }

    public static String generateSuiteName() {
        return faker.name().lastName();
    }
}
