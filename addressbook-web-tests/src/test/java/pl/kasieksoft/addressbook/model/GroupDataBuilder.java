package pl.kasieksoft.addressbook.model;

public class GroupDataBuilder {
    private int id = Integer.MAX_VALUE;
    private String name;
    private String header;
    private String footer;

    private GroupDataBuilder() {
    }

    public static GroupDataBuilder aGroupData() {
        return new GroupDataBuilder();
    }

    public GroupDataBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public GroupDataBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GroupDataBuilder withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupDataBuilder withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public GroupData build() {
        return new GroupData(id, name, header, footer);
    }
}