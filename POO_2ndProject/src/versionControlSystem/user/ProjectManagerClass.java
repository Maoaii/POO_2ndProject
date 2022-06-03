package versionControlSystem.user;

import versionControlSystem.project.Project;

import java.util.*;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>ProjectManager</code> Class. A subclass of <code>User</code>. Has a collection of <code>User</code>s,
 * stored alphabetically, and a collection of <code>Project</code>s, stored by insertion order.
 */
public class ProjectManagerClass extends AbstractUserClass implements ProjectManager {
    //Instance variables
    private final Set<User> developers; // Users ordered alphabetically
    private final List<Project> projectsAsManagerByInsertion; // Projects stored by insertion

    /**
     * Project Manager constructor
     *
     * @param username       - this <code>ProjectManager</code>s <code>username</code>
     * @param clearanceLevel - this <code>ProjectManager</code>s <code>clearanceLevel</code>
     */
    public ProjectManagerClass(String username, int clearanceLevel) {
        super(username, clearanceLevel);
        developers = new TreeSet<>();
        projectsAsManagerByInsertion = new LinkedList<>();
    }

    @Override
    public void addProjectAsManager(Project project) {
        projectsAsManagerByInsertion.add(project);
    }

    @Override
    public void addDeveloper(User developer) {
        developers.add(developer);
    }

    @Override
    public int getNumDevelopers() {
        return developers.size();
    }

    @Override
    public int getNumProjectsAsManagers() {
        return projectsAsManagerByInsertion.size();
    }

    @Override
    public Iterator<User> getDevelopers() {
        return developers.iterator();
    }
}
