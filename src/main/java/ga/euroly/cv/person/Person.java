package ga.euroly.cv.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="seq", initialValue=1, allocationSize=2)
public class Person {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
    private Long id = 1L;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    private String aboutMe;
}
