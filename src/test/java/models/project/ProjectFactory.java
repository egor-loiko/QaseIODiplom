package models.project;

import static utils.DataGenerator.*;

public class ProjectFactory {
    public static Project getRandomProject() {
        return Project.builder()
                .title(generateProjectName())
                .code(generateProjectCode())
                .description(generateProjectDescription())
                .access("all")
                .group("test")
                .build();
    }

    public static Project getProjectWithEmptyTitle() {
        return Project.builder()
                .title("")
                .code(generateProjectCode())
                .description(generateProjectDescription())
                .access("all")
                .group("test")
                .build();
    }

    public static Project getProjectWithEmptyCode() {
        return Project.builder()
                .title(generateProjectName())
                .code("")
                .description(generateProjectDescription())
                .access("all")
                .group("test")
                .build();
    }
}
