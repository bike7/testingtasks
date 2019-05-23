package pl.kasieksoft.mantis.model;

public final class IssueBuilder {
    private int id;
    private String summary;
    private String description;
    private Project project;

    private IssueBuilder() {
    }

    public static IssueBuilder anIssue() {
        return new IssueBuilder();
    }

    public IssueBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public IssueBuilder withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public IssueBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public IssueBuilder withProject(Project project) {
        this.project = project;
        return this;
    }

    public Issue build() {
        Issue issue = new Issue();
        issue.setId(id);
        issue.setSummary(summary);
        issue.setDescription(description);
        issue.setProject(project);
        return issue;
    }
}
