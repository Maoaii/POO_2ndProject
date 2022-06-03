package versionControlSystem.exceptions;

/**
 * @author Lucas Girotto / Pedro Afonso
 */
public class ConfidentialityLevelHigherThanManagerException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "Project manager %s has clearance level %s.\n";

    // Instance variables
    private final String username;
    private final int clearanceLevel;

    /**
     * Exception constructor
     *
     * @param username - <code>username</code> that caused the exception
     */
    public ConfidentialityLevelHigherThanManagerException(String username, int clearanceLevel) {
        this.username = username;
        this.clearanceLevel = clearanceLevel;
    }

    /**
     * @return the <code>username</code> that caused the exception
     */
    public String getErrorInfoName() {
        return username;
    }

    /**
     * @return the <code>username</code> that caused the exception
     */
    public int getErrorInfoClearanceLevel() {
        return clearanceLevel;
    }

    /**
     * @return the <code>ERROR_MESSAGE</code> for this exception
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
