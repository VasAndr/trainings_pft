package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Hermit on 13.03.2016.
 */
public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            File photo = new File("src/test/resources/photo.jpg");
            ContactData contact = new ContactData().withFirstName("Andr").withMidName("Serg").withLastName("Vas")
                    .withNick("chirp").withTitle("title").withCompany("PFT").withAddress("Dark st, 4//13-7")
                    .withHomePhone("6765867867979").withMobile("23477894").withWorkPhone("08653423").withFax("4654769")
                    .withPhoto(photo);
            app.contact().create(contact);
        }
    }

    @Test //(enabled = false)
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Modify").withMidName("New")
                .withLastName("Vas").withNick("chirp").withTitle("title").withCompany("PFT").withAddress("New Dark st, 4//13-7")
                .withHomePhone("6765867867979").withMobile("23477894").withWorkPhone("08653423").withFax("4654769");
        app.goTo().homePage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }

}
