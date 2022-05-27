package versionControlSystem.user;

import versionControlSystem.project.Project;
import versionControlSystem.project.Revision;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public interface User extends Comparable<User> {

    /**
     * @return this <code>User</code>s <code>username</code>
     */
    String getUsername();

    /**
     * @return the number of <code>Project</code>s this <code>User</code> participates in, as a member
     */
    int getNumProjectsAsMember();

    /**
     * @return the <code>clearanceLevel</code> of this <code>User</code>
     */
    int getClearanceLevel();

    /**
     * Checks if this <code>User</code> is a member of <code>Project</code> with <code>projectName</code>
     *
     * @param projectName - <code>projectName</code> to check
     * @return true if this <code>User</code> is a member
     */
    boolean isMember(String projectName);

    /**
     * @return a <code>Revision Iterator</code> that iterates through all <code>Revision</code>s this
     *         <code>User</code> has made as a team member, sorted by:
     *         <code>date</code>; <code>revisionNumber</code>; <code>projectName</code>;
     */
    Iterator<Revision> getUserRevisions();

    /**
     * @return the <code>date</code> of the last <code>Revision</code> this <code>User</code> made
     */
    LocalDate getDateOfLastRevision();

    /**
     * Adds a new <code>Project</code> to this <code>User</code>
     *
     * @param project - <code>Project</code> to add
     */
    void addProject(Project project);

    /**
     * Adds a new <code>Revision</code> to this <code>User</code>
     *
     * @param revision - <code>Revision</code> to add
     */
    void addRevision(Revision revision);

    /**
     * @return the number of <code>Revision</code>s this <code>User</code> made
     */
    int getNumRevisions();

    int compareTo(User other);
}
