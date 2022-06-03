package versionControlSystem.user.comparators;

import versionControlSystem.project.Revision;

import java.util.Comparator;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>Revision</code> Comparator by <code>revisionDate</code>, <code>revisionNumber</code> and <code>projectName</code>
 */
public class RevisionComparatorByDateNumberProjectName implements Comparator<Revision> {
    @Override
    public int compare(Revision o1, Revision o2) {
        int cmp = o2.getRevisionDate().compareTo(o1.getRevisionDate());

        if (cmp != 0)
            return cmp;
        else {
            cmp = o2.getRevisionNumber() - o1.getRevisionNumber();

            if (cmp != 0)
                return cmp;
            else
                return o1.getProjectName().compareTo(o2.getProjectName());
        }
    }
}
