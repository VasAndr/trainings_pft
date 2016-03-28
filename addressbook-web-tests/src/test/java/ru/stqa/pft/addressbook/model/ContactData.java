package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
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

    private String group;

    public ContactData(int id, String firstName, String midName, String lastName, String nick, String title, String company, String address, String homePhone, String mobile, String workPhone, String fax,String group) {
        this.id = id;
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
        this.group = group;
    }

    public ContactData(String firstName, String midName, String lastName, String nick, String title, String company, String address, String homePhone, String mobile, String workPhone, String fax,String group) {
        this.id = Integer.MAX_VALUE;
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
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
