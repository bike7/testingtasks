package pl.kasieksoft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistartionHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistartionHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    }
}
