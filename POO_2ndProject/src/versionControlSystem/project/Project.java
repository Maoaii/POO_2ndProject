package versionControlSystem.project;

public interface Project {

    /**
     * @return the <code>projectName</code> of this <code>Project</code>
     */
    String getProjectName();

    /**
     * @return the <code>username</code> of this <code>Project</code>s <code>ProjectManager</code>
     */
    String getProjectManagerUsername();

    /**
     * @return the <code>clearanceLevel</code> of this <code>Project</code>s <code>ProjectManager</code>
     */
    int getProjectManagerClearanceLevel();
}