package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Hermit on 13.03.2016.
 */
public class ContactModificationTest extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().editSelectedContacts();
        app.getContactHelper().fillContactForm(new ContactData("Andr", "New", "Vas", "chirp", "title", "PFT", "New Dark st, 4//13-7", "6765867867979", "23477894", "08653423", "4654769", null), false);
        app.getContactHelper().updateSelectedContact();
        app.getNavigationHelper().returnToHomePage();
    }
}
