package versionControlSystem;

import versionControlSystem.user.User;

import java.util.*;

public class CommonalityClass implements Commonality {
    // Constants
    private static final int MAX_COMMON_USERS = 2;

    // Instance variables
    private Set<User> commonUsers;
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
