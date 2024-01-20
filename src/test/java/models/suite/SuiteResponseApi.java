package models.suite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuiteResponseApi {

    private boolean status;
    private Suite result;

}
