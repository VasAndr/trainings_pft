package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Hermit on 13.03.2016.
 */
public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Andr", "Serg", "Vas", "chirp", "title", "PFT", "Dark st, 4//13-7", "6765867867979", "23477894", "08653423", "4654769", "Group1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
    }

}
