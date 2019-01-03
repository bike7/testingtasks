package pl.kasieksoft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pl.kasieksoft.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
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
}
