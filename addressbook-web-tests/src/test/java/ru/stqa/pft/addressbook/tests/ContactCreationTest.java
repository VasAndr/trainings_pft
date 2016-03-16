package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest  extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Andr", "Serg", "Vas", "chirp", "title", "PFT", "Dark st, 4//13-7", "6765867867979", "23477894", "08653423", "4654769", "Group1"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToHomePage();
    }







}
