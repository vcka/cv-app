package ga.euroly.cv.contact;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class ContactService {
    private final ContactsRepository repository;

    @Autowired
    public ContactService(ContactsRepository repository) {
        this.repository = repository;
    }

    private ContactsView mapToView(Contacts contacts){
        return new ContactsView(contacts.getId(), contacts.getValue(),contacts.getContactsType());
    }

    private Contacts mapFromView(ContactsView contactsView){
        return new Contacts(contactsView.getValue(), contactsView.getContactsType());
    }

    public List<ContactsView> getContactsView(){
        return repository.findAll()
                .stream()
                .map(this::mapToView)
                .collect(Collectors.toList());
    }

    public Contacts save(ContactsView contactsView) {
        return repository.save(mapFromView(contactsView));
    }


}
