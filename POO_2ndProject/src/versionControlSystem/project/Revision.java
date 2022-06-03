package versionControlSystem.project;

import java.time.LocalDate;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>Revision</code> Interface. Resembles a revision of the content of an <code>Artefact</code>
 * Holds all the functions a <code>Revision</code> has access to.
 */
public interface Revision {

    /**
     * @return this <code>Revision</code>s <code>Project</code>s <code>projectName</code>
     */
    String getProjectName();

    /**
     * @return this <code>Revision</code>s <code>Artefact</code>s <code>artefactName</code>
     */
    String getArtefactName();

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
