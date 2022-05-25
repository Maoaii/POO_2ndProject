package versionControlSystem.project;

import java.time.LocalDate;

public interface Revision {

    /**
     * @return this <code>Revision</code>s <code>revisionNumber</code>
     */
    int getRevisionNumber();

    /**
     * @return this <code>Revision</code>s <code>authorUsername</code>
     */
    String getAuthorUsername();

    /**
     * @return this <code>Revision</code>s <code>revisionDate</code>
     */
    LocalDate getRevisionDate();

    /**
     * @return this <code>Revision</code>s <code>revisionComment</code>
     */
    String getRevisionComment();
}
