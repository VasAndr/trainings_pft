package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

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
     app.getGroupHelper().selectGroup();
     app.getGroupHelper().initGroupModification();
     app.getGroupHelper().fillGroupForm(new GroupData("Mod_Group1", "Log_text", "New_Comment_text"));
     app.getGroupHelper().submitGroupModification();
     app.getGroupHelper().returnToGroupPage();
 }
}
