package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.GroupData;
import pl.kasieksoft.addressbook.model.GroupDataBuilder;
import pl.kasieksoft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = GroupDataBuilder.aGroupData().withName("test1").build();
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        group.setId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(before.withAdded(group)));
    }

}
