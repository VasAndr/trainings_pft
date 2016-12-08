package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Hermit on 24.04.2016.
 */
public class DetailsCompareWithEditTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Andr").withMidName("Serg").withLastName("Vas")
                    .withNick("chirp").withTitle("title").withCompany("PFT").withAddress("Dark st, 4//13-7")
                    .withHomePhone("+7(676)586786").withMobile("234-778-94").withWorkPhone("08 63 423")
                    .witheMail2("new@new.com"));  //.inGroup("Ex_Group1")
        }
    }

    @Test //(enabled = false)
    public void testContactDetails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        String contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contactInfoFromDetailsForm, equalTo(app.contact().mergeInfo(contactInfoFromEditForm)));
    }

}
