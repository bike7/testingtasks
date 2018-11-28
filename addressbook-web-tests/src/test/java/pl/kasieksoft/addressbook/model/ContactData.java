package pl.kasieksoft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String phoneHome;
    private final String email;
    private final String bday;
    private final String bmonth;
    private final String byear;
    private final String group;

    public ContactData(String firstname, String lastname, String phoneHome, String email, String bday, String bmonth, String byear, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneHome = phoneHome;
        this.email = email;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getEmail() {
        return email;
    }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }

    public String getGroup() {
        return group;
    }
}
