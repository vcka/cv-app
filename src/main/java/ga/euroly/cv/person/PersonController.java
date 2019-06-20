package ga.euroly.cv.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/person")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @CrossOrigin("*")
    @GetMapping("info")
    public Person getPerson() {
        return personRepository.findById(1L).get();
    }

    @CrossOrigin("*")
    @PostMapping("info")
    public Person createInfo(@RequestBody Person person){
        return personRepository.save(person);
    }

    @CrossOrigin("*")
    @PutMapping("info/{id}")
    public ResponseEntity<Person> updatePersonByID(@PathVariable Long id, @RequestBody Person person){
        Optional<Person> personUpdated = personRepository.findById(id);
        if (personUpdated.isPresent()){
            person.setId(id);
            if (person.getName()==null){
                person.setName(personUpdated.get().getName());
            }
            if (person.getSurname()==null){
                person.setSurname(personUpdated.get().getSurname());
            }
            if (person.getAboutMe()==null){
                person.setAboutMe(personUpdated.get().getAboutMe());
            }

            personRepository.save(person);
            return new ResponseEntity<Person>(person, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("info/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable Long id){
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()){
            personRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
