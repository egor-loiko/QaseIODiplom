package models.suite;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
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
    @SerializedName(value = "parent_id")
    @JsonProperty("parent_id")
    private int parentId;
    private int position;
    @SerializedName(value = "cases_count")
    @JsonProperty("cases_count")
    private int casesCount;
    private String created;
    private String updated;
    @SerializedName(value = "created_at")
    @JsonProperty("created_at")
    private String createdAt;
    @SerializedName(value = "updated_at")
    @JsonProperty("updated_at")
    private String updatedAt;
}