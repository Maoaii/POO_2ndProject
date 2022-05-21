package versionControlSystem.project.comparators;

import versionControlSystem.project.Artefact;

import java.util.Comparator;

public class ArtefactComparatorByRevisionDate implements Comparator<Artefact> {
    @Override
    public int compare(Artefact o1, Artefact o2) {
        int cmp = o1.getLastRevisionDate().compareTo(o2.getLastRevisionDate());
        if (cmp > 0)
            return 1;
        else if (cmp < 0)
            return -1;
        else
            return o1.getArtefactName().compareTo(o2.getArtefactName());
    }
}
