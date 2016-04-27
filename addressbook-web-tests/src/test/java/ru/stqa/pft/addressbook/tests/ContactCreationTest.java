package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest  extends TestBase {

    @Test //(enabled = false)
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/photo.jpg");
        ContactData contact = new ContactData().withFirstName("Andr").withMidName("Serg").withLastName("Vas")
                .withNick("chirp").withTitle("title").withCompany("PFT").withAddress("Dark st, 4//13-7")
                .withHomePhone("6765867867979").withMobile("23477894").withWorkPhone("08653423").withFax("4654769")
                .withPhoto(photo);
               // .inGroup("Ex_Group1");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void testBadContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("D'Andr").withMidName("Serg").withLastName("Vas")
                .withNick("chirp").withTitle("title").withCompany("PFT").withAddress("Dark st, 4//13-7")
                .withHomePhone("6765867867979").withMobile("23477894").withWorkPhone("08653423").withFax("4654769")
                .inGroup("Ex_Group1");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before));
    }
}
