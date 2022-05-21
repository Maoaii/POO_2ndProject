package versionControlSystem;

import versionControlSystem.user.User;

import java.util.Iterator;

public interface Workaholics {

    /**
     * @return an <code>User Iterator</code> that iterates through the top three <code>Workaholics</code>
     */
    Iterator<User> getWorkaholics();
}
