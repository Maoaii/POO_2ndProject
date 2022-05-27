package versionControlSystem;

import versionControlSystem.user.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WorkaholicsClass implements Workaholics {
    // Constants
    private static final int MAX_WORKAHOLICS = 3;

    // Instance variables
    private List<User> workaholics;

    /**
     * Workaholics constructor
     */
    public WorkaholicsClass() {
        this.workaholics = new ArrayList<>(MAX_WORKAHOLICS);
    }

    @Override
    public void addWorkaholic(User workaholic) {
        workaholics.add(workaholic);
    }

    @Override
    public Iterator<User> getWorkaholics() {
        return workaholics.iterator();
    }
}
