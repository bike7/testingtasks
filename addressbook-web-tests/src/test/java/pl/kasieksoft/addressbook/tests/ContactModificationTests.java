package pl.kasieksoft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(ContactDataBuilder.aContactData().withFirstname("Martin").withLastname("Ann").build(), true);
        }
        app.getNavigationHelper().goToHomePage();
    }


    @Test
    public void testContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = 0;
        ContactData newContact = ContactDataBuilder.aContactData()
                .withId(before.get(index).getId())
                .withFirstname("Adam")
                .withLastname("Mickiewicz")
                .withPhoneHome("+10 123 45 67")
                .withEmail("N/A")
                .withBday("7")
                .withBmonth("July")
                .withByear("2000")
                .build();
        app.getContactHelper().modifyContact(index, newContact);
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(newContact);
        before.sort(Comparator.comparingInt(ContactData::getId));
        after.sort(Comparator.comparingInt(ContactData::getId));
        Assert.assertEquals(before, after);
    }
}
