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
     *
     * @param manager - this <code>Project</code>s <code>ProjectManager</code>
     * @param projectName - this <code>Project</code>s <code>projectName</code>
     * @param keywords - this <code>Project</code>s <code>keywords</code>
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

	@Override
	public boolean hasKeyword(String keyword) {
		for(int i = 0; i < keywords.length; i++) {
			if(keyword.equals(keywords[i])) {
				return true;
			}
		}
		return false;
	}
}
