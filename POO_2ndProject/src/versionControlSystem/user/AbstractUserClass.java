package versionControlSystem.user;

import versionControlSystem.project.Project;
import versionControlSystem.project.Revision;

import java.util.*;

public class AbstractUserClass implements User {
    // Instance variables
    private String username;
    private int clearanceLevel;
    private Map<String, Project> projects; // Map used for fast access
    private List<Project> projectsByInsertion; // Projects ordered by insertion order

    public AbstractUserClass(String username, int clearanceLevel) {
        this.username = username;
        this.clearanceLevel = clearanceLevel;
        projects = new HashMap<>();
        projectsByInsertion = new LinkedList<>();
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
        return null;
    }

    @Override
    public Date getDateOfLastRevision() {
        return null;
    }

    @Override
    public void addProject(Project project) {
        projects.put(project.getProjectName(), project);
        projectsByInsertion.add(project);
    }
}
