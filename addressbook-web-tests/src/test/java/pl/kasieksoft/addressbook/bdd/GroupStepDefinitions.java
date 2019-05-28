package pl.kasieksoft.addressbook.bdd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import pl.kasieksoft.addressbook.appmanager.ApplicationManager;
import pl.kasieksoft.addressbook.model.GroupData;
import pl.kasieksoft.addressbook.model.GroupDataBuilder;
import pl.kasieksoft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;

public class GroupStepDefinitions {

    private ApplicationManager app;
    private Groups groups;
    private GroupData newGroup;

    @Before
    public void init() {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
        app.init();
    }

    @After
    public void stop() {
        app.stop();
        app = null;
    }

    @Given("^a set of groups$")
    public void loadGroups() {
        groups = app.db().groups();
    }

    @When("^I create a new group with name (.+), header (.+), footer (.+)$")
    public void createGroup(String name, String header, String footer) {
        newGroup = GroupDataBuilder.aGroupData().withName(name).withHeader(header).withFooter(footer).build();
        app.goTo().groupPage();
        app.group().create(newGroup);
    }

    @Then("^the new set of groups is equal to the old set with th added group$")
    public void verifyGroupCreation() {
        Groups newGroups = app.db().groups();
        newGroup.setId(newGroups.stream().mapToInt(GroupData::getId).max().getAsInt());
        MatcherAssert.assertThat(newGroups, equalTo(groups.withAdded(newGroup)));
    }
}
