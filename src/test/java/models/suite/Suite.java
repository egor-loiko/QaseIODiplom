package models.suite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suite {

    private int id;
    private String title;
    private String description;
    private String preconditions;
    @JsonProperty("parent_id")
    private int parentId;
    private int position;
    @JsonProperty("cases_count")
    private int casesCount;
    private String created;
    private String updated;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

}