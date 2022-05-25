package versionControlSystem.project;

import versionControlSystem.project.comparators.RevisionComparatorByNumber;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ArtefactClass implements Artefact {
    // Instance variables
    private String artefactName;
    private LocalDate artefactDate;
    private int confidentialityLevel;
    private String description;
    private Set<Revision> revisionsByNumber; // Sorted revisions by their number. Revision numbers start at 1
    private Revision lastRevision; // Last revision done

    /**
     * Artefact Constructor
     *
     * @param authorUsername - this <code>Artefact</code>s author
     * @param artefactName - this <code>Artefact</code>s <code>artefactName</code>
     * @param artefactDate - this <code>Artefact</code>s <code>artefactDate</code>
     * @param confidentialityLevel - this <code>Artefact</code>s <code>confidentialityLevel</code>
     * @param description - this <code>Artefact</code>s <code>description</code>
     */
    public ArtefactClass(String authorUsername, String artefactName, LocalDate artefactDate,
                         int confidentialityLevel, String description) {
        this.revisionsByNumber = new TreeSet<>(new RevisionComparatorByNumber());
        this.artefactName = artefactName;
        this.artefactDate = artefactDate;
        this.confidentialityLevel = confidentialityLevel;
        this.description = description;

        this.lastRevision = new RevisionClass(revisionsByNumber.size() + 1, authorUsername, artefactDate, description);
        revisionsByNumber.add(lastRevision);
    }

    @Override
    public void reviewArtefact(Revision revision) {
        revisionsByNumber.add(revision);
        lastRevision = revision;
    }

    @Override
    public String getArtefactName() {
        return artefactName;
    }

    @Override
    public int getArtefactConfidentialityLevel() {
        return confidentialityLevel;
    }

    // TODO: hasn't been used yet. Maybe delete later
    @Override
    public String getArtefactDescription() {
        return description;
    }

    @Override
    public int getNumRevisions() {
        return revisionsByNumber.size();
    }

    @Override
    public LocalDate getLastRevisionDate() {
        return lastRevision.getRevisionDate();
    }

    @Override
    public Iterator<Revision> getArtefactRevisions() {
        return revisionsByNumber.iterator();
    }
}
