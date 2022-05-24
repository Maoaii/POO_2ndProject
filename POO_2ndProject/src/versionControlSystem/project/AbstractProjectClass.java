package versionControlSystem.project;

import versionControlSystem.user.User;

import java.util.*;

public class AbstractProjectClass implements Project {
    // Instance variables
    private User manager;
    private String projectName;
    private String[] keywords;


    /**
     * Abstract Project constructor
     */
    public AbstractProjectClass(User manager, String projectName, String[] keywords) {
        this.manager = manager;
        this.projectName = projectName;
        this.keywords = keywords;
    }

    @Override
    public String getProjectName() {
        return projectName;
    }

    @Override
    public String getProjectManagerUsername() {
        return manager.getUsername();
    }

    @Override
    public int getProjectManagerClearanceLevel() {
        return manager.getClearanceLevel();
    }
}
