package ga.euroly.cv.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    private String aboutMe;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "person", orphanRemoval = true)
    private List<PersonContacts> contacts = new ArrayList<>();

    public void addContact(PersonContacts contacts) {
        this.contacts.add(contacts);
        contacts.setPerson(this);
    }

    public void removeContact(PersonContacts contacts) {
        contacts.setPerson(null);
        this.contacts.remove(contacts);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
