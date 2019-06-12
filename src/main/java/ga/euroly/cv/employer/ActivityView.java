package ga.euroly.cv.employer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ActivityView {

    private Long id;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String position;
}
