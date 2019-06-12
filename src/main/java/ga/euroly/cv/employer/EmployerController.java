package ga.euroly.cv.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/employer")
public class EmployerController {

    private EmployerRepository repository;

    @Autowired
    public EmployerController(EmployerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<EmployerView> getAllEmployers(){
        return repository.findAll().stream()
                .map(this::mapFromEmployer)
                .collect(Collectors.toList());
    }

    @PostMapping("new-employer")
    public void addEmployer(@RequestBody EmployerView employerView){
        repository.save(mapToEmployer(employerView));
    }

    @PostMapping("new-activity/{id}")
    public void addActivity(@PathVariable Long id, @RequestBody ActivityView activityView){
        Activity activity = mapToActivity(activityView);
        Employer employer = repository.findById(id)
                .orElseThrow((() -> new IllegalArgumentException("Wrong employer ID.")));
        employer.addActivity(activity);
        repository.save(employer);
    }

    private Activity mapToActivity(ActivityView activityView){
        return new Activity(activityView.getId(),
                activityView.getDescription(),
                activityView.getEndDate(),
                activityView.getStartDate(),
                activityView.getPosition());
    }

    private Employer mapToEmployer(EmployerView employerView){
        return new Employer(employerView.getId(),
                employerView.getName(),
                employerView.getStartDate(),
                employerView.getEndDate(),
                Collections.emptyList());
    }

    private EmployerView mapFromEmployer(Employer employer){
        List<ActivityView> activityViews = employer.getActivityList().stream()
                .map(this::mapFromActivity)
                .collect(Collectors.toList());
        return new EmployerView(employer.getId(), employer.getName(), employer.getStartDate(), employer.getEndDate(),
                activityViews);
    }

    private ActivityView mapFromActivity(Activity activity){
        return new ActivityView(activity.getId(),
                activity.getDescription(),
                activity.getStartDate(),
                activity.getEndDate(),
                activity.getPosition());
    }
}
