package ga.euroly.cv.employer;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ActivityView {

    private Long id;

    @NotEmpty
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    @NotEmpty
    private String position;
}
