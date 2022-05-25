package versionControlSystem.project;

import java.time.LocalDate;

public class RevisionClass implements Revision {
    // Instance variables
    private int revisionNumber;
    private String authorUsername;
    private LocalDate revisionDate;
    private String revisionComment;

    /**
     * Revision constructor
     *
     * @param revisionNumber - this <code>Revision</code>s <code>revisionNumber</code>
     * @param authorUsername - this <code>Revision</code>s <code>authorUsername</code>
     * @param revisionDate - this <code>Revision</code>s <code>revisionDate</code>
     * @param revisionComment - this <code>Revision</code>s <code>revisionComment</code>
     */
    public RevisionClass(int revisionNumber, String authorUsername, LocalDate revisionDate, String revisionComment) {
        this.revisionNumber = revisionNumber;
        this.authorUsername = authorUsername;
        this.revisionDate = revisionDate;
        this.revisionComment = revisionComment;
    }


    @Override
    public int getRevisionNumber() {
        return revisionNumber;
    }

    @Override
    public String getAuthorUsername() {
        return authorUsername;
    }

    @Override
    public LocalDate getRevisionDate() {
        return revisionDate;
    }

    @Override
    public String getRevisionComment() {
        return revisionComment;
    }
}
