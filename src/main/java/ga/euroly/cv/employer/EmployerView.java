package ga.euroly.cv.employer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class EmployerView {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<ActivityView> activities;
}
