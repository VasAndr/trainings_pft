package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by Hermit on 13.03.2016.
 */
public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
    }

}
