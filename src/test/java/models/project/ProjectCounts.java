package models.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCounts {

    private int cases;
    private int suites;
    private int milestones;
    private ProjectRuns runs;
    private ProjectDefects defects;

}
