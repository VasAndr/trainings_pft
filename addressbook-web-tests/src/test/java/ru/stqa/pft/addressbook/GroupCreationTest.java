package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("Ex_Group1", "Log_text", "Comment_text"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
