package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Fox on 10.12.2016.
 */
public class RemoveContactFromGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withGroupName("Main_Group").withHeader("Log_text").withFooter("GroupList was empty"));
        }
    }

    @Test //(enabled = false)
    public void testRemoveContactFromGroup(){
        Groups groups = app.db().groups();
        GroupData selectedGroup = groups.iterator().next();
        if (selectedGroup.getContacts().size() == 0) {
            app.goTo().homePage();
            File photo = new File("src/test/resources/photo.jpg");
            ContactData contact = new ContactData().withFirstName("Creation").withMidName("Test").withLastName("Main")
                    .withNick("chirp").withTitle("title").withCompany("PFT").withAddress("Dark st, 4//13-7")
                    .withHomePhone("6765867867979").withMobile("23477894").withWorkPhone("08653423").withFax("4654769")
                    .withPhoto(photo).inGroup(selectedGroup);
            app.contact().create(contact);
        }

        app.db().refreshGroup(selectedGroup);
        Set<ContactData> contactsInGroup = selectedGroup.getContacts();
        ContactData selectedContact = contactsInGroup.iterator().next();

        // блок начальных данных для сравнения
        Set<ContactData> beforeContacts = selectedGroup.getContacts();
        Set<GroupData> beforeGroups = selectedContact.getGroups();

        //remove selectedContact from group
        app.goTo().homePage();
        app.contact().initRemoveContactFromGroup(selectedContact.getId(), selectedGroup.getId(), selectedGroup.getGroupname());
        app.goTo().homePage();
        app.db().refreshGroup(selectedGroup);
        app.db().refreshContact(selectedContact);

        //блок конечных данных для сравнения
        Set<ContactData> afterContacts = selectedGroup.getContacts();
        Set<GroupData> afterGroups = selectedContact.getGroups();

        // проверка контактов в выбранной группе
        assertThat(afterContacts.size(), equalTo(beforeContacts.size() - 1));

        beforeContacts.remove(selectedContact);
        assertThat(afterContacts, equalTo(beforeContacts));

        // проверка появлкния группы в контакте
        assertThat(afterGroups.size(), equalTo(beforeGroups.size() - 1));

        beforeGroups.remove(selectedGroup);
        assertThat(afterGroups, equalTo(beforeGroups));
    }
}
