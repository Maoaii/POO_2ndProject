package versionControlSystem.user;

import versionControlSystem.project.Project;

import java.util.*;

public class ProjectManagerClass extends AbstractUserClass implements ProjectManager {
    //Instance variables
    private Set<User> developers; // Users ordered alphabetically
    private Map<String, Project> projectsAsManager; // Projects stored for fast access. projectName -> Project
    private List<Project> projectsAsManagerByInsertion; // Projects stored by insertion

    /**
     * Project Manager constructor
     *
     * @param username - this <code>ProjectManager</code>s <code>username</code>
     * @param clearanceLevel - this <code>ProjectManager</code>s <code>clearanceLevel</code>
     */
    public ProjectManagerClass(String username, int clearanceLevel) {
        super(username, clearanceLevel);
        developers = new TreeSet<>();
        projectsAsManager = new HashMap<>();
        projectsAsManagerByInsertion = new LinkedList<>();
    }

    @Override
    public void addProjectAsManager(Project project) {
        projectsAsManager.put(project.getProjectName(), project);
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

    @Override
    public Iterator<Project> getProjectsAsManager() {
        return projectsAsManagerByInsertion.iterator();
    }
}
