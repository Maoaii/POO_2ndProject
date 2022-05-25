package versionControlSystem.user;

public class DeveloperClass extends AbstractUserClass implements Developer {
    // Instance variables
    private String managerUsername;

    /**
     * Developer constructor
     *
     * @param managerUsername - this <code>Developer</code>s <code>ProjectManager username</code>
     * @param username - this <code>Developer</code>s <code>username</code>
     * @param clearanceLevel - this <code>Developer</code>s <code>clearanceLevel</code>
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
