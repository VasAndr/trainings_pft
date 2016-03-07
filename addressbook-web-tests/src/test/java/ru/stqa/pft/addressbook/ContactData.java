package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstName;
    private final String midName;
    private final String lastName;
    private final String nick;
    private final String title;
    private final String company;
    private final String address;
    private final String homePhone;
    private final String mobile;
    private final String workPhone;
    private final String fax;

    public ContactData(String firstName, String midName, String lastName, String nick, String title, String company, String address, String homePhone, String mobile, String workPhone, String fax) {
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.nick = nick;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobile = mobile;
        this.workPhone = workPhone;
        this.fax = fax;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMidName() {
        return midName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNick() {
        return nick;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFax() {
        return fax;
    }
}
