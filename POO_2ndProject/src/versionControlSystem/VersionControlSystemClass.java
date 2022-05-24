package versionControlSystem;

import versionControlSystem.comparators.ComparatorByName;
import versionControlSystem.project.*;
import versionControlSystem.user.DeveloperClass;
import versionControlSystem.user.ProjectManager;
import versionControlSystem.user.ProjectManagerClass;
import versionControlSystem.user.User;
import versionControlSystem.exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VersionControlSystemClass implements VersionControlSystem {
    // Constants
    private static final String PROJECTMANAGER = "manager";
    private static final String DEVELOPER = "developer";
    private static final String INHOUSEPROJECT = "inhouse";
    private static final String OUTSOURCEDPROJECT = "outsourced";


    // Instance variables
    private Map<String, User> users; // Stores users for easy access
    private Set<User> usersByName; // Stores Users ordered by name
    private Map<String, Project> projects; // Stores projects for easy access
    private List<Project> projectsByInsertion; // Stores projects by insertion order

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
            project = new InHouseProjectClass(manager, projectName, keywords, confidentialityLevel);
        }
        else {
            project = new OutsourcedProjectClass(manager, projectName, keywords, companyName);
        }

        ((ProjectManager) manager).addProjectAsManager(project);
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
    public void checkDeveloper(String developerUsername, String projectName)
            throws UserNameDoesntExistException, ProjectNameDoesntExistException, DeveloperNotMemberException {
		if(!users.containsKey(developerUsername)){
            throw new UserNameDoesntExistException(developerUsername);
        }
        if(!projects.containsKey(projectName) || projects.get(projectName) instanceof OutsourcedProject){
            throw new ProjectNameDoesntExistException(projectName);
        }
        User developer = users.get(developerUsername);
        if(!developer.isMember(projectName)){
            throw new DeveloperNotMemberException(developerUsername, projectName);
        }
    }
    
    public void addArtefact(String projectName, String artefactName, String artefactDate, int confidentialityLevel, String description)
    		throws ArtefactAlreadyInProjectException, ArtefactExceedsConfidentialityException{
    	Project project = projects.get(projectName);
    	if(((InHouseProject) project).hasArtefact(artefactName)) {
    		throw new ArtefactAlreadyInProjectException(artefactName);
    	}
    	if(((InHouseProject) project).getConfidentialityLevel() < confidentialityLevel) {
    		throw new ArtefactExceedsConfidentialityException(artefactName);
    	}
    	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-mm-yyyy");
    	LocalDate date = LocalDate.parse(artefactDate, format);
    	((InHouseProject) project).addArtefact(new ArtefactClass(artefactName, date, confidentialityLevel, description));
    }

    @Override
    public Iterator<Project> listProjectInfo(String projectName)
            throws ProjectNameDoesntExistException, ProjectIsOutsourcedException {
        Project project = projects.get(projectName);
        if (project == null)
            throw new ProjectNameDoesntExistException(projectName);
        if (project instanceof OutsourcedProject)
            throw new ProjectIsOutsourcedException(projectName);

        List<Project> projectArray = new ArrayList<>(1); // TODO: tornar isto constante?

        projectArray.add(project);

        return projectArray.iterator();
    }

    @Override
    public int reviewArtefact(String username, String projectName, String artefactName, LocalDate date, String comment)
            throws UserNameDoesntExistException, ProjectNameDoesntExistException,
                   DeveloperNotMemberException, ArtefactNotInProjectException {
        return 0;
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
