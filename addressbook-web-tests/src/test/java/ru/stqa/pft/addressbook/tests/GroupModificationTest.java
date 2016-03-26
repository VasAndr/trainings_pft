package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Hermit on 13.03.2016.
 */
public class GroupModificationTest extends TestBase{

 @Test
    public void testGroupModification(){
     app.getNavigationHelper().gotoGroupPage();
     if (! app.getGroupHelper().isThereAGroup()) {
         app.getGroupHelper().createGroup(new GroupData("Ex_Group1", "Log_text", "Comment_text"));
     }
     List<GroupData> before = app.getGroupHelper().getGroupList();
     app.getGroupHelper().selectGroup(before.size() - 1);
     app.getGroupHelper().initGroupModification();
     app.getGroupHelper().fillGroupForm(new GroupData("Mod_Group1", "Log_text", "New_Comment_text"));
     app.getGroupHelper().submitGroupModification();
     app.getGroupHelper().returnToGroupPage();
  List<GroupData> after = app.getGroupHelper().getGroupList();
  Assert.assertEquals(after.size(), before.size());
 }
}
