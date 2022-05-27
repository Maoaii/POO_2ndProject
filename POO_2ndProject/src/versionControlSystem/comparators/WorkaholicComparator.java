package versionControlSystem.comparators;

import versionControlSystem.user.ProjectManager;
import versionControlSystem.user.User;

import java.util.Comparator;

public class WorkaholicComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        // Number of revisions compare
        int cmp = o2.getNumRevisions() - o1.getNumRevisions();
        if (cmp != 0)
            return cmp;
        else {
            // Number of projects compare
            int a = o1.getNumProjectsAsMember();
            if (o1 instanceof ProjectManager)
                a += ((ProjectManager) o1).getNumProjectsAsManagers();

            int b = o2.getNumProjectsAsMember();
            if (o2 instanceof ProjectManager)
                b += ((ProjectManager) o2).getNumProjectsAsManagers();

            cmp = b - a;
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
