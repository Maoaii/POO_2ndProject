package versionControlSystem.project.comparators;

import versionControlSystem.project.Revision;

import java.util.Comparator;

public class RevisionComparatorByNumber implements Comparator<Revision> {
    @Override
    public int compare(Revision o1, Revision o2) {
        // Sort o2 before o1 if o2's revision number is bigger
        return o2.getRevisionNumber() - o1.getRevisionNumber();
    }
}
