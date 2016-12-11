package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hermit on 13.03.2016.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) { super(wd);  }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMidName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNick());
        attach(By.name("photo"), contactData.getPhoto());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.geteMail());
        type(By.name("email2"), contactData.geteMail2());
        type(By.name("email3"), contactData.geteMail3());
        if (creation) {
            if (contactData.getGroups().size() >0) {
                Assert.assertTrue(contactData.getGroups().size() ==1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getGroupname());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void editSelectedContacts() { click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void updateSelectedContact() {click(By.name("update"));
    }

    public void returnToHomePage() {
       click(By.linkText("home page"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        editSelectedContacts();
        fillContactForm(contact, false);
        updateSelectedContact();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void initContactCreation() {
        if (isElementPresent(By.name("submit"))) {
            return;
        }
        click(By.linkText("add new"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String alleMails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("id"));
            contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
                    .withAddress(address).withAlleMails(alleMails).withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String midname = wd.findElement(By.name("middlename")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
        String title = wd.findElement(By.name("title")).getAttribute("value");
        String company = wd.findElement(By.name("company")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String fax = wd.findElement(By.name("fax")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withMidName(midname).withLastName(lastname)
                .withNick(nickname).withTitle(title).withCompany(company).withAddress(address)
                .witheMail(email).witheMail2(email2).witheMail3(email3)
                .withHomePhone(home).withMobile(mobile).withWorkPhone(work).withFax(fax);
    }

    private void initContactModificationById(int id) {
        wd. findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

        // wd. findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
        // wd. findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
        /*
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[@value='%s']", id)));
        WebElement row = wd.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
         */
    }

    public String infoFromDetailsForm(ContactData contact) {
        initContactDetailsById(contact.getId());
        String details = wd.findElement(By.id("content")).getText();
        // System.out.println(details);
        wd.navigate().back();
        return details;
    }

    private void initContactDetailsById(int id) {
        wd. findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }

    public String mergeInfo(ContactData contact) {
        String details = mergeFullName(contact) //+ "\n"
                + exist(contact.getNick()) + "\n" + exist(contact.getTitle()) + exist(contact.getCompany()) + exist(contact.getAddress()) + "\n"
                + existPhone(contact.getHomePhone(), "H") + existPhone(contact.getMobile(), "M") + existPhone(contact.getWorkPhone(), "W")
                + existPhone(contact.getFax(), "F")
                + existMail(contact.geteMail()) + existMail(contact.geteMail2()) + existMail(contact.geteMail3());
        // System.out.println("*********\n" + details + "\n**********");
        return details;
    }

    private String exist(String fieldValue) {
        if (!fieldValue.isEmpty()) {
            return "\n" + fieldValue ;
        }
        return "";
    }

    private String existPhone(String phone, String phoneType) {
        if (!phone.isEmpty()) {
            return  "\n" + phoneType + ": " + phone;
        }
        return "";
    }

    private String existMail(String fieldValue) {
        if (!fieldValue.isEmpty()) {
            String[] dom = fieldValue.split("@");
            return ("\n" + fieldValue); // + " (www."+ dom[1] + ")");
        }
        return "";
    }

    private String mergeFullName(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getMidName(), contact.getLastName())
                .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining(" "));
    }

    private void selectGroupForAddTo (int groupId) {
        wd.findElement(By.xpath("//div[@class='right']/select[@name='to_group']/option[@value='" + groupId +"']")).click();
    }

    public void initAddContactToGroup (int contactId, int groupId) {
        wd.findElement(By.cssSelector("input[value='"+contactId+"']")).click();
        wd.findElement(By.xpath("//div[@class='right']/select[@name='to_group']/option[@value='" + groupId +"']")).click();
        wd.findElement(By.name("add")).click();
    }

    public void contactsForSelectedGroup (int groupId) {
        wd.findElement(By.xpath("//form[@id='right']/select[@name='group']/option[@value='" + groupId +"']")).click();
    }

    public void initRemoveContactFromGroup (int contactId, int groupId, String groupName) {
        contactsForSelectedGroup (groupId);
        wd.findElement(By.cssSelector("input[value='"+contactId+"']")).click();
        wd.findElement(By.xpath("//div[@class='left']/input[@value='Remove from \"" + groupName + "\"']")).click();
    }
}
