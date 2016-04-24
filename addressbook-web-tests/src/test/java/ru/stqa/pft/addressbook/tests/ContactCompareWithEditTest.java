package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Hermit on 23.04.2016.
 */
public class ContactCompareWithEditTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Andr").withMidName("Serg").withLastName("Vas")
                    .withNick("chirp").withTitle("title").withCompany("PFT").withAddress("Dark st, 4//13-7")
                    .withHomePhone("+7(676)586786").withMobile("234-778-94").withWorkPhone("08 63 423").withFax("4654769")
                    .witheMail2("new@new.com").inGroup("Ex_Group1"));
        }
    }

    @Test //(enabled = false)
    public void testContactPhones () {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAlleMails(), equalTo(mergeMails(contactInfoFromEditForm)));
    }

    private String mergeMails(ContactData contact) {
        return Arrays.asList(contact.geteMail(), contact.geteMail2(), contact.geteMail3())
                .stream().filter((s) -> ! s.equals("")).map(ContactCompareWithEditTest::cleaned).collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals("")).map(ContactCompareWithEditTest::cleaned).collect(Collectors.joining("\n"));
    }


    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
