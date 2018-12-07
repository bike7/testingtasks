package pl.kasieksoft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(ContactDataBuilder.aContactData().withFirstname("Martin").withLastname("Ann").build());
        }
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillNewContactForm(ContactDataBuilder.aContactData()
                        .withFirstname("Adam")
                        .withLastname("Mickiewicz")
                        .withPhoneHome("+10 123 45 67")
                        .withEmail("N/A")
                        .withBday("7")
                        .withBmonth("July")
                        .withByear("2000")
                        .build(),
                false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());
    }

}
