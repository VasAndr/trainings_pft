package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Hermit on 13.03.2016.
 */
public class ContactModificationTest extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Andr", "Serg", "Vas", "chirp", "title", "PFT", "Dark st, 4//13-7", "6765867867979", "23477894", "08653423", "4654769", "Ex_Group1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().editSelectedContacts();
        ContactData contact = new ContactData(before.get(0).getId(),"Modify", "New", "Vas", "chirp", "title", "PFT", "New Dark st, 4//13-7", "6765867867979", "23477894", "08653423", "4654769", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateSelectedContact();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(0);
        before.add(contact);
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        //Add sorting from m10
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
