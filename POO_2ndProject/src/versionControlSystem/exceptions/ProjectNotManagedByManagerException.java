package versionControlSystem.exceptions;

public class ProjectNotManagedByManagerException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "%s is managed by %s.\n";


    // Instance variables
    private final String username;
    private final String projectName;

    /**
     * Exception constructor
     *
     * @param username    - <code>username</code> that caused the exception
     * @param projectName - <code>projectName</code> that caused the exception
     */
    public ProjectNotManagedByManagerException(String username, String projectName) {
        this.username = username;
        this.projectName = projectName;
    }

    /**
     * @return the <code>username</code> that caused the exception
     */
    public String getErrorInfoName() {
        return username;
    }

    public String getErrorInfoProjectName() {
        return projectName;
    }

    /**
     * @return the <code>ERROR_MESSAGE</code> for this exception
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
