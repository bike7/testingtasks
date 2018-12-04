package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Martin", "Ann", null, null, null, null, null, null));
        }
        app.getContactHelper().selectContact(0);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillNewContactForm(new ContactData("Adam", "Mickiewicz", "+10 123 456 789", "N/A", "1", "January", "2000", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }

}
