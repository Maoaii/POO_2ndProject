package versionControlSystem.project;

import java.util.Iterator;

public interface Artefact {

    /**
     * @return this <code>Artefact</code>s <code>confidentialityLevel</code>
     */
    int getArtefactConfidentialityLevel();

    /**
     * @return this <code>Artefact</code>s <code>description</code>
     */
    String getArtefactDescription();

    /**
     * @return the number of <code>Revision</code>s this <code>Artefact</code> has
     */
    int getNumRevisions();

    /**
     * @return a <code>Revision Iterator</code> that iterates through all of this
     *         <code>Artefact</code>s <code>Revision</code>s, sorted by descending order of <code>Date</code>.
     */
    Iterator<Revision> getArtefactRevisions();
}
