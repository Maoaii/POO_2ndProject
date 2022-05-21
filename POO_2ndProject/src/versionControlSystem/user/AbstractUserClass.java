package versionControlSystem.user;

import versionControlSystem.project.Project;
import versionControlSystem.project.Revision;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AbstractUserClass implements User {
    // Instance variables
    private String username;
    private int clearanceLevel;
    private List<Project> projects;

    public AbstractUserClass(String username, int clearanceLevel) {
        this.username = username;
        this.clearanceLevel = clearanceLevel;
        projects = new LinkedList<>();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getNumProjectsAsMember() {
        return projects.size();
    }

    @Override
    public int getClearanceLevel() {
        return clearanceLevel;
    }

    @Override
    public Iterator<Revision> getUserRevisions() {
        return null;
    }

    @Override
    public Date getDateOfLastRevision() {
        return null;
    }
}
