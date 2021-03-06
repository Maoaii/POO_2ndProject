package versionControlSystem;

import versionControlSystem.comparators.ProjectComparator;
import versionControlSystem.enumerates.JobPositions;
import versionControlSystem.enumerates.ProjectTypes;
import versionControlSystem.exceptions.*;
import versionControlSystem.project.*;
import versionControlSystem.user.DeveloperClass;
import versionControlSystem.user.ProjectManager;
import versionControlSystem.user.ProjectManagerClass;
import versionControlSystem.user.User;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Lucas Girotto / Pedro Afonso
 *
 * <code>VersionControlSystem</code> Class. Has a map of <code>User</code>s, a collection of <code>User</code>s,
 * stored alphabetically, a map of <code>Project</code>s and a collection of <code>Project</code>s, stored
 * by insertion order.
 */
public class VersionControlSystemClass implements VersionControlSystem {
    // Instance variables
    private final Map<String, User> users; // Stores users for easy access. username -> User
    private final Set<User> usersByName; // Stores Users ordered by name
    private final Map<String, Project> projects; // Stores projects for easy access. projectName -> Project
    private final List<Project> projectsByInsertion; // Stores projects by insertion order

    /**
     * Version Control System constructor
     */
    public VersionControlSystemClass() {
        users = new HashMap<>();
        usersByName = new TreeSet<>();
        projects = new HashMap<>();
        projectsByInsertion = new LinkedList<>();
    }

