package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;
import pl.kasieksoft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(ContactDataBuilder.aContactData().withFirstname("Martin").withLastname("Ann").build());
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData newContact = ContactDataBuilder.aContactData()
                .withId(modifiedContact.getId())
                .withFirstname("Adam")
                .withLastname("Mickiewicz")
                .withHomePhone("+10 123 45 67")
                .withEmail("N/A")
                .withBday("7")
                .withBmonth("July")
                .withByear("2000")
                .build();
        app.contact().modify(newContact);
        app.goTo().homePage();
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));
    }
}
