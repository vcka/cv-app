package ga.euroly.cv.contact;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return new ContactsView(contacts.getId(), contacts.getValue(),contacts.getContactsType(),contacts.getLink());
    }

    private Contacts mapFromView(ContactsView contactsView){
        return new Contacts(contactsView.getValue(), contactsView.getContactsType(), contactsView.getLink());
    }

    public ContactsView findByID(Long id){
        return mapToView(repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Not found.")));
    }

    public List<ContactsView> getContactsView(){
        return repository.findAll()
                .stream()
                .map(this::mapToView)
                .collect(Collectors.toList());
    }

    public ContactsView save(ContactsView contactsView) {
        return mapToView(repository.save(mapFromView(contactsView)));
    }

    public HttpStatus delete(Long id){
        repository.deleteById(id);
        return HttpStatus.OK;
    }


}
