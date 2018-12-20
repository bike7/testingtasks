package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.ContactData;

import java.util.ArrayList;
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

        assertThat(contactInfoFromDetailsPage, equalTo(asListInContactDetailsPageFormat(contactInfoFromEditForm)));
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

    private static List<String> asListInContactDetailsPageFormat(ContactData contact) {
        List<String> result = new ArrayList<>();
        result.add(contact.getFirstname() + " " + contact.getLastname());
        result.add(contact.getAddress());
        if (contact.getHomePhone() != null) {
            result.add("H: " + contact.getHomePhone());
        }
        if (contact.getMobilePhone() != null) {
            result.add("M: " + contact.getMobilePhone());
        }
        if (contact.getWorkPhone() != null) {
            result.add("W: " + contact.getWorkPhone());
        }
        result.add(contact.getEmail());
        result.add(contact.getEmail2());
        result.add(contact.getEmail3());
        result.removeIf(String::isEmpty);
        return result;
    }
}
