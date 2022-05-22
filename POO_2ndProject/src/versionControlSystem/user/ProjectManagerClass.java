package versionControlSystem.user;

import versionControlSystem.project.Project;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProjectManagerClass extends AbstractUserClass implements ProjectManager {
    //Instance variables
    private List<User> developers;
    private List<Project> projectsAsManager;

    public ProjectManagerClass(String username, int clearanceLevel) {
        super(username, clearanceLevel);
        developers = new LinkedList<>();
        projectsAsManager = new LinkedList<>();
    }

    @Override
    public void addProjectAsManager(Project project) {
        projectsAsManager.add(project);
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
        return projectsAsManager.size();
    }

    @Override
    public Iterator<User> getDevelopers() {
        return developers.iterator();
    }
}
