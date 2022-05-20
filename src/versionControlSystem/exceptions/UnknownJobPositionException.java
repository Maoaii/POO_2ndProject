package versionControlSystem.exceptions;

public class UnknownJobPositionException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "Unknown job position.";

    /**
     * Exception constructor
     */
    public UnknownJobPositionException() {
        super();
    }

    /**
     * @return the <code>ERROR_MESSAGE</code> for this exception
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
