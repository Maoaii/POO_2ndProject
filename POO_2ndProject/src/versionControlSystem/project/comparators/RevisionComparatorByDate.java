package versionControlSystem.project.comparators;

import java.util.Comparator;

import versionControlSystem.project.Revision;

public class RevisionComparatorByDate implements Comparator<Revision>{

	@Override
	public int compare(Revision o1, Revision o2) {
		return o1.getRevisionDate().compareTo(o2.getRevisionDate());
	}

}
