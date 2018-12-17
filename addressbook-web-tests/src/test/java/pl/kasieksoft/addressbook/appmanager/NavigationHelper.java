package pl.kasieksoft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));

    }

    public void homePage(boolean forceRefresh) {
        if (!forceRefresh && isElementPresent(By.id("maintable"))) {
            return;
        }
        wd.get(ApplicationManager.APPLICATION_URL);
    }

    public void homePage() {
        homePage(false);
    }
}
