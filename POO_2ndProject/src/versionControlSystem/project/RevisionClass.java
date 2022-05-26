package versionControlSystem.project;

import java.time.LocalDate;

public class RevisionClass implements Revision {
    // Instance variables
    private String projectName;
    private String artefactName;
    private int revisionNumber;
    private String authorUsername;
    private LocalDate revisionDate;
    private String revisionComment;

    /**
     * Revision constructor
     *
     * @param projectName - this <code>Revision</code>s <code>Project</code>s <code>projectName</code>
     * @param artefactName - this <code>Revision</code>s <code>Artefact</code>s <code>artefactName</code>
     * @param revisionNumber - this <code>Revision</code>s <code>revisionNumber</code>
     * @param authorUsername - this <code>Revision</code>s <code>authorUsername</code>
     * @param revisionDate - this <code>Revision</code>s <code>revisionDate</code>
     * @param revisionComment - this <code>Revision</code>s <code>revisionComment</code>
     */
    public RevisionClass(String projectName, String artefactName, int revisionNumber,
                         String authorUsername, LocalDate revisionDate, String revisionComment) {
        this.projectName = projectName;
        this.artefactName = artefactName;
        this.revisionNumber = revisionNumber;
        this.authorUsername = authorUsername;
        this.revisionDate = revisionDate;
        this.revisionComment = revisionComment;
    }

    @Override
    public String getProjectName() {
        return projectName;
    }

    @Override
    public String getArtefactName() {
        return artefactName;
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
