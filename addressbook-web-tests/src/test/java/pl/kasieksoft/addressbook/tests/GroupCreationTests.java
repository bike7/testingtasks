package pl.kasieksoft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.kasieksoft.addressbook.model.GroupData;
import pl.kasieksoft.addressbook.model.GroupDataBuilder;
import pl.kasieksoft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{GroupDataBuilder.aGroupData().withName("test 1").withHeader("header 1").withFooter("footer 1").build()});
        list.add(new Object[]{GroupDataBuilder.aGroupData().withName("test 2").withHeader("header 2").withFooter("footer 2").build()});
        list.add(new Object[]{GroupDataBuilder.aGroupData().withName("test 3").withHeader("header 3").withFooter("footer 3").build()});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        group.setId(after.stream().mapToInt(GroupData::getId).max().getAsInt());
        assertThat(after, equalTo(before.withAdded(group)));
    }

}
