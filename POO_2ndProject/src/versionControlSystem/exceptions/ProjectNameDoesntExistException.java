package versionControlSystem.exceptions;

public class ProjectNameDoesntExistException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "%s project does not exist.\n";


    // Instance variables
    private final String projectName;

    /**
     * Exception constructor
     *
     * @param projectName - <code>username</code> that caused the exception
     */
    public ProjectNameDoesntExistException(String projectName) {
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
