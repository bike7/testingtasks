package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

    @Test
    public void testContactDetails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        List<String> contactInfoFromDetailsPage = app.contact().infoFromDetailsPage(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

        assertThat(contactInfoFromDetailsPage, equalTo(app.contact().modelDetailsPage(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .filter((s) -> !s.equals(""))
                .map(ContactDetailsTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
