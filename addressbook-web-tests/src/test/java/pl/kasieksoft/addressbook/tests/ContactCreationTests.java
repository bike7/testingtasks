package pl.kasieksoft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;
import pl.kasieksoft.addressbook.model.GroupData;

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
        app.getContactHelper().fillNewContactForm(ContactDataBuilder.aContactData().withFirstname("Mikołaj").withLastname("Kopernik").withBday("1").withBmonth("April").withEmail("1999").build(), true);
        app.getContactHelper().submitNewContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}
