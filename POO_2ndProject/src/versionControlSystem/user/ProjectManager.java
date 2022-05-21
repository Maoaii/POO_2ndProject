package versionControlSystem.user;

import java.util.Iterator;

public interface ProjectManager {

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
    Iterator<Developer> getDevelopers();
}
