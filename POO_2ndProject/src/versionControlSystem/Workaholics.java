package versionControlSystem;

import versionControlSystem.user.User;

import java.util.Iterator;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>Workaholics</code> Interface. Resembles the top three workaholics in the system.
 * A workaholic is determined by the number of <code>Revision</code>s made, <code>Project</code>s assigned in,
 * date of the last revision and by name.
 */
public interface Workaholics {

    /**
     * @return an <code>User Iterator</code> that iterates through the top three <code>Workaholics</code>
     */
    Iterator<User> getWorkaholics();

    /**
     * Adds a new <code>User</code> to the <code>workaholics</code>
     *
     * @param workaholic - <code>User</code> to add
     */
    void addWorkaholic(User workaholic);
}
