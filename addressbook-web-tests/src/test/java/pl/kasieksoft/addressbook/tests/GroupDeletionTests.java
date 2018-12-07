package pl.kasieksoft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        if (!app.getGroupHelper().isThereAnyGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        before.sort(Comparator.comparingInt(GroupData::getId));
        after.sort(Comparator.comparingInt(GroupData::getId));
        Assert.assertEquals(before, after);
    }

}
