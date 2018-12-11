package pl.kasieksoft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;
import pl.kasieksoft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    private static final String TEST_GROUP_NAME = "test1";

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (!app.group().isThereAGroup(TEST_GROUP_NAME)) {
            app.group().create(new GroupData(TEST_GROUP_NAME, null, null));
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.contact().list();
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
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        newContact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
        before.add(newContact);
        before.sort(Comparator.comparingInt(ContactData::getId));
        after.sort(Comparator.comparingInt(ContactData::getId));
        Assert.assertEquals(before, after);
    }
}
