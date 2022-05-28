package versionControlSystem.exceptions;

public class NoProjectsWithKeywordException extends Exception {
	// Constants
    private static final String ERROR_MESSAGE = "No projects with keyword %s.\n";


    // Instance variables
    private String keyword;

    /**
     * Exception constructor
     *
     * @param projectName - <code>username</code> that caused the exception
     */
    public NoProjectsWithKeywordException(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return the <code>projectName</code> that caused the exception
     */
    public String getErrorInfo() {
        return keyword;
    }

    /**
     * @return the <code>ERROR_MESSAGE</code> for this exception
     */
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}