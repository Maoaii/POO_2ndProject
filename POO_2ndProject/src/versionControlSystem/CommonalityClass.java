package versionControlSystem;

import versionControlSystem.user.User;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class CommonalityClass implements Commonality {
    // Instance variables
    private final Set<User> commonUsers;
    private int numCommonProjects;

    public CommonalityClass() {
        commonUsers = new TreeSet<>();
        numCommonProjects = 0;
    }

    @Override
    public void addCommonUsers(User user1, User user2, int numCommonProjects) {
        commonUsers.add(user1);
        commonUsers.add(user2);
        this.numCommonProjects = numCommonProjects;
    }

    @Override
    public Iterator<User> getCommonUsers() {
        return commonUsers.iterator();
    }

    @Override
    public int getNumCommonProjects() {
        return numCommonProjects;
    }
}
