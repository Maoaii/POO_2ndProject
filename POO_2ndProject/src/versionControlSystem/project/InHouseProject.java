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
    int getNumMember();

    /**
     * @return this <code>InHouseProject</code>s number of <code>Artefact</code>s
     */
    int getNumArtefacts();

    /**
     * @return this <code>InHouseProject</code>s number of <code>Revision</code>s
     */
    int getNumRevisions();

    /**
     * @param keyword - keyword to check
     * @return true if this <code>InHouseProject</code> has <code>keyword</code> as a <code>keyword</code>
     */
    boolean hasKeyword(String keyword);

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
