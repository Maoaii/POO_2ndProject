package versionControlSystem.exceptions;

public class ArtefactExceedsConfidentialityException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "%s: exceeds project confidentiality level.\n";


    // Instance variables
    private final String artefactName;

    /**
     * Exception constructor
     *
     * @param artefactName - <code>artefactName</code> that caused the exception
     */
    public ArtefactExceedsConfidentialityException(String artefactName) {
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
