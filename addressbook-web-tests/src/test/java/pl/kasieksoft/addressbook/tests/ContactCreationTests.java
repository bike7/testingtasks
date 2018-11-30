package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        String groupName = "test1";

        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup(groupName)) {
            app.getGroupHelper().createGroup(new GroupData(groupName, null, null));
        }
        app.getContactHelper().initNewContact();
        app.getContactHelper().fillNewContactForm(new ContactData("Katarzyna", "Jarosz", null, null, "10", "March", "1992", groupName), true);
        app.getContactHelper().submitNewContact();
    }
}
