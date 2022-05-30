package versionControlSystem.exceptions;

public class UserNameDoesntExistException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "User %s does not exist.\n";


    // Instance variables
    private final String username;

    /**
     * Exception constructor
     *
     * @param username - <code>username</code> that caused the exception
     */
    public UserNameDoesntExistException(String username) {
        this.username = username;
    }

    /**
     * @return the <code>username</code> that caused the exception
     */
    public String getErrorInfo() {
        return username;
    }

    /**
     * @return the <code>ERROR_MESSAGE</code> for this exception
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
