package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHome(), equalTo(contactInfoFromEditForm.getHome()));
        assertThat(contact.getMobile(), equalTo(contactInfoFromEditForm.getMobile()));
        assertThat(contact.getWork(), equalTo(contactInfoFromEditForm.getWork()));
    }

}
