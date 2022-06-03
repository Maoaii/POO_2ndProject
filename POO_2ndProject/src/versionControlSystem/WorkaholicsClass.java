package versionControlSystem;

import versionControlSystem.comparators.WorkaholicComparator;
import versionControlSystem.user.User;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>Workaholics</code> Class. Has a collection of, at most, three <code>User</code>s, stored
 * by number of <code>Revision</code>s made, number of <code>Project</code>s assigned to,
 * date of the last <code>Revision</code> and by name.
 */
public class WorkaholicsClass implements Workaholics {
    // Constants
    private static final int MAX_WORKAHOLICS = 3;

    // Instance variables
    private Set<User> workaholics;

    /**
     * Workaholics constructor
     */
    public WorkaholicsClass() {
        this.workaholics = new TreeSet<>(new WorkaholicComparator());
    }

    @Override
    public void addWorkaholic(User workaholic) {
        workaholics.add(workaholic);
        if (workaholics.size() > MAX_WORKAHOLICS) {
            workaholics.remove(((TreeSet<User>) workaholics).last());
        }
    }

    @Override
    public Iterator<User> getWorkaholics() {
        return workaholics.iterator();
    }
}
