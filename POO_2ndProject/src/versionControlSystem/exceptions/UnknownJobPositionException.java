package versionControlSystem.exceptions;

/**
 * @author Lucas Girotto / Pedro Afonso
 */
public class UnknownJobPositionException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "Unknown job position.";

    /**
     * Exception constructor
     */
    public UnknownJobPositionException() {
    }

    /**
     * @return the <code>ERROR_MESSAGE</code> for this exception
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
