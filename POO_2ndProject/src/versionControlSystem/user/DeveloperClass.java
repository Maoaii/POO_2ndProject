package versionControlSystem.user;

public class DeveloperClass extends AbstractUserClass implements Developer {
    // Instance variables
    private String managerUsername;
    public DeveloperClass(String managerUsername, String username, int clearanceLevel) {
        super(username, clearanceLevel);
        this.managerUsername = managerUsername;
    }

    @Override
    public String getManager() {
        return managerUsername;
    }
}
