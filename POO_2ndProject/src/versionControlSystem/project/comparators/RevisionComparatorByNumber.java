package versionControlSystem.project.comparators;

import versionControlSystem.project.Revision;

import java.util.Comparator;

public class RevisionComparatorByNumber implements Comparator<Revision> {
    @Override
    public int compare(Revision o1, Revision o2) {
        return o1.getRevisionNumber() - o2.getRevisionNumber();
    }
}
