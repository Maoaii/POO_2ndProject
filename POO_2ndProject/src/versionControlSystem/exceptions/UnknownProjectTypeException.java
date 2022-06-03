package versionControlSystem.exceptions;

/**
 * @author Lucas Girotto / Pedro Afonso
 */
public class UnknownProjectTypeException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "Unknown project type.\n";

    /**
     * Exception constructor
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
