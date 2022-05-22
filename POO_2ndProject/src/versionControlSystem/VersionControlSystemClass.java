package versionControlSystem;

import versionControlSystem.comparators.ComparatorByName;
import versionControlSystem.project.*;
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
    private static final String INHOUSEPROJECT = "inhouse";
    private static final String OUTSOURCEDPROJECT = "outsourced";
    private static final String USER_DOESNT_EXIST = ": does not exist.";
    private static final String USER_ALREADY_MEMBER = ": already a member.";
    private static final String USER_INSUF_CLEARANCE = ": insufficient clearance level.";
    private static final String USER_ADDED = ": added to the team.";

    // Instance variables
    private Map<String, User> users; // Stores users for easy access
    private Set<User> usersByName; // Stores Users ordered by name
    private Map<String, Project> projects; // Stores projects for easy access
    private List<Project> projectsByInsertion; // Stores Project's by insertion order

    /**
     * Version Control System constructor
     */
    public VersionControlSystemClass() {
        users = new HashMap<>();
        usersByName = new TreeSet<>(new ComparatorByName());
        projects = new HashMap<>();
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
            if (manager == null || !(manager instanceof ProjectManager))
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
        boolean isInHouse = projectType.equals(INHOUSEPROJECT);
        boolean isOutsourced = projectType.equals(OUTSOURCEDPROJECT);

        if (!isInHouse && !isOutsourced)
            throw new UnknownProjectTypeException();

        User manager = users.get(managerUsername);
        // If manager doesn't exist or the managerUsername doesn't belong to a manager
        if (manager == null || !(manager instanceof ProjectManager))
            throw new ManagerUsernameInvalidException(managerUsername);
        if (projects.containsKey(projectName))
            throw new ProjectNameAlreadyExistsException(projectName);
        if (confidentialityLevel > manager.getClearanceLevel())
            throw new ConfidentialityLevelHigherThanManagerException(managerUsername, manager.getClearanceLevel());


        Project project;
        if (isInHouse) {
            project = new InHouseProjectClass(managerUsername, projectName, keywords, confidentialityLevel);
        }
        else {
            project = new OutsourcedProjectClass(managerUsername, projectName, keywords, companyName);
        }

        ((ProjectManager) users.get(managerUsername)).addProjectAsManager(project);
        projects.put(projectName, project);
        projectsByInsertion.add(project);
    }

    @Override
    public Iterator<Project> listAllProjects() {
        return projectsByInsertion.iterator();
    }

    @Override
    public String[] addTeamMembers(String managerUsername, String projectName, String[] memberNames)
            throws ManagerUsernameInvalidException, ProjectNameDoesntExistException, ProjectNotManagedByManagerException {

        User manager = users.get(managerUsername);
        // If manager doesn't exist or the managerUsername doesn't belong to a manager
        if (manager == null || !(manager instanceof ProjectManager))
            throw new ManagerUsernameInvalidException(managerUsername);

        Project project = projects.get(projectName);
        if (project == null || project instanceof OutsourcedProject)
            throw new ProjectNameDoesntExistException(projectName);
        if (!project.getProjectManagerUsername().equals(managerUsername))
            throw new ProjectNotManagedByManagerException(project.getProjectManagerUsername(), projectName);

        String[] outputMessages = new String[memberNames.length];
        for (int i = 0; i < memberNames.length; i++) {
            User member = users.get(memberNames[i]);
            if (member == null)
                outputMessages[i] = memberNames[i] + USER_DOESNT_EXIST;
            else if (member.isMember(projectName) || member.getUsername().equals(project.getProjectManagerUsername()))
                outputMessages[i] = memberNames[i] + USER_ALREADY_MEMBER;
            else if (((InHouseProject) project).getConfidentialityLevel() > member.getClearanceLevel())
                outputMessages[i] = memberNames[i] + USER_INSUF_CLEARANCE;
            else {
                member.addProject(project);
                ((InHouseProject) project).addMember(member);
                outputMessages[i] = memberNames[i] + USER_ADDED;
            }
        }

        return outputMessages;
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
