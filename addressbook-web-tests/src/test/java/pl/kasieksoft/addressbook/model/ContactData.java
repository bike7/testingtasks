package pl.kasieksoft.addressbook.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    private int id;

    private String firstname;
    private String lastname;

    @Transient
    private String bday;

    @Transient
    private String bmonth;

    @Transient
    private String byear;

    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Transient
    private String allPhones;

    @Type(type = "text")
    private String email;

    @Type(type = "text")
    private String email2;

    @Type(type = "text")
    private String email3;

    @Transient
    private String allEmails;

    @Transient
    @Type(type = "text")
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    private ContactData() {
    }

    public ContactData(int id, String firstname, String lastname, String bday, String bmonth, String byear, Groups groups,
                       String homePhone, String mobilePhone, String workPhone, String allPhones,
                       String email, String email2, String email3, String allEmails, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
        this.groups = groups;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.allPhones = allPhones;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.allEmails = allEmails;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
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

    public Groups getGroups() {
        return new Groups(groups);
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
