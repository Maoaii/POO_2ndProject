package versionControlSystem;

import versionControlSystem.user.User;

import java.util.Iterator;

public interface Commonality {

    /**
     * @return an <code>User Iterator</code> that iterates through the two common <code>User</code>s
     */
    Iterator<User> getCommonUsers();
}
