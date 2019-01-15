package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(GroupDataBuilder.aGroupData().withName("test").build());
        }
    }

    @Test
    public void testContactCreation() {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData contact = ContactDataBuilder.aContactData()
                .withFirstname("test_name")
                .withLastname("test_surname")
                .inGroup(groups.iterator().next())
                .build();
        app.contact().create(contact);
        app.goTo().homePage();

        assertEquals(app.contact().count(), before.size() + 1);
        Contacts after = app.db().contacts();
        contact.setId(after.stream().mapToInt(ContactData::getId).max().getAsInt());
        assertThat(after, equalTo(before.withAdded(contact)));
        app.goTo().homePage();
        verifyContactListInUI();
    }
}
