package pl.kasieksoft.addressbook.model;

public final class ContactDataBuilder {
    private int id;
    private String firstname;
    private String lastname;
    private String phoneHome;
    private String email;
    private String bday;
    private String bmonth;
    private String byear;
    private String group;

    private ContactDataBuilder() {
    }

    public static ContactDataBuilder aContactData() {
        return new ContactDataBuilder();
    }

    public ContactDataBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public ContactDataBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactDataBuilder withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactDataBuilder withPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
        return this;
    }

    public ContactDataBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactDataBuilder withBday(String bday) {
        this.bday = bday;
        return this;
    }

    public ContactDataBuilder withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactDataBuilder withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactDataBuilder withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData build() {
        return new ContactData(id, firstname, lastname, phoneHome, email, bday, bmonth, byear, group);
    }
}
