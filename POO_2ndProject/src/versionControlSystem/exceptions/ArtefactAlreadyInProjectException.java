package versionControlSystem.exceptions;

/**
 * @author Lucas Girotto / Pedro Afonso
 */
public class ArtefactAlreadyInProjectException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "%s: already in the project.\n";


    // Instance variables
    private final String artefactName;

    /**
     * Exception constructor
     *
     * @param artefactName - <code>artefactName</code> that caused the exception
     */
    public ArtefactAlreadyInProjectException(String artefactName) {
        this.artefactName = artefactName;
    }

    /**
     * @return the <code>artefactName</code> that caused the exception
     */
    public String getErrorInfo() {
        return artefactName;
    }

    /**
     * @return the <code>ERROR_MESSAGE</code> for this exception
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
