package versionControlSystem.project;

import versionControlSystem.user.User;

import java.util.Iterator;

public interface InHouseProject {

    /**
     * @return this <code>InHouseProject</code>s <code>confidentialityLevel</code>
     */
    int getConfidentialityLevel();

    /**
     * @return this <code>InHouseProject</code>s number of <code>Developer</code>s
     */
    int getNumMembers();

    /**
     * @return this <code>InHouseProject</code>s number of <code>Artefact</code>s
     */
    int getNumArtefacts();

    /**
     * @return this <code>InHouseProject</code>s number of <code>Revision</code>s
     */
    int getNumRevisions();
    
    /**
     * @return this <code>InHouseProject</code>s last <code>Revision</code>'s
     */
    Revision getLastRevision();

    /**
     * @return the number of <code>Revision</code>s the <code>Artefact</code> with <code>artefactName</code> has
     */
    int getNumArtefactRevisions(String artefactName);

    /**
     * @param keyword - keyword to check
     * @return true if this <code>InHouseProject</code> has <code>keyword</code> as a <code>keyword</code>
     */
    boolean hasKeyword(String keyword);

    /**
     * Adds a new <code>User</code> to this <code>InHouseProject</code>
     *
     * @param user - <code>User</code> to add
     */
    void addMember(User user);

    /**
     * @param artefactName - <code>artefactName</code> to check
     * @return true if this <code>InHouseProject</code> has an <code>Artefact</code> with <code>artefactName</code>
     */
    boolean hasArtefact(String artefactName);

    /**
     * Adds a new <code>Artefact</code> to this <code>InHouseProject</code>
     *
     * @param artefact - <code>Artefact</code> to add
     */
    void addArtefact(Artefact artefact);

    /**
     * Reviews an <code>Artefact</code> from this <code>InHouseProject</code>
     *
     * @param artefactName - reviewed <code>Artefact</code>
     * @param revision - <code>Revision</code> to be made
     */
    void reviewArtefact(String artefactName, Revision revision);

    /**
     * @return an <code>User Iterator</code> that iterates through all of the <code>Project</code>s members,
     *         by insertion order
     */
    Iterator<User> getProjectMembers();

    /**
     * @return an <code>Artefact Iterator</code> that iterates through all of the <code>Project</code>s members,
     *         by: <code>Date</code> of the last revision number; <code>artefactName</code>
     */
    Iterator<Artefact> getProjectArtefacts();
}
