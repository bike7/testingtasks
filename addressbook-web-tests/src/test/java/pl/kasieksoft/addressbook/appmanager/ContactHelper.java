package pl.kasieksoft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.kasieksoft.addressbook.model.ContactData;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void simpleFillNewContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        click(By.xpath("//div[@id='content']/form/label[3]"));
        type(By.name("lastname"), contactData.getLastname());
    }

    public void fillNewContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        click(By.xpath("//div[@id='content']/form/label[3]"));
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getPhoneHome());
        type(By.name("email"), contactData.getEmail());
        click(By.name("bday"));
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
        click(By.name("bday"));
        click(By.name("bmonth"));
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
        click(By.name("bmonth"));
        type(By.name("byear"), contactData.getByear());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification(int index) {
        click(By.xpath("//tr[" + (index + 2) + "]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        initNewContact();
        simpleFillNewContactForm(contact);
        submitNewContact();
    }

    public List<ContactData> getContactList() {
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

}
