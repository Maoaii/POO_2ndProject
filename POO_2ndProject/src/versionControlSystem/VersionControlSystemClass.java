package versionControlSystem;

import versionControlSystem.project.*;
import versionControlSystem.user.DeveloperClass;
import versionControlSystem.user.ProjectManager;
import versionControlSystem.user.ProjectManagerClass;
import versionControlSystem.user.User;
import versionControlSystem.exceptions.*;

import java.time.LocalDate;
import java.util.*;

public class VersionControlSystemClass implements VersionControlSystem {
    // Constants
    private static final String PROJECTMANAGER = "manager";
    private static final String DEVELOPER = "developer";
    private static final String INHOUSEPROJECT = "inhouse";
    private static final String OUTSOURCEDPROJECT = "outsourced";


    // Instance variables
    private Map<String, User> users; // Stores users for easy access. username -> User
    private Set<User> usersByName; // Stores Users ordered by name
    private Map<String, Project> projects; // Stores projects for easy access. projectName -> Project
    private List<Project> projectsByInsertion; // Stores projects by insertion order
    // private Set<Project> sortedProjects;

    /**
     * Version Control System constructor
     */
    public VersionControlSystemClass() {
        users = new HashMap<>();
        usersByName = new TreeSet<>();
        projects = new HashMap<>();
        projectsByInsertion = new LinkedList<>();
        // sortedProjects = new TreeSet<>(new ProjectComparator());
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
    public void createProject(String managerUsername, String projectType, String projectName,
                              String[] keywords, String companyName, int confidentialityLevel)
            throws UnknownProjectTypeException, ManagerUsernameInvalidException,
                   ProjectNameAlreadyExistsException, ConfidentialityLevelHigherThanManagerException {

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
            project = new InHouseProjectClass(manager, projectName, keywords, confidentialityLevel);
        }
        else {
            project = new OutsourcedProjectClass(manager, projectName, keywords, companyName);
        }

        ((ProjectManager) manager).addProjectAsManager(project);
        projects.put(projectName, project);
        projectsByInsertion.add(project);
        // sortedProjects.add(project);
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
        if (manager == null || !(manager instanceof ProjectManager))
            throw new ManagerUsernameInvalidException(managerUsername);

        Project project = projects.get(projectName);
        if (project == null || project instanceof OutsourcedProject)
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
		if(developer == null){
            throw new UserNameDoesntExistException(developerUsername);
        }

        Project project = projects.get(projectName);
        if(project == null || project instanceof OutsourcedProject){
            throw new ProjectNameDoesntExistException(projectName);
        }


        if(!developer.isMember(projectName) && !project.getProjectManagerUsername().equals(developerUsername)) {
            throw new DeveloperNotMemberException(developerUsername, projectName);
        }
    }
    
    @Override
    public void addArtefact(String authorUsername ,String projectName, String artefactName,
                            LocalDate artefactDate, int confidentialityLevel, String description)
    		throws ArtefactAlreadyInProjectException, ArtefactExceedsConfidentialityException{
    	Project project = projects.get(projectName);
    	if(((InHouseProject) project).hasArtefact(artefactName)) {
    		throw new ArtefactAlreadyInProjectException(artefactName);
    	}
    	if(((InHouseProject) project).getConfidentialityLevel() < confidentialityLevel) {
    		throw new ArtefactExceedsConfidentialityException(artefactName);
    	}

    	((InHouseProject) project).addArtefact(new ArtefactClass(projectName, authorUsername, artefactName, artefactDate,
                                                                 confidentialityLevel, description));
        // Add this artefact as the user first revision
        users.get(authorUsername).addRevision(new RevisionClass(projectName, artefactName, 1,
                                                                authorUsername, artefactDate, description));
    }

    // TODO: ask teacher if we can return an iterator of 1 User
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
        if (project == null || project instanceof OutsourcedProject)
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
        if (manager == null || !(manager instanceof ProjectManager))
            throw new ManagerUsernameInvalidException(managerUsername);

        return ((ProjectManager) manager).getDevelopers();
    }

    @Override
    public Iterator<Project> listProjectsByKeyword(String keyword) {

        /*
    	List<Project> projectsWithKeyword = new ArrayList<>();
    	Iterator<Project> it = sortedProjects.iterator();

    	while(it.hasNext()) {
    		Project project = it.next();
    		if(project.hasKeyword(keyword)) {
                projectsWithKeyword.add(project);
    		}
    	}

        return projectsWithKeyword.iterator();
        */

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
