package versionControlSystem.exceptions;

public class ArtefactNotInProjectException extends Exception {
    // Constants
    private static final String ERROR_MESSAGE = "%s does not exist in the project.\n";


    // Instance variables
    private String artefactName;

    /**
     * Exception constructor
     *
     * @param artefactName - <code>artefactName</code> that caused the exception
     */
    public ArtefactNotInProjectException(String artefactName) {
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
