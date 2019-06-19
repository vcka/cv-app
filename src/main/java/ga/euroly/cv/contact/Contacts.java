package ga.euroly.cv.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contacts {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String value;

    @NotEmpty
    private String link;

    private ContactsType contactsType;

    public Contacts(String value, ContactsType contactsType) {
        this.value = value;
        this.contactsType = contactsType;
    }

    //    @ManyToOne
//    private Person person;
}
