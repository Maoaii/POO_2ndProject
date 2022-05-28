package versionControlSystem;

import versionControlSystem.user.User;

import java.util.Iterator;

public interface Commonality {

    /**
     * Adds the two <code>User</code>s
     *
     * @param user1 - first <code>User</code> to add
     * @param user2 - second <code>User</code> to add
     */
    void addCommonUsers(User user1, User user2, int numCommonProjects);

    /**
     * @return an <code>User Iterator</code> that iterates through the two common <code>User</code>s
     */
    Iterator<User> getCommonUsers();

    /**
     * @return the number of common <code>Project</code>s the two <code>User</code>s have
     */
    int getNumCommonProjects();
}
