package versionControlSystem.project;

import java.time.LocalDate;

public class RevisionClass implements Revision {
    // Instance variables
    private int revisionNumber;
    private String authorUsername;
    private LocalDate revisionDate;
    private String revisionComment;

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
