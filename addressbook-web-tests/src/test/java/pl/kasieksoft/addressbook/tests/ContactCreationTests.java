package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;
import pl.kasieksoft.addressbook.model.Contacts;
import pl.kasieksoft.addressbook.model.GroupDataBuilder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    private static final String TEST_GROUP_NAME = "test1";

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (!app.group().isThereAGroup(TEST_GROUP_NAME)) {
            app.group().create(GroupDataBuilder.aGroupData().withName(TEST_GROUP_NAME).build());
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        ContactData newContact = ContactDataBuilder.aContactData()
                .withFirstname("Mikołaj")
                .withLastname("Kopernik")
                .withBday("1")
                .withBmonth("April")
                .withEmail("1999")
                .withGroup(TEST_GROUP_NAME)
                .build();
        app.contact().create(newContact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);

        newContact.setId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        assertThat(after, equalTo(before.withAdded(newContact)));
    }
}
