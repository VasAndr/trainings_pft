package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest  extends TestBase {

    @Test //(enabled = false)
    public void testContactCreation() {
        Groups groups = app.db().groups();
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        File photo = new File("src/test/resources/photo.jpg");
        ContactData contact = new ContactData().withFirstName("Andr").withMidName("Serg").withLastName("Vas")
                .withNick("chirp").withTitle("title").withCompany("PFT").withAddress("Dark st, 4//13-7")
                .withHomePhone("6765867867979").withMobile("23477894").withWorkPhone("08653423").withFax("4654769").withPhoto(photo)
                .inGroup(groups.iterator().next());
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }

    @Test (enabled = false)
    public void testBadContactCreation() {
        Groups groups = app.db().groups();
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData contact = new ContactData().withFirstName("D'Andr").withMidName("Serg").withLastName("Vas")
                .withNick("chirp").withTitle("title").withCompany("PFT").withAddress("Dark st, 4//13-7")
                .withHomePhone("6765867867979").withMobile("23477894").withWorkPhone("08653423").withFax("4654769")
                .inGroup(groups.iterator().next());
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before));
        verifyContactListInUI();
    }
}
