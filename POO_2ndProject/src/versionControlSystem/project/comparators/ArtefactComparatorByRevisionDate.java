package versionControlSystem.project.comparators;

import versionControlSystem.project.Artefact;

import java.util.Comparator;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>Artefact</code> Comparator by date of last revision and <code>artefactName</code>
 */
public class ArtefactComparatorByRevisionDate implements Comparator<Artefact> {
    @Override
    public int compare(Artefact o1, Artefact o2) {
        int cmp = o2.getLastRevisionDate().compareTo(o1.getLastRevisionDate());
        if (cmp != 0)
            return cmp;
        else
            return o1.getArtefactName().compareTo(o2.getArtefactName());
    }
}
