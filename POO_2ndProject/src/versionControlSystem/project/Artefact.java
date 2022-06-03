package versionControlSystem.project;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * @author Lucas Girotto / Pedro Afonso
 * <p>
 * Artefact Interface. Holds all the methods a <code>Artefact</code> has access to
 */
public interface Artefact {

    /**
     * Reviews this <code>Artefact</code>
     *
     * @param revision - <code>Artefact</code>s revision
     */
    void reviewArtefact(Revision revision);

    /**
     * @return this <code>Artefact</code>'s <code>artefactName</code>
     */
    String getArtefactName();

    /**
     * @return this <code>Artefact</code>s <code>confidentialityLevel</code>
     */
    int getArtefactConfidentialityLevel();

    /**
     * @return the number of <code>Revision</code>s this <code>Artefact</code> has
     */
    int getNumRevisions();

    /**
     * @return the <code>LocalDate</code> of the last <code>Revision</code>
     */
    LocalDate getLastRevisionDate();

    /**
     * @return a <code>Revision Iterator</code> that iterates through all of this
     * <code>Artefact</code>s <code>Revision</code>s, sorted by descending order of <code>Date</code>.
     */
    Iterator<Revision> getArtefactRevisions();
}
