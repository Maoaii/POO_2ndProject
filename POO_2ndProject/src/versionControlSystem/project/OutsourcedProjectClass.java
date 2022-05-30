package versionControlSystem.project;

import versionControlSystem.user.User;

public class OutsourcedProjectClass extends AbstractProjectClass implements OutsourcedProject {
    // Instance variables
    private final String companyName;

    /**
     * Outsourced Project constructor
     *
     * @param manager     - this <code>Project</code>s <code>ProjectManager</code>
     * @param projectName - this <code>Project</code>s <code>projectName</code>
     * @param keywords    - this <code>Project</code>s <code>keywords</code>
     * @param companyName - this <code>Project</code>s <code>companyName</code>
     */
    public OutsourcedProjectClass(User manager, String projectName, String[] keywords, String companyName) {
        super(manager, projectName, keywords);
        this.companyName = companyName;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }
}
