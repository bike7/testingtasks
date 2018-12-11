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
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup(TEST_GROUP_NAME)) {
            app.getGroupHelper().createGroup(new GroupData(TEST_GROUP_NAME, null, null));
        }
        app.getNavigationHelper().goToHomePage();
    }

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData newContact = ContactDataBuilder.aContactData()
                .withFirstname("Mikołaj")
                .withLastname("Kopernik")
                .withBday("1")
                .withBmonth("April")
                .withEmail("1999")
                .withGroup(TEST_GROUP_NAME)
                .build();
        app.getContactHelper().createContact(newContact, false);
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        newContact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
        before.add(newContact);
        before.sort(Comparator.comparingInt(ContactData::getId));
        after.sort(Comparator.comparingInt(ContactData::getId));
        Assert.assertEquals(before, after);
    }
}
