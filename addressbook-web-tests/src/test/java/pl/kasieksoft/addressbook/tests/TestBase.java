package pl.kasieksoft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pl.kasieksoft.addressbook.appmanager.ApplicationManager;
import pl.kasieksoft.addressbook.model.ContactDataBuilder;
import pl.kasieksoft.addressbook.model.Contacts;
import pl.kasieksoft.addressbook.model.GroupDataBuilder;
import pl.kasieksoft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite(alwaysRun = true)

    public void setUp() {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod(enabled = false)
    public void logStart(Method m, Object[] p) {
        logger.info("Start test: " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod(enabled = false)
    public void logStop(Method m, Object[] p) {
        logger.info("Finish test:" + m.getName() + " with parameters " + Arrays.asList(p));
    }

    void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> GroupDataBuilder.aGroupData()
                            .withId(g.getId())
                            .withName(g.getName())
                            .build())
                    .collect(Collectors.toSet())));
        }
    }

    void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((c) -> ContactDataBuilder.aContactData()
                            .withId(c.getId())
                            .withFirstname(c.getFirstname())
                            .withLastname(c.getLastname())
                            .build())
                    .collect(Collectors.toSet())));
        }
    }
}
