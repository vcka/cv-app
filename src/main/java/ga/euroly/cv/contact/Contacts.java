package ga.euroly.cv.contact;

import ga.euroly.cv.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contacts {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    private ContactsType contactsType;

    public Contacts(String value, ContactsType contactsType) {
        this.value = value;
        this.contactsType = contactsType;
    }

    //    @ManyToOne
//    private Person person;
}
