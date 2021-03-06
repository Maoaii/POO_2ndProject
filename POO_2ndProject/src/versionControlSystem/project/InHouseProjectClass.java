package versionControlSystem.project;

import versionControlSystem.project.comparators.ArtefactComparatorByRevisionDate;
import versionControlSystem.user.User;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>InHouseProject</code> Class. A subclass of <code>Project</code>.
 * Has a confidentiality level, a collection of <code>User</code>s, store by insertion order,
 * and a map and collection of <code>Artefact</code>s, sorted by insertion.
 */
public class InHouseProjectClass extends AbstractProjectClass implements InHouseProject {
    // Instance variables
    private final int confidentialityLevel;
    private final List<User> membersByInsertion; // Stores members by insertion order
    private final Map<String, Artefact> artefacts; // Stores artefacts for easy access. artefactName -> Artefact
    private final List<Artefact> artefactsByInsertion; // Stores artefacts by insertion
    private int numRevisions;

    /**
     * InHouse Project constructor
     *
     * @param manager              - this <code>Project</code>s <code>ProjectManager</code>
     * @param projectName          - this <code>Project</code>s <code>projectName</code>
     * @param keywords             - this <code>Project</code>s <code>keywords</code>
     * @param confidentialityLevel - this <code>Project</code>s <code>confidentialityLevel</code>
     */
    public InHouseProjectClass(User manager, String projectName, String[] keywords, int confidentialityLevel) {
        super(manager, projectName, keywords);

        this.confidentialityLevel = confidentialityLevel;
        membersByInsertion = new LinkedList<>();
        artefacts = new HashMap<>();
        artefactsByInsertion = new LinkedList<>();
        numRevisions = 0;
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
        return numRevisions;
    }

    @Override
    public LocalDate getLastUpdateDate() {
        LocalDate date = artefactsByInsertion.get(0).getLastRevisionDate();
        for (int i = 0; i < artefactsByInsertion.size(); i++) {
            if (date.compareTo(artefactsByInsertion.get(i).getLastRevisionDate()) < 0) {
                date = artefactsByInsertion.get(i).getLastRevisionDate();
            }
        }
        return date;
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
        numRevisions++;
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
