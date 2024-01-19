package models.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Case {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;
    private String description;
    private String preconditions;
    private String postconditions;
    private String title;
    private int severity;
    private int priority;
    private int behavior;
    private int type;
    private int layer;
    @JsonProperty("is_flaky")
    private int isFlaky;
    @JsonProperty("suite_id")
    private int suiteId;
    @JsonProperty("milestone_id")
    private Integer milestoneId;
    private int automation;
    private int status;
    private List<String> attachments;
    private List<Steps> steps;
    private List<String> tags;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> links;
    private List<Params> params;
    @JsonProperty(value = "custom_fields", access = JsonProperty.Access.WRITE_ONLY)
    private List<CustomFields> customFields;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String created;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String updated;
    @JsonProperty(value = "created_at", access = JsonProperty.Access.WRITE_ONLY)
    private String createdAt;
    @JsonProperty(value = "updated_at", access = JsonProperty.Access.WRITE_ONLY)
    private String updatedAt;
    private int position;
    @JsonProperty(value = "steps_type", access = JsonProperty.Access.WRITE_ONLY)
    public String stepsType;
    @JsonProperty(value = "member_id", access = JsonProperty.Access.WRITE_ONLY)
    public String memberId;
    @JsonProperty(value = "author_id", access = JsonProperty.Access.WRITE_ONLY)
    public String authorId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String deleted;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public int isManual;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isToBeAutomated;


}
