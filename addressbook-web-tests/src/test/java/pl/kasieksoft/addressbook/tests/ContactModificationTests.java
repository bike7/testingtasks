package pl.kasieksoft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(ContactDataBuilder.aContactData().withFirstname("Martin").withLastname("Ann").build(), true);
        }
        app.goTo().homePage();
    }


    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData newContact = ContactDataBuilder.aContactData()
                .withId(modifiedContact.getId())
                .withFirstname("Adam")
                .withLastname("Mickiewicz")
                .withPhoneHome("+10 123 45 67")
                .withEmail("N/A")
                .withBday("7")
                .withBmonth("July")
                .withByear("2000")
                .build();
        app.contact().modify(newContact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();

        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(newContact);
        Assert.assertEquals(before, after);
    }
}
