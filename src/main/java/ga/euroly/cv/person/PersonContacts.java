package ga.euroly.cv.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PersonContacts {

    enum ContactType {
        PHONE,
        EMAIL,
        LINKEDIN
    }

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne
    private Person person;
}
