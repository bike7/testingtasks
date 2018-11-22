package pl.kasieksoft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        initNewContact();
        fillNewContactForm(new ContactData("Katarzyna", "Jarosz", "+48 792 899 848", "pytel.kat@gmail.com", "10", "March", "1992"));
        submitNewContact();
    }
}
