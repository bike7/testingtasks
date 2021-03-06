package pl.kasieksoft.mantis.tests;

import org.testng.annotations.Test;
import pl.kasieksoft.mantis.model.Issue;
import pl.kasieksoft.mantis.model.IssueBuilder;
import pl.kasieksoft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = IssueBuilder.anIssue()
                .withSummary("Test issue")
                .withDescription("Test issue description")
                .withProject(projects.iterator().next())
                .build();
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getDescription(), created.getDescription());
    }
}
