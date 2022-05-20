package versionControlSystem.classesInterfaces;

import java.util.Date;
import java.util.Iterator;

public interface User {

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
     * @return the <code>jobType</code> of this <code>User</code>
     */
    String getJobType();

    /**
     * @return a <code>Revision Iterator</code> that iterates through all <code>Revision</code>s this
     *         <code>User</code> has made as a team member, sorted by:
     *         <code>Date</code>; <code>revisionNumber</code>; <code>projectName</code>;
     */
    Iterator<Revision> getUserRevisions();

    /**
     * @return the <code>Date</code> of the last <code>Revision</code> this <code>User</code> made
     */
    Date getDateOfLastRevision();
}
