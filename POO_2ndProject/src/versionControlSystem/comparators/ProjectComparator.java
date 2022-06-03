package versionControlSystem.comparators;

import versionControlSystem.project.InHouseProject;
import versionControlSystem.project.Project;

import java.util.Comparator;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>Project</code> Comparator. Compares date of last revision, then the number of revisions and, lastly, the project name
 */
public class ProjectComparator implements Comparator<Project> {
    // Constants
    private static final int IS_BEFORE = -1;
    private static final int IS_AFTER = 1;

    @Override
    public int compare(Project o1, Project o2) {
        if (o1 instanceof InHouseProject) {
            if (o2 instanceof InHouseProject) {
                // comparing the dates of last update
                int cmp = ((InHouseProject) o2).getLastUpdateDate().compareTo(((InHouseProject) o1).getLastUpdateDate());
                if (cmp != 0) {
                    return cmp;
                } else {
                    // Comparing number of revisions
                    cmp = ((InHouseProject) o2).getNumRevisions() - ((InHouseProject) o1).getNumRevisions();
                    if (cmp != 0)
                        return cmp;
                }
            } else
                return IS_BEFORE;
        } else if (o2 instanceof InHouseProject)
            return IS_AFTER;
        
        // Compare names
        return o1.getProjectName().compareTo(o2.getProjectName());
    }
}


