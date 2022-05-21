package versionControlSystem.exceptions;

public class DeveloperNotMemberException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "User %s does not belong to the team of %s.\n";


    // Instance variables
    private String username;
    private String teamName;

    /**
     * Exception constructor
     *
     * @param username - <code>username</code> that caused the exception
     */
    public DeveloperNotMemberException(String username, String teamName) {
        this.username = username;
        this.teamName = teamName;
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
        return teamName;
    }

    /**
     * @return the <code>ERROR_MESSAGE</code> for this exception
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
