package versionControlSystem.exceptions;

/**
 * @author Lucas Girotto / Pedro Afonso
 */
public class ProjectIsOutsourcedException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "%s is an outsourced project.\n";


    // Instance variables
    private final String projectName;

    /**
     * Exception constructor
     *
     * @param projectName - <code>projectName</code> that caused the exception
     */
    public ProjectIsOutsourcedException(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the <code>projectName</code> that caused the exception
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
