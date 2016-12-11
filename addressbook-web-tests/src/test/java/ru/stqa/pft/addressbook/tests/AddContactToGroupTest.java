package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Fox on 08.12.2016.
 */
public class AddContactToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            File photo = new File("src/test/resources/photo.jpg");
            ContactData contact = new ContactData().withFirstName("Creation").withMidName("Test").withLastName("Main")
                    .withNick("chirp").withTitle("title").withCompany("PFT").withAddress("Dark st, 4//13-7")
                    .withHomePhone("6765867867979").withMobile("23477894").withWorkPhone("08653423").withFax("4654769")
                    .withPhoto(photo);
            app.contact().create(contact);
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withGroupName("Main_Group").withHeader("Log_text").withFooter("GroupList was empty"));
        }
    }

    @Test //(enabled = false)
    public void testAddContactToGroup(){
        Groups groups = app.db().groups();         // список групп
        Contacts contacts = app.db().contacts();   // список контактов
        app.goTo().homePage();
        ContactData selectedContact = contacts.iterator().next();
        Set<GroupData> groupsOfContact = selectedContact.getGroups();
        int idOfSelectedGroup = 0;
        GroupData selectedGroup = null;
        if (groups.size()> groupsOfContact.size()) {
            // ищем нужную группу
            Set<GroupData> freeGroups = groups;
            freeGroups.removeAll(selectedContact.getGroups());
            selectedGroup = freeGroups.iterator().next();
            idOfSelectedGroup = selectedGroup.getId();
        } else {
            // create new group
            app.goTo().groupPage();
            app.group().create(new GroupData().withGroupName("New_Group").withHeader("Log_text").withFooter("New Group for contact"));
            Groups newList = app.db().groups();
            idOfSelectedGroup = newList.stream().mapToInt((g) -> g.getId()).max().getAsInt();
            selectedGroup = app.db().selectGroupByIdDb(idOfSelectedGroup).iterator().next();
        }

        // блок начальных данных для сравнения
        Set<ContactData> beforeContacts = selectedGroup.getContacts();
        Set<GroupData> beforeGroups = selectedContact.getGroups();

        //add selectedContact to new group
        app.goTo().homePage();
        app.contact().initAddContactToGroup(selectedContact.getId(), idOfSelectedGroup);
        app.db().refreshGroup(selectedGroup);
        app.db().refreshContact(selectedContact);
        app.goTo().homePage();

        //блок конечных данных для сравнения
        Set<ContactData> afterContacts = selectedGroup.getContacts();
        Set<GroupData> afterGroups = selectedContact.getGroups();


        // проверка контактов в выбранной группе
        assertThat(afterContacts.size(), equalTo(beforeContacts.size() + 1));

        beforeContacts.add(selectedContact);
        assertThat(afterContacts, equalTo(beforeContacts));

        // проверка появления группы в контакте
        assertThat(afterGroups.size(), equalTo(beforeGroups.size() + 1));

        beforeGroups.add(selectedGroup);
        assertThat(afterGroups, equalTo(beforeGroups));
    }
}
