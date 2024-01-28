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
public class Steps {

    private String action;
    @JsonProperty("expected_result")
    private String expected_result;
    private String data;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String hash;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int position;
    @JsonProperty(value = "shared_step_hash", access = JsonProperty.Access.WRITE_ONLY)
    private String shared_step_hash;
    @JsonProperty(value = "shared_step_nested_hash", access = JsonProperty.Access.WRITE_ONLY)
    private String shared_step_nested_hash;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> attachments;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Steps> steps;

}
