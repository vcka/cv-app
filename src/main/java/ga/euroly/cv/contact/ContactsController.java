package ga.euroly.cv.contact;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/contacts")
@CrossOrigin("*")
public class ContactsController {

    private final ContactService contactService;

    @Autowired
    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PutMapping("update/{id}")
    public ContactsView updateContact (@PathVariable Long id, @RequestBody ContactsView contactsView) {
        ContactsView contactsToUpdate = contactService.findByID(id);
        return contactService.save(contactsToUpdate);
    }

    @PostMapping("add")
    public ContactsView createInfo(@RequestBody ContactsView contactsView){
        return contactService.save(contactsView);
    }

    @GetMapping("show")
    public List<ContactsView> showContacts(){
        return contactService.getContactsView();
    }

    @GetMapping("type")
    public List<ContactsType> getContactTypes(){
        return Arrays.asList(ContactsType.values());
    }

    @ExceptionHandler({EmptyResultDataAccessException.class, NotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void handleNotFoundException(){}
}
