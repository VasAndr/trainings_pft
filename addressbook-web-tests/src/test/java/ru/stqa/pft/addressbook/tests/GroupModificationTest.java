package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Hermit on 13.03.2016.
 */
public class GroupModificationTest extends TestBase{

 @BeforeMethod
 public void ensurePreconditions() {
     app.goTo().groupPage();
     if (app.group().all().size() == 0) {
         app.group().create(new GroupData().withGroupName("Ex_Group1").withHeader("Log_text").withFooter("Comment_text"));
     }
 }

 @Test
    public void testGroupModification(){
     Groups before = app.group().all();
     GroupData modifiedGroup = before.iterator().next();
     GroupData group = new GroupData().withId(modifiedGroup.getId()).withGroupName("Mod_Group1").withHeader("Log_text").withFooter("New_Comment_text");
     app.group().modify(group);
     assertThat(app.group().count(), equalTo(before.size()));
     Groups after = app.group().all();
     assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
 }

}
