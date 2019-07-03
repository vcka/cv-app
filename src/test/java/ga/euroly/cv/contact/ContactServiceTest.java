package ga.euroly.cv.contact;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ContactService.class)
@DataJpaTest
@EnableAutoConfiguration
public class ContactServiceTest {

    @Autowired
    private ContactsRepository repository;

    @Autowired
    ContactService service;

    @Before
    public void beforeEach() {
        repository.deleteAll();
    }

    @Test
    public void testContactService_contact_updateExistingContact() {
        Contacts savedContact = repository.save(new Contacts("test", ContactsType.EMAIL, "http://"));
        ContactsView updatedContact = new ContactsView(savedContact.getId(),"updated-value", null, null);
        savedContact.setValue("updated-value");
        service.save(updatedContact);
//        service.update(updatedContact);

        Contacts actualResult = repository.getOne(updatedContact.getId());
        assertEquals("updated-value",actualResult.getValue());
        assertEquals(ContactsType.EMAIL,actualResult.getContactsType());
        assertEquals("http://",actualResult.getLink());
    }
}