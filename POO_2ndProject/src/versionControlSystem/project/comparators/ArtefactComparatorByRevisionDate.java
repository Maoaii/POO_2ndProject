package versionControlSystem.project.comparators;

import versionControlSystem.project.Artefact;

import java.util.Comparator;

public class ArtefactComparatorByRevisionDate implements Comparator<Artefact> {
    // Constants
    private static final int IS_BEFORE = 1;
    private static final int IS_AFTER = -1;
    @Override
    public int compare(Artefact o1, Artefact o2) {
        int cmp = o1.getLastRevisionDate().compareTo(o2.getLastRevisionDate());
        if (cmp > 0)
            return IS_AFTER;
        else if (cmp < 0)
            return IS_BEFORE;
        else
            return o1.getArtefactName().compareTo(o2.getArtefactName());
    }
}
