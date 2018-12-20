package pl.kasieksoft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;
import pl.kasieksoft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ContactHelper extends HelperBase {

    private Contacts contactCache = null;

    ContactHelper(WebDriver wd) {
        super(wd);
    }

    private void fillNewContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
        selectFromDropdown("bday", contactData.getBday());
        selectFromDropdown("bmonth", contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());
        if (creation) {
            if (contactData.getGroup() != null) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    private void submitNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    private void initNewContact() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        contactCache = null;
    }

    public void selectContact(int id) {
        click(By.cssSelector("input[value='" + id + "']"));
    }

    private void initContactModification(int id) {
        String currentUrl = wd.getCurrentUrl();
        wd.get(currentUrl.substring(0, currentUrl.lastIndexOf('/') + 1) + "edit.php?id=" + id);
    }

    private void enterContactDetailsPage(int id) {
        String currentUrl = wd.getCurrentUrl();
        wd.get(currentUrl.substring(0, currentUrl.lastIndexOf('/') + 1) + "view.php?id=" + id);

    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void create(ContactData contact) {
        initNewContact();
        fillNewContactForm(contact, true);
        contactCache = null;
        submitNewContact();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillNewContactForm(contact, false);
        contactCache = null;
        submitContactModification();
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement row : rows) {
            contactCache.add(ContactDataBuilder.aContactData()
                    .withId(Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value")))
                    .withFirstname(row.findElement(By.xpath("td[3]")).getText())
                    .withLastname(row.findElement(By.xpath("td[2]")).getText())
                    .withAllPhones(row.findElement(By.xpath("td[6]")).getText())
                    .withAllEmails(row.findElement(By.xpath("td[5]")).getText())
                    .withAddress(row.findElement(By.xpath("td[4]")).getText())
                    .build());
        }
        return new Contacts(contactCache);
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            contacts.add(ContactDataBuilder.aContactData()
                    .withId(Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")))
                    .withFirstname(element.findElement(By.xpath("td[3]")).getText())
                    .withLastname(element.findElement(By.xpath("td[2]")).getText())
                    .build());
        }
        return contacts;
    }

    private void selectFromDropdown(String name, String text) {
        if (text != null) {
            click(By.name(name));
            new Select(wd.findElement(By.name(name))).selectByVisibleText(text);
            click(By.name(name));
        }
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        return ContactDataBuilder.aContactData()
                .withFirstname(wd.findElement(By.name("firstname")).getAttribute("value"))
                .withLastname(wd.findElement(By.name("lastname")).getAttribute("value"))
                .withHomePhone(wd.findElement(By.name("home")).getAttribute("value"))
                .withMobilePhone(wd.findElement(By.name("mobile")).getAttribute("value"))
                .withWorkPhone(wd.findElement(By.name("work")).getAttribute("value"))
                .withEmail(wd.findElement(By.name("email")).getAttribute("value"))
                .withEmail2(wd.findElement(By.name("email2")).getAttribute("value"))
                .withEmail3(wd.findElement(By.name("email3")).getAttribute("value"))
                .withAddress(wd.findElement(By.name("address")).getText())
                .build();

    }

    public List<String> infoFromDetailsPage(ContactData contact) {
        enterContactDetailsPage(contact.getId());
        return Arrays.stream(wd.findElement(By.xpath("//div[@id='content']")).getText().split("\n"))
                .filter((s) -> !s.equals("")).collect(Collectors.toList());
    }

    public List<String> modelDetailsPage(ContactData contact) {
        List<String> model = new ArrayList();
        model.add(contact.getFirstname() + " " + contact.getLastname());
        model.add(contact.getAddress());
        if (contact.getHomePhone() != null) {
            model.add("H: " + contact.getHomePhone());
        }
        if (contact.getMobilePhone() != null) {
            model.add("M: " + contact.getMobilePhone());
        }
        if (contact.getWorkPhone() != null) {
            model.add("W: " + contact.getWorkPhone());
        }
        model.add(contact.getEmail());
        model.add(contact.getEmail2());
        model.add(contact.getEmail3());
        model.removeIf(p -> p.isEmpty());
        return model;
    }
}
