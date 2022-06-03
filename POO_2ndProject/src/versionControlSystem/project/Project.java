package versionControlSystem.project;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>Project</code> Interface. Holds all the functions a <code>Project</code> has access to.
 */
public interface Project extends Comparable<Project> {

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

    /**
     * @return this <code>Project</code>s <code>keywords</code>
     */
    String[] getKeywords();

    /**
     * @param keyword - the keyword to be tested
     * @return true if the project contains this keyword
     */
    boolean hasKeyword(String keyword);
}