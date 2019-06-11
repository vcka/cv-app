package ga.euroly.cv.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactsView {

    private Long id;

    private String value;

    private ContactsType contactsType;
}
