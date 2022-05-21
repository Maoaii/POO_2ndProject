package versionControlSystem.exceptions;

public class ProjectNameAlreadyExistsException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "%s project already exists.\n";


    // Instance variables
    private String projectName;

    /**
     * Exception constructor
     *
     * @param projectName - <code>username</code> that caused the exception
     */
    public ProjectNameAlreadyExistsException(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the <code>username</code> that caused the exception
     */
    public String getErrorInfo() {
        return projectName;
    }

    /**
     * @return the <code>ERROR_MESSAGE</code> for this exception
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
