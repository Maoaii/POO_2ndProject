package versionControlSystem.user;

import versionControlSystem.project.Project;
import versionControlSystem.project.Revision;
import versionControlSystem.project.RevisionClass;
import versionControlSystem.user.comparators.RevisionComparatorByDateNumberProjectName;

import java.time.LocalDate;
import java.util.*;

public class AbstractUserClass implements User {
    // Instance variables
    private String username;
    private int clearanceLevel;
    private Map<String, Project> projects; // Map used for fast access. projectName -> Project
    private List<Project> projectsByInsertion; // Projects ordered by insertion order
    private Set<Revision> orderedRevisions; // Revisions ordered by date, number and projectName

    /**
     * Abstract User constructor
     *
     * @param username - <code>User</code>s <code>username</code>
     * @param clearanceLevel - <code>User</code>s <code>clearanceLevel</code>
     */
    public AbstractUserClass(String username, int clearanceLevel) {
        this.username = username;
        this.clearanceLevel = clearanceLevel;
        projects = new HashMap<>();
        projectsByInsertion = new LinkedList<>();
        orderedRevisions = new TreeSet<>(new RevisionComparatorByDateNumberProjectName());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getNumProjectsAsMember() {
        return projectsByInsertion.size();
    }

    @Override
    public int getClearanceLevel() {
        return clearanceLevel;
    }

    @Override
    public boolean isMember(String projectName) {
        return projects.containsKey(projectName);
    }

    @Override
    public Iterator<Revision> getUserRevisions() {
        return orderedRevisions.iterator();
    }

    @Override
    public LocalDate getDateOfLastRevision() {
        return orderedRevisions.iterator().next().getRevisionDate();
    }

    @Override
    public void addProject(Project project) {
        projects.put(project.getProjectName(), project);
        projectsByInsertion.add(project);
    }

    @Override
    public void addRevision(Revision revision) {
        orderedRevisions.add(revision);
    }

    @Override
    public int getNumRevisions() {
        return orderedRevisions.size();
    }

    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.getUsername());
    }
}
