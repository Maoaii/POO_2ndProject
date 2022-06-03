package versionControlSystem.exceptions;

/**
 * @author Lucas Girotto / Pedro Afonso
 */
public class DeveloperNotMemberException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "User %s does not belong to the team of %s.\n";


    // Instance variables
    private final String username;
    private final String projectName;

    /**
     * Exception constructor
     *
     * @param username - <code>username</code> that caused the exception
     */
    public DeveloperNotMemberException(String username, String projectName) {
        this.username = username;
        this.projectName = projectName;
    }

    /**
     * @return the <code>username</code> that caused the exception
     */
    public String getErrorInfoName() {
        return username;
    }

    /**
     * @return the <code>teamName</code> that caused the exception
     */
    public String getErrorInfoTeamName() {
        return projectName;
    }

    /**
     * @return the <code>ERROR_MESSAGE</code> for this exception
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
