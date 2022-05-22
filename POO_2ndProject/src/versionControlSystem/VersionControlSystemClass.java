package versionControlSystem;

import versionControlSystem.comparators.ComparatorByName;
import versionControlSystem.project.Project;
import versionControlSystem.user.DeveloperClass;
import versionControlSystem.user.ProjectManager;
import versionControlSystem.user.ProjectManagerClass;
import versionControlSystem.user.User;
import versionControlSystem.exceptions.*;

import java.util.*;

public class VersionControlSystemClass implements VersionControlSystem {
    // Constants
    private static final String PROJECTMANAGER = "manager";
    private static final String DEVELOPER = "developer";

    // Instance variables
    private Map<String, User> users;
    private Set<User> usersByName; // Stores Users ordered by name
    private List<Project> projectsByInsertion; // Stores Project's by insertion order

    /**
     * Version Control System constructor
     */
    public VersionControlSystemClass() {
        users = new HashMap<>();
        usersByName = new TreeSet<>(new ComparatorByName());
        projectsByInsertion = new LinkedList<>();
    }


    @Override
    public void registerUser(String jobPosition, String username, String managerUsername, int clearanceLevel)
            throws UnknownJobPositionException, UserNameAlreadyExistsException, ManagerUsernameInvalidException {
        boolean isManager = jobPosition.equals(PROJECTMANAGER);
        boolean isDeveloper = jobPosition.equals(DEVELOPER);

        if (!isManager && !isDeveloper)
            throw new UnknownJobPositionException();
        if (users.containsKey(username))
            throw new UserNameAlreadyExistsException(username);


        User user;
        if (isManager) {
            user = new ProjectManagerClass(username, clearanceLevel);
        }
        else {
            User manager = users.get(managerUsername);

            // If manager doesn't exist or the managerUsername doesn't belong to a manager
            if (!users.containsKey(managerUsername) || !(manager instanceof ProjectManager))
                throw new ManagerUsernameInvalidException(managerUsername);

            user = new DeveloperClass(managerUsername, username, clearanceLevel);

            ((ProjectManager) manager).addDeveloper(user);
        }

        users.put(username, user);
        usersByName.add(user);
    }

    @Override
    public Iterator<User> listAllUsers() {
        return usersByName.iterator();
    }

    @Override
    public void createProject(String managerUsername, String projectType, String projectName, String[] keywords, String companyName, int confidentialityLevel)
            throws UnknownProjectTypeException, ManagerUsernameInvalidException, ProjectNameAlreadyExistsException, ConfidentialityLevelHigherThanManagerException {

    }

    @Override
    public Iterator<Project> listAllProjects() {
        return null;
    }

    @Override
    public void addTeamMember(String managerUsername, String projectName, String developerUsername) throws ManagerUsernameInvalidException, ProjectNameDoesntExistException, ProjectNotManagedByManagerException, DeveloperAlreadyMemberException, InsufficientClearanceLevelException {

    }

    @Override
    public void addArtefact(String developerUsername, String projectName, Date date, String artefactName, int confidentialityLevel, String description) throws UserNameDoesntExistException, ProjectNameDoesntExistException, DeveloperNotMemberException, ArtefactAlreadyInProjectException, ArtefactExceedsConfidentialityException {

    }

    @Override
    public Project listProjectInfo(String projectName) throws ProjectNameDoesntExistException, ProjectIsOutsourcedException {
        return null;
    }

    @Override
    public void reviewArtefact(String username, String projectName, String artefactName, Date date, String comment) throws UserNameDoesntExistException, ProjectNameDoesntExistException, DeveloperNotMemberException, ArtefactNotInProjectException {

    }

    @Override
    public Iterator<User> listDevelopersInfo(String managerUsername) {
        return null;
    }

    @Override
    public Iterator<Project> listProjectsByKeyword(String keyword) {
        return null;
    }

    @Override
    public Iterator<Project> listProjectsByConfidentiality(int lowerLimit, int upperLimit) {
        return null;
    }

    @Override
    public Workaholics listWorkaholics(){
        return null;
    }

    @Override
    public Commonality listTopCommonUsers(){
        return null;
    }
}
