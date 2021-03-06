package versionControlSystem.user;

import versionControlSystem.project.Project;
import versionControlSystem.project.Revision;
import versionControlSystem.user.comparators.RevisionComparatorByDateNumberProjectName;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>User</code> Abstract Class. Has an username, clearance level, map and collection of <code>Project</code>s,
 * stored by insertion order,and a collection of <code>Revision</code>s, stored by date, number and project name.
 */
public class AbstractUserClass implements User {
    // Instance variables
    private final String username;
    private final int clearanceLevel;
    private final Map<String, Project> projects; // Map used for fast access. projectName -> Project
    private final List<Project> projectsByInsertion; // Projects ordered by insertion order
    private final Set<Revision> orderedRevisions; // Revisions ordered by date, number and projectName

    /**
     * Abstract User constructor
     *
     * @param username       - <code>User</code>s <code>username</code>
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
    public Iterator<Project> getProjects() {
        return projectsByInsertion.iterator();
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
    public int getCommonProjects(User other) {
        Iterator<Project> thisProjectIterator = this.getProjects();

        int sumProject = 0;

        while (thisProjectIterator.hasNext()) {
            Project project1 = thisProjectIterator.next();

            Iterator<Project> otherProjectIterator = other.getProjects();
            while (otherProjectIterator.hasNext()) {
                Project project2 = otherProjectIterator.next();

                if (project1.equals(project2))
                    sumProject++;
            }
        }


        return sumProject;
    }

    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.getUsername());
    }
}
