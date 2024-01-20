package models.project;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static utils.DataGenerator.*;

@Log4j2
public class ProjectFactory {

    @Step("Generate Project test data")
    public static Project getRandomProject() {
        log.info("Generate Project test data");
        return Project.builder()
                .title(generateProjectName())
                .code(generateProjectCode())
                .description(generateProjectDescription())
                .access("all")
                .group("test")
                .build();
    }

    @Step("Generate Project with empty title test data")
    public static Project getProjectWithEmptyTitle() {
        log.info("Generating Project with empty title test data");
        return Project.builder()
                .title("")
                .code(generateProjectCode())
                .description(generateProjectDescription())
                .access("all")
                .group("test")
                .build();
    }

    @Step("Generate Project with empty project code test data")
    public static Project getProjectWithEmptyCode() {
        log.info("Generating Project with empty project code test data");
        return Project.builder()
                .title(generateProjectName())
                .code("")
                .description(generateProjectDescription())
                .access("all")
                .group("test")
                .build();
    }

}
