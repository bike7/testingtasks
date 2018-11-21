package pl.kasieksoft.addressbook;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String phoneHome;
    private final String email;
    private final String bday;
    private final String bmonth;
    private final String byear;

    public ContactData(String firstname, String lastname, String phoneHome, String email, String bday, String bmonth, String byear) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneHome = phoneHome;
        this.email = email;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
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
}