    @Override
    public void registerUser(String jobPosition, String username, String managerUsername, int clearanceLevel)
            throws UnknownJobPositionException, UserNameAlreadyExistsException, ManagerUsernameInvalidException {
        JobPositions isValidJobPosition;
        try {
            isValidJobPosition = JobPositions.valueOf(jobPosition.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownJobPositionException();
        }

        if (users.containsKey(username))
            throw new UserNameAlreadyExistsException(username);


        User user;
        if (isValidJobPosition.equals(JobPositions.MANAGER)) {
            user = new ProjectManagerClass(username, clearanceLevel);
        } else {
            User manager = users.get(managerUsername);

            // If manager doesn't exist or the managerUsername doesn't belong to a manager
            if (!(manager instanceof ProjectManager))
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
    public void createProject(String managerUsername, String projectType, String projectName,
                              String[] keywords, String companyName, int confidentialityLevel)
            throws UnknownProjectTypeException, ManagerUsernameInvalidException,
            ProjectNameAlreadyExistsException, ConfidentialityLevelHigherThanManagerException {

        ProjectTypes isValidProjectType;
        try {
            isValidProjectType = ProjectTypes.valueOf(projectType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownProjectTypeException();
        }

        User manager = users.get(managerUsername);
        // If manager doesn't exist or the managerUsername doesn't belong to a manager
        if (!(manager instanceof ProjectManager))
            throw new ManagerUsernameInvalidException(managerUsername);
        if (projects.containsKey(projectName))
            throw new ProjectNameAlreadyExistsException(projectName);
        if (confidentialityLevel > manager.getClearanceLevel())
            throw new ConfidentialityLevelHigherThanManagerException(managerUsername, manager.getClearanceLevel());


        Project project;
        if (isValidProjectType.equals(ProjectTypes.INHOUSE)) {
            project = new InHouseProjectClass(manager, projectName, keywords, confidentialityLevel);
        } else {
            project = new OutsourcedProjectClass(manager, projectName, keywords, companyName);
        }

        ((ProjectManager) manager).addProjectAsManager(project);
        manager.addProject(project);
        projects.put(projectName, project);
        projectsByInsertion.add(project);
    }

    @Override
    public Iterator<Project> listAllProjects() {
        return projectsByInsertion.iterator();
    }

    @Override
    public void checkManagerProject(String managerUsername, String projectName)
            throws ManagerUsernameInvalidException, ProjectNameDoesntExistException, ProjectNotManagedByManagerException {

        User manager = users.get(managerUsername);
        // If manager doesn't exist or the managerUsername doesn't belong to a manager
        if (!(manager instanceof ProjectManager))
            throw new ManagerUsernameInvalidException(managerUsername);

        Project project = projects.get(projectName);
        if (!(project instanceof InHouseProject))
            throw new ProjectNameDoesntExistException(projectName);
        if (!project.getProjectManagerUsername().equals(managerUsername))
            throw new ProjectNotManagedByManagerException(project.getProjectManagerUsername(), projectName);
    }

    @Override
    public void addTeamMember(String projectName, String memberName)
            throws UserDoesntExistException, DeveloperAlreadyMemberException, InsufficientClearanceLevelException {

        User member = users.get(memberName);
        if (member == null)
            throw new UserDoesntExistException(memberName);

        Project project = projects.get(projectName);
        if (member.isMember(projectName) || member.getUsername().equals(project.getProjectManagerUsername()))
            throw new DeveloperAlreadyMemberException(memberName);
        if (((InHouseProject) project).getConfidentialityLevel() > member.getClearanceLevel())
            throw new InsufficientClearanceLevelException(memberName);

        ((InHouseProject) project).addMember(member);
        member.addProject(project);
    }


    @Override
    public void checkDeveloperProject(String developerUsername, String projectName)
            throws UserNameDoesntExistException, ProjectNameDoesntExistException, DeveloperNotMemberException {
        User developer = users.get(developerUsername);
        if (developer == null) {
            throw new UserNameDoesntExistException(developerUsername);
        }

        Project project = projects.get(projectName);
        if (!(project instanceof InHouseProject)) {
            throw new ProjectNameDoesntExistException(projectName);
        }

        if (!developer.isMember(projectName) && !project.getProjectManagerUsername().equals(developerUsername)) {
            throw new DeveloperNotMemberException(developerUsername, projectName);
        }
    }

    @Override
    public void addArtefact(String authorUsername, String projectName, String artefactName,
                            LocalDate artefactDate, int confidentialityLevel, String description)
            throws ArtefactAlreadyInProjectException, ArtefactExceedsConfidentialityException {

        Project project = projects.get(projectName);
        if (((InHouseProject) project).hasArtefact(artefactName)) {
            throw new ArtefactAlreadyInProjectException(artefactName);
        }
        if (((InHouseProject) project).getConfidentialityLevel() < confidentialityLevel) {
            throw new ArtefactExceedsConfidentialityException(artefactName);
        }

        ((InHouseProject) project).addArtefact(new ArtefactClass(projectName, authorUsername, artefactName, artefactDate,
                confidentialityLevel, description));
        // Add this artefact as the user first revision
        users.get(authorUsername).addRevision(new RevisionClass(projectName, artefactName, 1,
                authorUsername, artefactDate, description));
    }

    @Override
    public Iterator<Project> listProjectInfo(String projectName)
            throws ProjectNameDoesntExistException, ProjectIsOutsourcedException {

        Project project = projects.get(projectName);
        if (project == null)
            throw new ProjectNameDoesntExistException(projectName);
        if (project instanceof OutsourcedProject)
            throw new ProjectIsOutsourcedException(projectName);

        List<Project> projectArray = new ArrayList<>(1);

        projectArray.add(project);

        return projectArray.iterator();
    }

    @Override
    public int reviewArtefact(String username, String projectName, String artefactName, LocalDate date, String comment)
            throws UserNameDoesntExistException, ProjectNameDoesntExistException,
            ArtefactNotInProjectException, DeveloperNotMemberException {

        User user = users.get(username);
        if (user == null)
            throw new UserNameDoesntExistException(username);

        Project project = projects.get(projectName);
        if (!(project instanceof InHouseProject))
            throw new ProjectNameDoesntExistException(projectName);
        if (!((InHouseProject) project).hasArtefact(artefactName))
            throw new ArtefactNotInProjectException(artefactName);
        if (!user.isMember(projectName))
            throw new DeveloperNotMemberException(username, projectName);

        Revision revision = new RevisionClass(projectName, artefactName,
                ((InHouseProject) project).getNumArtefactRevisions(artefactName) + 1,
                username, date, comment);
        ((InHouseProject) project).reviewArtefact(artefactName, revision);
        user.addRevision(revision);

        return revision.getRevisionNumber();
    }

    @Override
    public Iterator<User> listDevelopersInfo(String managerUsername) throws ManagerUsernameInvalidException {
        User manager = users.get(managerUsername);
        if (!(manager instanceof ProjectManager))
            throw new ManagerUsernameInvalidException(managerUsername);

        return ((ProjectManager) manager).getDevelopers();
    }


    @Override
    public Iterator<Project> listProjectsByKeyword(String keyword) {
        Set<Project> projectsWithKeyword = new TreeSet<>(new ProjectComparator());
        Iterator<Project> it = projectsByInsertion.iterator();

        while (it.hasNext()) {
            Project project = it.next();
            if (project.hasKeyword(keyword)) {
                projectsWithKeyword.add(project);
            }
        }

        return projectsWithKeyword.iterator();
    }

    @Override
    public Iterator<Project> listProjectsByConfidentiality(int lowerLimit, int upperLimit) {

        Iterator<Project> projectIterator = listAllProjects();
        Set<Project> projectsWithinLimit = new TreeSet<>();

        while (projectIterator.hasNext()) {
            Project project = projectIterator.next();

            if (project instanceof InHouseProject)
                if (((InHouseProject) project).getConfidentialityLevel() >= lowerLimit &&
                        ((InHouseProject) project).getConfidentialityLevel() <= upperLimit)
                    projectsWithinLimit.add(project);
        }

        return projectsWithinLimit.iterator();
    }

    @Override
    public Iterator<User> getWorkaholics() {
        Workaholics workaholics = new WorkaholicsClass();

        Iterator<User> userIterator = listAllUsers();
        while (userIterator.hasNext()) {
            User workaholic = userIterator.next();
            if (workaholic.getNumRevisions() > 0) {
                workaholics.addWorkaholic(workaholic);
            }
        }

        return workaholics.getWorkaholics();
    }

    @Override
    public Commonality listTopCommonUsers() {
        Commonality commonality = new CommonalityClass();
        User user1 = null;
        User user2 = null;
        int sumMostProjects = 0;

        Iterator<User> firstUserIterator = listAllUsers();
        while (firstUserIterator.hasNext()) {

            User firstUser = firstUserIterator.next();

            Iterator<User> secondUserIterator = listAllUsers();
            while (secondUserIterator.hasNext()) {
                int sumProjects = 0;
                User secondUser = secondUserIterator.next();

                if (!firstUser.equals(secondUser)) {
                    sumProjects += firstUser.getCommonProjects(secondUser);
                }

                if (sumProjects > 0) {
                    if (sumProjects > sumMostProjects) {
                        sumMostProjects = sumProjects;
                        user1 = firstUser;
                        user2 = secondUser;
                    } else if (sumProjects == sumMostProjects) {
                        if (user1.compareTo(firstUser) > 0 || user2.compareTo(secondUser) > 0) {
                            user1 = firstUser;
                            user2 = secondUser;
                        }
                    }
                }
            }
        }

        if (user1 != null && user2 != null)
            commonality.addCommonUsers(user1, user2, sumMostProjects);

        return commonality;
    }
}

