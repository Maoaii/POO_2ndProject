package versionControlSystem.project.comparators;

import versionControlSystem.project.Revision;

import java.util.Comparator;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>Revision</code> Comparator by <code>revisionNumer</code>
 */
public class RevisionComparatorByNumber implements Comparator<Revision> {
    @Override
    public int compare(Revision o1, Revision o2) {
        // Sort o2 before o1 if o2's revision number is bigger
        return o2.getRevisionNumber() - o1.getRevisionNumber();
    }
}
