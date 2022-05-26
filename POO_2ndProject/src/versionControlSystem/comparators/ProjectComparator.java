package versionControlSystem.comparators;

import java.util.Comparator;

import versionControlSystem.project.InHouseProject;
import versionControlSystem.project.Project;

public class ProjectComparator implements Comparator<Project> {

	@Override
	public int compare(Project o1, Project o2) {
		if(o1 instanceof InHouseProject) {
			if(o2 instanceof InHouseProject) {
				// comparing the dates
				if(((InHouseProject) o1).getLastRevision().getRevisionDate().compareTo(((InHouseProject) o1).getLastRevision().getRevisionDate()) != 0) {
					return ((InHouseProject) o1).getLastRevision().getRevisionDate().compareTo(((InHouseProject) o1).getLastRevision().getRevisionDate());
				}
				else if(((InHouseProject) o1).getNumRevisions() != ((InHouseProject) o2).getNumRevisions()) {
					return ((InHouseProject) o2).getNumRevisions() - ((InHouseProject) o1).getNumRevisions();
				}
				else {
					return o1.getProjectName().compareTo(o2.getProjectName());
				}
			}
			else {
				return -1;
			}
		}
		else {
			if(o2 instanceof InHouseProject) {
				return 1;
			}
			else {
				return o1.getProjectName().compareTo(o2.getProjectName());
			}
		}
	}

}
