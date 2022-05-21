package versionControlSystem.user;

import versionControlSystem.project.Project;

import java.util.Iterator;

public interface ProjectManager {

    /**
     * Adds a new <code>Project</code> for this <code>Manager</code>
     *
     * @param project - project to add
     */
    void addProject(Project project);

    /**
     * Adds a new <code>User</code> under the charge of this <code>ProjectManager</code>
     *
     * @param developer - <code>User</code> to add
     */
    void addDeveloper(User developer);

    /**
     * @return the number of <code>Developer</code>s this <code>ProjectManager</code> is manager of
     */
    int getNumDevelopers();

    /**
     * @return the number of <code>Project</code>s this <code>ProjectManager</code> participates in, as a
     *         <code>ProjectManager</code>
     */
    int getNumProjectsAsManagers();

    /**
     * @return a <code>Developer Iterator</code> that iterates through all of the <code>Developer</code>s this
     *         <code>ProjectManager</code> manages, by alphabetic order.
     */
    Iterator<User> getDevelopers();
}
