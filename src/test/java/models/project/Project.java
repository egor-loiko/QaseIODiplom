package models.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    private String title;
    private String code;
    private String description;
    private String access;
    private String group;
    private ProjectCounts counts;
    private ProjectRuns runs;
    private ProjectDefects defects;

}
