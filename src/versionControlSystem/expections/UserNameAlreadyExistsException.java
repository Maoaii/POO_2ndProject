package versionControlSystem.expections;

public class UserNameAlreadyExistsException extends Exception {
    // Instance variables
    private String username;

    /**
     * Exception constructor
     *
     * @param username - <code>username</code> that caused the exception
     */
    public UserNameAlreadyExistsException(String username) {
        this.username = username;
    }

    /**
     * @return the <code>username</code> that caused the exception
     */
    public String getErrorInfo() {
        return username;
    }
}
