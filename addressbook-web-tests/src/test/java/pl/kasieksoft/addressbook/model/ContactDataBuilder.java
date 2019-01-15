package pl.kasieksoft.addressbook.model;

public final class ContactDataBuilder {
    private int id;
    private String firstname;
    private String lastname;
    private String bday;
    private String bmonth;
    private String byear;
    private Groups groups = new Groups();
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhones;
    private String email;
    private String email2;
    private String email3;
    private String allEmails;
    private String address;

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

    public ContactDataBuilder withGroups(Groups groups) {
        this.groups = groups;
        return this;
    }

    public ContactDataBuilder inGroup(GroupData group) {
        groups.add(group);
        return this;
    }

    public ContactDataBuilder withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactDataBuilder withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactDataBuilder withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactDataBuilder withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactDataBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactDataBuilder withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactDataBuilder withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactDataBuilder withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactDataBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData build() {
        return new ContactData(id, firstname, lastname, bday, bmonth, byear, groups, homePhone, mobilePhone, workPhone, allPhones, email, email2, email3, allEmails, address);
    }


}
