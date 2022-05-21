package versionControlSystem.project;

import versionControlSystem.user.User;

import java.util.*;

public class AbstractProjectClass implements Project {
    // Instance variables
    private String managerUsername;
    private String projectName;
    private String[] keywords;


    /**
     * Abstract Project constructor
     */
    public AbstractProjectClass(String managerUsername, String projectName, String[] keywords) {
        this.managerUsername = managerUsername;
        this.projectName = projectName;
        this.keywords = keywords;
    }

    @Override
    public String getProjectName() {
        return projectName;
    }

    @Override
    public String getProjectManagerUsername() {
        return managerUsername;
    }
}
