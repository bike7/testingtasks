package pl.kasieksoft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;
import pl.kasieksoft.addressbook.model.GroupDataBuilder;

import java.util.Set;

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
        Set<ContactData> before = app.contact().all();
        ContactData newContact = ContactDataBuilder.aContactData()
                .withFirstname("Mikołaj")
                .withLastname("Kopernik")
                .withBday("1")
                .withBmonth("April")
                .withEmail("1999")
                .withGroup(TEST_GROUP_NAME)
                .build();
        app.contact().create(newContact, false);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        
        newContact.setId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(newContact);
        Assert.assertEquals(before, after);
    }
}
