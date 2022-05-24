package versionControlSystem.project;

import java.time.LocalDate;

public interface Revision {

    /**
     * @return this <code>Revision</code>s <code>number</code>
     */
    int getRevisionNumber();

    /**
     * @return this <code>Revision</code>s <code>User</code> <code>username</code>
     */
    String getAuthorUsername();

    /**
     * @return this <code>Revision</code>s <code>date</code>
     */
    LocalDate getRevisionDate();

    /**
     * @return this <code>Revision</code>s <code>comment</code>
     */
    String getRevisionComment();
}
