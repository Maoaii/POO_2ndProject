package versionControlSystem;

import versionControlSystem.comparators.ProjectComparator;
import versionControlSystem.comparators.WorkaholicComparator;
import versionControlSystem.project.*;
import versionControlSystem.user.*;
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
    public Iterator<Project> listProjectsByKeyword(String keyword) throws NoProjectsWithKeywordException{
    	Set<Project> projectsWithKeyword = new TreeSet<Project>(new ProjectComparator());
    	Iterator<Project> it = projectsByInsertion.iterator();

    	while(it.hasNext()) {
    		Project project = it.next();
    		if(project.hasKeyword(keyword)) {
                projectsWithKeyword.add(project);
    		}
    	}
    	// Na minha opinião, não haver projetos com este filtro não é uma exceção, portanto acho que
        // poderiamos simplesmente retornar o iterator e, na main, se "!iterador.hasNext()", fazemos print da mensagem
        // de "erro"
    	if(projectsWithKeyword.size() == 0) {
    		throw new NoProjectsWithKeywordException(keyword);
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

    // TODO: vale mesmo a pena ter uma entidade para os workaholics? Devemos retornar o iterador?
    @Override
    public Workaholics getWorkaholics(){
        Set<User> usersByWorkaholicness = new TreeSet<>(new WorkaholicComparator());
        Workaholics workaholics = new WorkaholicsClass();

        Iterator<User> userIterator = listAllUsers();
        while(userIterator.hasNext()) {
            User user = userIterator.next();
            if (user.getNumRevisions() > 0)
                usersByWorkaholicness.add(user);
        }

        int workaholicCounter = 0;
        Iterator<User> workaholicsIterator = usersByWorkaholicness.iterator();
        while (workaholicCounter < 3 && workaholicsIterator.hasNext()) { // TODO: make "3" a constant? Or find a better way (maybe a method that return 3 in WorkaholicClass)
            workaholics.addWorkaholic(workaholicsIterator.next());
            workaholicCounter++;
        }

        return workaholics;
    }

    // TODO: should this return the common users iterator?
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
                int sumProject = 0;
                User secondUser = secondUserIterator.next();

                if (!firstUser.equals(secondUser)) {

                    // Check first person projects as manager
                    if (firstUser instanceof ProjectManager) {
                        sumProject += ((ProjectManager) firstUser).getCommonProjectsAsManager(secondUser);
                    }
                    // Check first person projects as developer
                    else if (firstUser instanceof Developer && secondUser instanceof ProjectManager){
                        sumProject += ((ProjectManager) secondUser).getCommonProjectsAsManager(firstUser);
                    }

                    // Check projects as developer
                    sumProject += firstUser.getCommonProjectsAsDeveloper(secondUser);

                }

                if (sumProject > 0) {
                    if (sumProject > sumMostProjects) {
                        sumMostProjects = sumProject;
                        user1 = firstUser;
                        user2 = secondUser;
                    }
                    else if (sumProject == sumMostProjects) {
                        if (user1.compareTo(firstUser) > 0) {
                            user1 = firstUser;
                            user2 = secondUser;
                        }
                        else if (user2.compareTo(secondUser) > 0){
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
