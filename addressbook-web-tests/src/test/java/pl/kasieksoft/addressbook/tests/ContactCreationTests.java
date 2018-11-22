package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initNewContact();
        app.getContactHelper().fillNewContactForm(new ContactData("Katarzyna", "Jarosz", "+48 792 899 848", "pytel.kat@gmail.com", "10", "March", "1992"));
        app.getContactHelper().submitNewContact();
    }
}
