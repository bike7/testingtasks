package pl.kasieksoft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;
import pl.kasieksoft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        String groupName = "test1";

        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup(groupName)) {
            app.getGroupHelper().createGroup(new GroupData(groupName, null, null));
        }
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initNewContact();
        ContactData newContact = ContactDataBuilder.aContactData()
                .withFirstname("Mikołaj")
                .withLastname("Kopernik")
                .withBday("1")
                .withBmonth("April")
                .withEmail("1999")
                .withGroup(groupName)
                .build();

        app.getContactHelper().fillNewContactForm(newContact, true);
        app.getContactHelper().submitNewContact();
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
