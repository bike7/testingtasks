package pl.kasieksoft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class NavigationHelper extends HelperBase {

    private final Properties properties;

    public NavigationHelper(WebDriver wd) {
        super(wd);
        properties = new Properties();
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void groupPage(int id) {
        String currentUrl = wd.getCurrentUrl();
        wd.get(currentUrl.substring(0, currentUrl.lastIndexOf('/') + 1) + "?group=" + id);
    }

    public void homePage(boolean forceRefresh) {
        if (!forceRefresh && isElementPresent(By.id("maintable"))) {
            return;
        }
        String target = System.getProperty("target", "local");
        try {
            properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        } catch (IOException ioe) {
            System.out.println("File with system properties cannot be reached" + ioe);
        }
        wd.get(properties.getProperty("web.baseUrl"));
    }

    public void homePage() {
        homePage(false);
    }
}
