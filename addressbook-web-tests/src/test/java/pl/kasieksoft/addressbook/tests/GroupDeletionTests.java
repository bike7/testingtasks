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
        int index = before.size() - 1;
        if (!app.getGroupHelper().isThereAnyGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        app.getGroupHelper().selectGroup(index);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        before.sort(Comparator.comparingInt(GroupData::getId));
        after.sort(Comparator.comparingInt(GroupData::getId));
        Assert.assertEquals(before, after);
    }

}
