package versionControlSystem.exceptions;

public class UnknownProjectTypeException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "Unknown project type.\n";

    /**
     * Exception constructor
     *
     * @param username - <code>username</code> that caused the exception
     */
    public UnknownProjectTypeException() {

    }

    /**
     * @return the <code>ERROR_MESSAGE</code> for this exception
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
