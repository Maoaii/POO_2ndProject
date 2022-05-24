package versionControlSystem.project;

import versionControlSystem.project.comparators.RevisionComparatorByNumber;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class ArtefactClass implements Artefact {
    // Instance variables
    private String artefactName;
    private LocalDate artefactDate;
    private int confidentialityLevel;
    private String description;
    private Set<Revision> revisionsByNumber; // Sorted revisions by their number
    private Revision lastRevision; // Last revision did

    /**
     * Artefact constructor
     */
    public ArtefactClass(String artefactName, LocalDate artefactDate, int confidentialityLevel, String description) {
        this.revisionsByNumber = new TreeSet<>(new RevisionComparatorByNumber());
        this.artefactName = artefactName;
        this.artefactDate = artefactDate;
        this.confidentialityLevel = confidentialityLevel;
        this.description = description;

        this.lastRevision = new RevisionClass(revisionsByNumber.size(), "", artefactDate, description);
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

    @Override
    public String getArtefactDescription() {
        return description;
    }

    @Override
    public int getNumRevisions() {
        return revisionsByNumber.size() - 1; // The first revision (when artefact is added) doesn't count
    }

    @Override
    public LocalDate getLastRevisionDate() {
        return lastRevision.getRevisionDate();
    }

    @Override
    public Iterator<Revision> getArtefactRevisions() {
        return revisionsByNumber.iterator(); // We have to skip the first revision (it's the artefact add)
    }
}
