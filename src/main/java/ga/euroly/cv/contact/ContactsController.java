package ga.euroly.cv.contact;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/contacts")
public class ContactsController {

    private final ContactService contactService;

    @Autowired
    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }
    @PostMapping("add")
    public Contacts createInfo(@RequestBody ContactsView contactsView){
        return contactService.save(contactsView);
    }

    @GetMapping("show")
    public List<ContactsView> showContacts(){
        return contactService.getContactsView();
    }

    @ExceptionHandler({EmptyResultDataAccessException.class, NotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void handleNotFoundException(){}
}
