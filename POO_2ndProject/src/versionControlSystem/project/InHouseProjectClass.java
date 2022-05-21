package versionControlSystem.project;

import versionControlSystem.project.comparators.ArtefactComparatorByRevisionDate;
import versionControlSystem.user.User;

import java.util.*;

public class InHouseProjectClass extends AbstractProjectClass implements InHouseProject {
    // Instance variables
    private int confidentialityLevel;
    private List<User> membersByInsertion; // Stores members by insertion order
    private Set<Artefact> artefactsByRevisionDateName; // Stores artefacts by revision date and name


    public InHouseProjectClass(String managerUsername, String projectName, String[] keywords, int confidentialityLevel) {
        super(managerUsername, projectName, keywords);
        this.confidentialityLevel = confidentialityLevel;
        membersByInsertion = new LinkedList<>();
        artefactsByRevisionDateName = new TreeSet<>(new ArtefactComparatorByRevisionDate());
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
        return artefactsByRevisionDateName.size();
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
    public Iterator<User> getProjectMembers() {
        return membersByInsertion.iterator();
    }

    @Override
    public Iterator<Artefact> getProjectArtefacts() {
        return artefactsByRevisionDateName.iterator();
    }
}
