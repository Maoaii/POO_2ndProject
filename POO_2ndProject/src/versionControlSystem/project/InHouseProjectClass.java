package versionControlSystem.project;

import versionControlSystem.project.comparators.ArtefactComparatorByRevisionDate;
import versionControlSystem.user.User;

import java.util.*;

public class InHouseProjectClass extends AbstractProjectClass implements InHouseProject {
    // Instance variables
    private int confidentialityLevel;
    private List<User> membersByInsertion; // Stores members by insertion order
    private Map<String, Artefact> artefacts; // Stores artefacts for easy access
    private List<Artefact> artefactsByInsertion; // Stores artefacts by insertion


    public InHouseProjectClass(User manager, String projectName, String[] keywords, int confidentialityLevel) {
        super(manager, projectName, keywords);
        this.confidentialityLevel = confidentialityLevel;
        membersByInsertion = new LinkedList<>();
        artefacts = new HashMap<>();
        artefactsByInsertion = new LinkedList<>();
    }
    @Override
    public int getConfidentialityLevel() {
        return confidentialityLevel;
    }

    @Override
    public int getNumMember() {
        return membersByInsertion.size();
    }

    @Override
    public int getNumArtefacts() {
        return artefactsByInsertion.size();
    }

    @Override
    public int getNumRevisions() {
        return 0;
    }

    @Override
    public boolean hasKeyword(String keyword) {
        return false;
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
    }

    @Override
    public Iterator<User> getProjectMembers() {
        return membersByInsertion.iterator();
    }

    @Override
    public Iterator<Artefact> getProjectArtefacts() {
        Set<Artefact> sortedArtefacts = new TreeSet<>(new ArtefactComparatorByRevisionDate());
        Iterator<Artefact> artefactIterator = artefactsByInsertion.iterator();

        while (artefactIterator.hasNext()) {
            sortedArtefacts.add(artefactIterator.next());
        }

        return sortedArtefacts.iterator();
    }
}
