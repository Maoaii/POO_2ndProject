package versionControlSystem.project;

import versionControlSystem.project.comparators.RevisionComparatorByNumber;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class ArtefactClass implements Artefact {
    // Instance variables
    private String artefactName;
    private int confidentialityLevel;
    private String description;
    private Set<Revision> revisionsByNumber; // Sorted revisions by their number

    /**
     * Artefact constructor
     */
    public ArtefactClass(String artefactName, int confidentialityLevel, String description) {
        revisionsByNumber = new TreeSet<>(new RevisionComparatorByNumber());
        this.artefactName = artefactName;
        this.confidentialityLevel = confidentialityLevel;
        this.description = description;
    }

    @Override
    public void reviewArtefact(Revision revision) {
        revisionsByNumber.add(revision);
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
        return revisionsByNumber.size();
    }

    @Override
    public LocalDate getLastRevisionDate() {
        return null;
    }

    @Override
    public Iterator<Revision> getArtefactRevisions() {
        return revisionsByNumber.iterator();
    }
}
