package versionControlSystem.user;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>Developer</code> Class. A subclass of <code>User</code>. Has a manager username.
 */
public class DeveloperClass extends AbstractUserClass implements Developer {
    // Instance variables
    private final String managerUsername;

    /**
     * Developer constructor
     *
     * @param managerUsername - this <code>Developer</code>s <code>ProjectManager username</code>
     * @param username        - this <code>Developer</code>s <code>username</code>
     * @param clearanceLevel  - this <code>Developer</code>s <code>clearanceLevel</code>
     */
    public DeveloperClass(String managerUsername, String username, int clearanceLevel) {
        super(username, clearanceLevel);
        this.managerUsername = managerUsername;
    }

    @Override
    public String getManager() {
        return managerUsername;
    }


}
