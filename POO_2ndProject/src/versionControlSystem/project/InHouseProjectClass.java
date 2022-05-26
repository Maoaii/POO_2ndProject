package versionControlSystem.project;

import versionControlSystem.project.comparators.ArtefactComparatorByRevisionDate;
import versionControlSystem.project.comparators.RevisionComparatorByDate;
import versionControlSystem.user.User;

import java.util.*;

public class InHouseProjectClass extends AbstractProjectClass implements InHouseProject {
    // Instance variables
    private int confidentialityLevel;
    private List<User> membersByInsertion; // Stores members by insertion order
    private Map<String, Artefact> artefacts; // Stores artefacts for easy access. artefactName -> Artefact
    private List<Artefact> artefactsByInsertion; // Stores artefacts by insertion
    private List<Revision> revisions; //Stores revisions

    /**
     * InHouse Project constructor
     *
     * @param manager - this <code>Project</code>s <code>ProjectManager</code>
     * @param projectName - this <code>Project</code>s <code>projectName</code>
     * @param keywords - this <code>Project</code>s <code>keywords</code>
     * @param confidentialityLevel - this <code>Project</code>s <code>confidentialityLevel</code>
     */
    public InHouseProjectClass(User manager, String projectName, String[] keywords, int confidentialityLevel) {
        super(manager, projectName, keywords);

        this.confidentialityLevel = confidentialityLevel;
        membersByInsertion = new LinkedList<>();
        artefacts = new HashMap<>();
        artefactsByInsertion = new LinkedList<>();
        revisions = new ArrayList<>();
    }
    @Override
    public int getConfidentialityLevel() {
        return confidentialityLevel;
    }

    @Override
    public int getNumMembers() {
        return membersByInsertion.size();
    }

    @Override
    public int getNumArtefacts() {
        return artefactsByInsertion.size();
    }

    @Override
    public int getNumRevisions() {
        return revisions.size();
    }
    
    @Override
    public Revision getLastRevision() {
    	return revisions.get(revisions.size());
    }

    @Override
    public int getNumArtefactRevisions(String artefactName) {
        return artefacts.get(artefactName).getNumRevisions();
    }

    @Override
    public void addMember(User user) {
        membersByInsertion.add(user);
    }

    @Override
    public boolean hasArtefact(String artefactName) {
    	return artefacts.containsKey(artefactName);
    }

    @Override
    public void addArtefact(Artefact artefact) {
        artefactsByInsertion.add(artefact);
        artefacts.put(artefact.getArtefactName(), artefact);
    }

    @Override
    public void reviewArtefact(String artefactName, Revision revision) {
        artefacts.get(artefactName).reviewArtefact(revision);
        revisions.add(revision);
    }

    @Override
    public Iterator<User> getProjectMembers() {
        return membersByInsertion.iterator();
    }

    @Override
    public Iterator<Artefact> getProjectArtefacts() {
        // Sort the artefacts by last revision's date
        Set<Artefact> sortedArtefacts = new TreeSet<>(new ArtefactComparatorByRevisionDate());
        Iterator<Artefact> artefactIterator = artefactsByInsertion.iterator();

        while (artefactIterator.hasNext()) {
            sortedArtefacts.add(artefactIterator.next());
        }

        return sortedArtefacts.iterator();
    }
}
