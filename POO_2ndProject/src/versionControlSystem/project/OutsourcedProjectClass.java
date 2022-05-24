package versionControlSystem.project;

import versionControlSystem.user.User;

public class OutsourcedProjectClass extends AbstractProjectClass implements OutsourcedProject {
    // Instance variables
    private String companyName;


    public OutsourcedProjectClass(User manager, String projectName, String[] keywords, String companyName) {
        super(manager, projectName, keywords);
        this.companyName = companyName;
    }
    @Override
    public String getCompanyName() {
        return companyName;
    }
}
