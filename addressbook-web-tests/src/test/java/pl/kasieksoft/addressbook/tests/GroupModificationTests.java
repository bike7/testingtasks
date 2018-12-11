package pl.kasieksoft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAnyGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupModification() {

        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(), "test1 modified", "test2 modified", "test3 modified");
        app.getGroupHelper().modifyGroup(index, group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        before.sort(Comparator.comparingInt(GroupData::getId));
        after.sort(Comparator.comparingInt(GroupData::getId));
        Assert.assertEquals(before, after);
    }
}
