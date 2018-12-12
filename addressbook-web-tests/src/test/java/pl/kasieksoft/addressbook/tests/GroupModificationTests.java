package pl.kasieksoft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.GroupData;
import pl.kasieksoft.addressbook.model.GroupDataBuilder;

import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(GroupDataBuilder.aGroupData().withName("test1").build());
        }
    }

    @Test
    public void testGroupModification() {

        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();

        GroupData group = GroupDataBuilder.aGroupData().withId(modifiedGroup.getId()).withName("test1 modified").withHeader("test2 modified").withFooter("test3 modified").build();
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }
}
