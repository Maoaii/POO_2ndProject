package versionControlSystem.user.comparators;

import versionControlSystem.project.Revision;

import java.util.Comparator;

public class RevisionComparatorByDateNumberProjectName implements Comparator<Revision> {
    @Override
    public int compare(Revision o1, Revision o2) {
        int cmp = o1.getRevisionDate().compareTo(o2.getRevisionDate());

        if (cmp != 0)
            return -cmp; // -cmp because ordered from newest to oldest
        else {
            cmp = o1.getRevisionNumber() - o2.getRevisionNumber();

            if (cmp != 0)
                return -cmp; // -cmp because ordered from last revision to first
            else
                return o1.getProjectName().compareTo(o2.getProjectName());
        }
    }
}
