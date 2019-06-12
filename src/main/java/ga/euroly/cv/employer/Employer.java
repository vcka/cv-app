package ga.euroly.cv.employer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @OneToMany(mappedBy = "employer",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Activity> activityList;

    public void addActivity(Activity activity) {
        if (activity.getStartDate().isBefore(this.startDate)||(this.endDate != null&& activity.getEndDate().isAfter(this.endDate))){
            throw new IllegalArgumentException("Wrong date.");
        }
        activityList.add(activity);
        activity.setEmployer(this);
    }

    public void removeActivity(Activity acitvity) {
        acitvity.setEmployer(null);
        this.activityList.remove(acitvity);
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
