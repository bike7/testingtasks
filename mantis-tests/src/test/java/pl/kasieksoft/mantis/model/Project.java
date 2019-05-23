package pl.kasieksoft.mantis.model;

public class Project {

    private int id;
    private String name;

    void setId(int id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
