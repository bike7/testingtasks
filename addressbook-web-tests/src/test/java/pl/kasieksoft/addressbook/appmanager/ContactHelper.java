package pl.kasieksoft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillNewContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getPhoneHome());
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

    public void submitNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void initNewContact() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void selectContact(int id) {
        click(By.cssSelector("input[value='" + id + "']"));
    }

    public void initContactModification(int id) {
        String currentUrl = wd.getCurrentUrl();
        wd.get(currentUrl.substring(0, currentUrl.lastIndexOf('/') + 1) + "edit.php?id=" + id);
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact, boolean simple) {
        initNewContact();
        fillNewContactForm(contact, true);
        submitNewContact();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillNewContactForm(contact, false);
        submitContactModification();
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
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
}
