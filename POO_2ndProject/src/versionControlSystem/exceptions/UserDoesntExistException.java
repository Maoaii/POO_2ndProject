package versionControlSystem.exceptions;

public class UserDoesntExistException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "%s: does not exist.\n";


    // Instance variables
    private String username;

    /**
     * Exception constructor
     *
     * @param username - <code>username</code> that caused the exception
     */
    public UserDoesntExistException(String username) {
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
