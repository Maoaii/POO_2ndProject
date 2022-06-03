package versionControlSystem.comparators;

import versionControlSystem.user.ProjectManager;
import versionControlSystem.user.User;

import java.util.Comparator;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>Workaholic</code> Comparator. Compares number of revisions made, number of projects assigned to,
 * date of last revision and, lastly, by name
 */
public class WorkaholicComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        // Number of revisions compare
        int cmp = o2.getNumRevisions() - o1.getNumRevisions();
        if (cmp != 0)
            return cmp;
        else {
            // Number of projects compare
            int o1Projects = o1.getNumProjectsAsMember();
            if (o1 instanceof ProjectManager)
                o1Projects += ((ProjectManager) o1).getNumProjectsAsManagers();

            int o2Projects = o2.getNumProjectsAsMember();
            if (o2 instanceof ProjectManager)
                o2Projects += ((ProjectManager) o2).getNumProjectsAsManagers();

            cmp = o2Projects - o1Projects;
            if (cmp != 0)
                return cmp;
            else {
                // Date of last revision compare
                cmp = o2.getDateOfLastRevision().compareTo(o1.getDateOfLastRevision());
                if (cmp != 0)
                    return cmp;
                else
                    // Username compare
                    return o1.getUsername().compareTo(o2.getUsername());
            }
        }


    }
}
