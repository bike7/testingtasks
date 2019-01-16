package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.*;

import java.util.HashMap;

import static org.testng.Assert.assertFalse;

public class RemoveContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(GroupDataBuilder.aGroupData().withName("test").build());
        }
        if (app.db().contacts().size() == 0) {
            app.contact().create(ContactDataBuilder.aContactData().withFirstname("test_name").withLastname("test_surname").build());
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        ContactData selectedContact = app.db().contacts().iterator().next();
        GroupData selectedGroup = app.db().groups().iterator().next();
        app.goTo().homePage();
        app.contact().addToGroup(selectedContact, selectedGroup);
        app.goTo().groupPage(selectedGroup.getId());
        app.contact().removeFromGroup(selectedContact);
        Contacts after = app.db().contacts();
        HashMap<ContactData, Groups> map = new HashMap<>();
        for (ContactData contact : after) {
            map.put(contact, contact.getGroups());
        }
        assertFalse(map.get(selectedContact).contains(selectedGroup));
    }

}