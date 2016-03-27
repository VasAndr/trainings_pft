package ru.stqa.pft.addressbook.tests;

import javafx.print.Collation;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
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
     GroupData group = new GroupData(before.get(before.size() - 1).getId(), "Mod_Group1", "Log_text", "New_Comment_text");
     app.getGroupHelper().fillGroupForm(group);
     app.getGroupHelper().submitGroupModification();
     app.getGroupHelper().returnToGroupPage();
  List<GroupData> after = app.getGroupHelper().getGroupList();
  Assert.assertEquals(after.size(), before.size());

  before.remove(before.size() - 1);
  before.add(group);
  //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    //Add sorting from m10
  Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals(before, after);
 }
}
