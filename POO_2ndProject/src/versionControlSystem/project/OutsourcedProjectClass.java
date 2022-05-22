package versionControlSystem.project;

public class OutsourcedProjectClass extends AbstractProjectClass implements OutsourcedProject {
    // Instance variables
    private String companyName;


    public OutsourcedProjectClass(String managerUsername, String projectName, String[] keywords, String companyName) {
        super(managerUsername, projectName, keywords);
        this.companyName = companyName;
    }
    @Override
    public String getCompanyName() {
        return companyName;
    }
}
