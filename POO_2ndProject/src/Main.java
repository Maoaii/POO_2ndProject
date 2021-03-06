import versionControlSystem.Commonality;
import versionControlSystem.VersionControlSystem;
import versionControlSystem.VersionControlSystemClass;
import versionControlSystem.exceptions.*;
import versionControlSystem.project.*;
import versionControlSystem.user.Developer;
import versionControlSystem.user.ProjectManager;
import versionControlSystem.user.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    //Keywords
    private static final String DEVELOPER = "developer";
    private static final String INHOUSE = "inhouse";
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    /**
     * 2.1 EXIT Command
     */
    private static final String EXIT_MESSAGE = "Bye!";


    // Output messages
    /**
     * 2.2 HELP Command
     */
    private static final String HELP_MESSAGE = "Available commands:\n" +
            "register - adds a new user\n" +
            "users - lists all registered users\n" +
            "create - creates a new project\n" +
            "projects - lists all projects\n" +
            "team - adds team members to a project\n" +
            "artefacts - adds artefacts to a project\n" +
            "project - shows detailed project information\n" +
            "revision - revises an artefact\n" +
            "manages - lists developers of a manager\n" +
            "keyword - filters projects by keyword\n" +
            "confidentiality - filters projects by confidentiality level\n" +
            "workaholics - top 3 employees with more artefacts updates\n" +
            "common - employees with more projects in common\n" +
            "help - shows the available commands\n" +
            "exit - terminates the execution of the program";
    /**
     * 2.3 REGISTER Command
     */
    private static final String USER_REGISTERED = "User %s was registered as %s with clearance level %s.\n";
    /**
     * 2.4 USERS Command
     */
    private static final String USERS_HEADER = "All registered users:";
    private static final String USERS_LISTING_MANAGER = "manager %s [%d, %d, %d]\n";
    private static final String USERS_LISTING_DEVELOPER = "developer %s is managed by %s [%d]\n";
    private static final String NO_USERS_REGISTERED = "No users registered.";
    /**
     * 2.5 CREATE Command
     */
    private static final String PROJECT_CREATED = "%s project was created.\n";
    /**
     * 2.6 PROJECTS Command
     */
    private static final String PROJECTS_HEADER = "All projects:";
    private static final String PROJECTS_LISTING_INHOUSE = "in-house %s is managed by %s [%d, %d, %d, %d]\n";
    private static final String PROJECTS_LISTING_OUTSOURCED = "outsourced %s is managed by %s and developed by %s\n";
    private static final String NO_PROJECTS_REGISTERED = "No projects added.";
    /**
     * 2.7 TEAM Command
     */
    private static final String TEAM_HEADER = "Latest team members:";
    private static final String TEAM_MEMBER_ADDED = "%s: added to the team.\n";
    /**
     * 2.8 ARTEFACTS Command
     */
    private static final String ARTEFACT_HEADER = "Latest project artefacts:";
    private static final String ARTEFACT_ADDED = "%s: added to the project.\n";
    /**
     * 2.9 PROJECT Command
     */
    private static final String PROJECT_LISTING = "%s [%d] managed by %s [%d]:\n";
    private static final String PROJECT_MEMBERS_LISTING = "%s [%d]\n";
    private static final String PROJECT_ARTEFACTS_LISTING = "%s [%d]\n";
    private static final String PROJECT_REVISIONS_LISTING = "revision %d %s %s %s\n";
    /**
     * 2.10 REVISION Command
     */
    private static final String REVISION_ADDED = "Revision %d of artefact %s was submitted.\n";
    /**
     * 2.11 MANAGES Command
     */
    private static final String MANAGES_HEADER = "Manager %s:\n";
    private static final String MANAGES_REVISIONS_LISTING = "%s, %s, revision %d, %s, %s\n";
    /**
     * 2.12 KEYWORD Command
     */
    private static final String KEYWORD_HEADER = "All projects with keyword %s:\n";
    private static final String KEYWORD_INHOUSE_LISTING = "in-house %s is managed by %s [%d, %d, %d, %d, %s]\n";
    private static final String KEYWORD_OUTSOURCED_LISTING = "outsourced %s is managed by %s and developed by %s\n";
    private static final String NO_PROJECTS_WITH_KEYWORD = "No projects with keyword %s.\n";
    /**
     * 2.13 CONFIDENTIALITY Command
     */
    private static final String CONFIDENTIALITY_HEADER = "All projects within levels %d and %d:\n";
    private static final String CONFIDENTIALITY_LISTING = "%s is managed by %s and has keywords ";
    private static final String NO_PROJECTS_WITHIN_LEVELS = "No projects within levels %d and %d.\n";
    /**
     * 2.14 WORKAHOLICS Command
     */
    private static final String WORKAHOLICS_LISTING = "%s: %d updates, %d projects, last update on %s\n";
    private static final String NO_WORKAHOLICS = "There are no workaholics.";
    /**
     * 2.15 COMMON Command
     */
    private static final String COMMON_LISTING = "%s %s have %d projects in common.\n";
    private static final String NO_USERS_COMMON_PROJECTS = "Cannot determine employees with common projects.";
    /**
     * UNKOWN Command
     */
    private static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";

    public static void main(String[] args) {
        interpretCommands();
    }

    /**
     * Interprets the user commands
     */
    private static void interpretCommands() {
        Scanner in = new Scanner(System.in);
        VersionControlSystem vcSystem = new VersionControlSystemClass();
        Command command;

        do {
            command = readCommand(in);

            switch (command) {
                case REGISTER:
                    interpretRegister(in, vcSystem);
                    break;
                case USERS:
                    interpretUsers(vcSystem);
                    break;
                case CREATE:
                    interpretCreate(in, vcSystem);
                    break;
                case PROJECTS:
                    interpretProjects(vcSystem);
                    break;
                case TEAM:
                    interpretTeam(in, vcSystem);
                    break;
                case ARTEFACTS:
                    interpretArtefacts(in, vcSystem);
                    break;
                case PROJECT:
                    interpretProject(in, vcSystem);
                    break;
                case REVISION:
                    interpretRevision(in, vcSystem);
                    break;
                case MANAGES:
                    interpretManages(in, vcSystem);
                    break;
                case KEYWORD:
                    interpretKeyword(in, vcSystem);
                    break;
                case CONFIDENTIALITY:
                    interpretConfidentiality(in, vcSystem);
                    break;
                case WORKAHOLICS:
                    interpretWorkaholics(vcSystem);
                    break;
                case COMMON:
                    interpretCommon(vcSystem);
                    break;
                case HELP:
                    interpretHelp();
                    break;
                case EXIT:
                    interpretExit();
                    break;
                default:
                    System.out.println(UNKNOWN_COMMAND);
                    break;
            }
        } while (!command.equals(Command.EXIT));
        in.close();
    }

    /**
     * Registers a new <code>User</code> to the system
     *
     * @param in       - input reader
     * @param vcSystem - system class
     */
    private static void interpretRegister(Scanner in, VersionControlSystem vcSystem) {
        String jobType = in.next().trim();
        String username = in.next().trim();

        String managerUsername = null;
        if (jobType.equals(DEVELOPER)) {
            managerUsername = in.next().trim();
        }

        int clearanceLevel = in.nextInt();
        in.nextLine();

        try {
            vcSystem.registerUser(jobType, username, managerUsername, clearanceLevel);
            System.out.printf(USER_REGISTERED, username, jobType, clearanceLevel);
        } catch (UnknownJobPositionException e) {
            System.out.println(e.getErrorMessage());
        } catch (UserNameAlreadyExistsException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
        } catch (ManagerUsernameInvalidException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
        }
    }

    /**
     * Lists all registered <code>User</code>s in the system
     *
     * @param vcSystem - system class
     */
    private static void interpretUsers(VersionControlSystem vcSystem) {
        Iterator<User> usersIt = vcSystem.listAllUsers();

        if (!usersIt.hasNext())
            System.out.println(NO_USERS_REGISTERED);
        else {
            System.out.println(USERS_HEADER);

            while (usersIt.hasNext()) {
                User user = usersIt.next();

                if (user instanceof ProjectManager)
                    System.out.printf(USERS_LISTING_MANAGER,
                            user.getUsername(), ((ProjectManager) user).getNumDevelopers(),
                            ((ProjectManager) user).getNumProjectsAsManagers(), user.getNumProjectsAsMember());
                else
                    System.out.printf(USERS_LISTING_DEVELOPER,
                            user.getUsername(), ((Developer) user).getManager(), user.getNumProjectsAsMember());
            }
        }
    }

    /**
     * Creates a new <code>Project</code> in the system
     *
     * @param in       - input reader
     * @param vcSystem - system class
     */
    private static void interpretCreate(Scanner in, VersionControlSystem vcSystem) {
        String projectManager = in.next();
        String projectType = in.next();
        String projectName = in.nextLine().trim();

        int numKeywords = in.nextInt();
        String[] keywords = new String[numKeywords];
        for (int i = 0; i < numKeywords; i++) {
            keywords[i] = in.next();
        }
        in.nextLine();

        int confidentialityLevel = 0;
        String companyName = null;
        if (projectType.equals(INHOUSE))
            confidentialityLevel = in.nextInt();
        else
            companyName = in.nextLine().trim();

        try {
            vcSystem.createProject(projectManager, projectType, projectName, keywords, companyName, confidentialityLevel);
            System.out.printf(PROJECT_CREATED, projectName);
        } catch (UnknownProjectTypeException e) {
            System.out.print(e.getErrorMessage());
        } catch (ManagerUsernameInvalidException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
        } catch (ProjectNameAlreadyExistsException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
        } catch (ConfidentialityLevelHigherThanManagerException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfoName(), e.getErrorInfoClearanceLevel());
        }
    }

    /**
     * Lists out all the <code>Project</code>s registered in the system
     *
     * @param vcSystem - system class
     */
    private static void interpretProjects(VersionControlSystem vcSystem) {
        Iterator<Project> projectIt = vcSystem.listAllProjects();

        if (!projectIt.hasNext())
            System.out.println(NO_PROJECTS_REGISTERED);
        else {
            System.out.println(PROJECTS_HEADER);

            while (projectIt.hasNext()) {
                Project project = projectIt.next();

                if (project instanceof InHouseProject)
                    System.out.printf(PROJECTS_LISTING_INHOUSE,
                            project.getProjectName(), project.getProjectManagerUsername(),
                            ((InHouseProject) project).getConfidentialityLevel(), ((InHouseProject) project).getNumMembers(),
                            ((InHouseProject) project).getNumArtefacts(), ((InHouseProject) project).getNumRevisions());
                else
                    System.out.printf(PROJECTS_LISTING_OUTSOURCED,
                            project.getProjectName(), project.getProjectManagerUsername(),
                            ((OutsourcedProject) project).getCompanyName());
            }
        }
    }

    /**
     * Adds new <code>User</code>s to an existing <code>Project</code>s <code>Team</code>>
     *
     * @param in       - input reader
     * @param vcSystem - system class
     */
    private static void interpretTeam(Scanner in, VersionControlSystem vcSystem) {
        String managerUsername = in.next().trim();
        String projectName = in.nextLine().trim();

        int numMembers = in.nextInt();

        String[] memberNames = new String[numMembers];
        for (int i = 0; i < numMembers; i++) {
            memberNames[i] = in.next().trim();
        }
        in.nextLine();

        try {
            vcSystem.checkManagerProject(managerUsername, projectName);
        } catch (ManagerUsernameInvalidException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
            return;
        } catch (ProjectNameDoesntExistException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
            return;
        } catch (ProjectNotManagedByManagerException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfoProjectName(), e.getErrorInfoName());
            return;
        }

        System.out.println(TEAM_HEADER);
        for (int i = 0; i < numMembers; i++) {
            try {
                vcSystem.addTeamMember(projectName, memberNames[i]);
                System.out.printf(TEAM_MEMBER_ADDED, memberNames[i]);
            } catch (InsufficientClearanceLevelException e) {
                System.out.printf(e.getErrorMessage(), e.getErrorInfo());
            } catch (UserDoesntExistException e) {
                System.out.printf(e.getErrorMessage(), e.getErrorInfo());
            } catch (DeveloperAlreadyMemberException e) {
                System.out.printf(e.getErrorMessage(), e.getErrorInfo());
            }
        }
    }

    /**
     * Adds new <code>Artefact</code>s to an existing <code>Project</code>
     *
     * @param in       - input reader
     * @param vcSystem - system class
     */
    private static void interpretArtefacts(Scanner in, VersionControlSystem vcSystem) {
        String developerName = in.next().trim();
        String projectName = in.nextLine().trim();
        LocalDate artefactDate = LocalDate.parse(in.nextLine().trim(),
                DateTimeFormatter.ofPattern(DATE_FORMAT));

        int numArtefacts = in.nextInt();
        in.nextLine();

        String[] artefactNames = new String[numArtefacts];
        int[] confidentialityLevel = new int[numArtefacts];
        String[] artefactDescription = new String[numArtefacts];

        for (int i = 0; i < numArtefacts; i++) {
            artefactNames[i] = in.next().trim();
            confidentialityLevel[i] = in.nextInt();
            artefactDescription[i] = in.nextLine().trim();
        }

        try {
            vcSystem.checkDeveloperProject(developerName, projectName);
        } catch (UserNameDoesntExistException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
            return;
        } catch (ProjectNameDoesntExistException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
            return;
        } catch (DeveloperNotMemberException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfoName(), e.getErrorInfoTeamName());
            return;
        }

        addArtefacts(vcSystem, developerName, projectName,
                artefactNames, artefactDate, confidentialityLevel, artefactDescription);

    }

    /**
     * Adds new <code>Artefact</code>s to the <code>VersionControlSystem</code>
     *
     * @param vcSystem              - <code>VersionControlSystem</code>
     * @param developerName         - <code>Artefact</code>s author
     * @param projectName           - <code>Project</code> the new <code>Artefact</code>s are added
     * @param artefactNames         - <code>Artefact</code>s <code>artefactNames</code>
     * @param artefactDate          - <code>Artefact</code>s <code>artefactDate</code>
     * @param confidentialityLevels - <code>Artefact</code>s <code>confidentialityLevel</code>
     * @param artefactDescriptions  - <code>Artefact</code>s <code>artefactDescription</code>
     */
    private static void addArtefacts(VersionControlSystem vcSystem, String developerName, String projectName, String[] artefactNames,
                                     LocalDate artefactDate, int[] confidentialityLevels, String[] artefactDescriptions) {
        System.out.println(ARTEFACT_HEADER);
        for (int i = 0; i < artefactNames.length; i++) {
            try {
                vcSystem.addArtefact(developerName, projectName, artefactNames[i],
                        artefactDate, confidentialityLevels[i], artefactDescriptions[i]);
                System.out.printf(ARTEFACT_ADDED, artefactNames[i]);
            } catch (ArtefactAlreadyInProjectException e) {
                System.out.printf(e.getErrorMessage(), e.getErrorInfo());
            } catch (ArtefactExceedsConfidentialityException e) {
                System.out.printf(e.getErrorMessage(), e.getErrorInfo());
            }
        }
    }


    /**
     * Shows the detailed information of an existing <code>In-House Project</code>
     *
     * @param in       - input reader
     * @param vcSystem - system class
     */
    private static void interpretProject(Scanner in, VersionControlSystem vcSystem) {
        String projectName = in.nextLine().trim();


        try {
            // Print project info
            Project project = vcSystem.listProjectInfo(projectName).next();
            System.out.printf(PROJECT_LISTING, project.getProjectName(), ((InHouseProject) project).getConfidentialityLevel(),
                    project.getProjectManagerUsername(), project.getProjectManagerClearanceLevel());

            // Print members info
            printMembersInfo(project);


            // Print artefacts info
            printArtefactsInfo(project);

        } catch (ProjectNameDoesntExistException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
        } catch (ProjectIsOutsourcedException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
        }
    }

    /**
     * Prints out the information of every <code>User</code> in <code>Project project</code>
     *
     * @param project - <code>Project</code> to list from
     */
    private static void printMembersInfo(Project project) {
        Iterator<User> memberIterator = ((InHouseProject) project).getProjectMembers();
        while (memberIterator.hasNext()) {
            User member = memberIterator.next();

            System.out.printf(PROJECT_MEMBERS_LISTING, member.getUsername(), member.getClearanceLevel());
        }
    }

    /**
     * Prints out the information of every <code>Artefact</code> in <code>Project project</code>
     *
     * @param project - <code>Project</code> to list from
     */
    private static void printArtefactsInfo(Project project) {
        Iterator<Artefact> artefactIterator = ((InHouseProject) project).getProjectArtefacts();
        while (artefactIterator.hasNext()) {
            Artefact artefact = artefactIterator.next();

            System.out.printf(PROJECT_ARTEFACTS_LISTING, artefact.getArtefactName(), artefact.getArtefactConfidentialityLevel());


            // Print revisions info
            Iterator<Revision> revisionIterator = artefact.getArtefactRevisions();
            while (revisionIterator.hasNext()) {
                Revision revision = revisionIterator.next();

                System.out.printf(PROJECT_REVISIONS_LISTING, revision.getRevisionNumber(), revision.getAuthorUsername(),
                        revision.getRevisionDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)),
                        revision.getRevisionComment());
            }
        }
    }

    /**
     * Revise an existing <code>Artefact</code>
     *
     * @param in       - input reader
     * @param vcSystem - system class
     */
    private static void interpretRevision(Scanner in, VersionControlSystem vcSystem) {
        String memberUsername = in.next().trim();
        String projectName = in.nextLine().trim();
        String artefactName = in.next().trim();
        LocalDate revisionDate = LocalDate.parse(in.next(), DateTimeFormatter.ofPattern(DATE_FORMAT));
        String revisionComment = in.nextLine().trim();

        try {
            int revisionNumber = vcSystem.reviewArtefact(memberUsername, projectName,
                    artefactName, revisionDate, revisionComment);
            System.out.printf(REVISION_ADDED, revisionNumber, artefactName);
        } catch (UserNameDoesntExistException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
        } catch (ArtefactNotInProjectException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
        } catch (DeveloperNotMemberException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfoName(), e.getErrorInfoTeamName());
        } catch (ProjectNameDoesntExistException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
        }
    }

    /**
     * Shows the detailed information about the <code>Developer</code>s supervised by a given <code>Manager</code>
     *
     * @param in       - input reader
     * @param vcSystem - system class
     */
    private static void interpretManages(Scanner in, VersionControlSystem vcSystem) {
        String managerUsername = in.nextLine().trim();

        try {
            Iterator<User> developersIterator = vcSystem.listDevelopersInfo(managerUsername);

            System.out.printf(MANAGES_HEADER, managerUsername);

            while (developersIterator.hasNext()) {
                User developer = developersIterator.next();

                System.out.println(developer.getUsername());

                Iterator<Revision> developerRevisionsIt = developer.getUserRevisions();
                while (developerRevisionsIt.hasNext()) {
                    Revision revision = developerRevisionsIt.next();

                    System.out.printf(MANAGES_REVISIONS_LISTING, revision.getProjectName(),
                            revision.getArtefactName(),
                            revision.getRevisionNumber(),
                            revision.getRevisionDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)),
                            revision.getRevisionComment());
                }
            }
        } catch (ManagerUsernameInvalidException e) {
            System.out.printf(e.getErrorMessage(), e.getErrorInfo());
        }
    }

    /**
     * Filters all <code>Project</code>s by a given <code>Keyword</code>
     *
     * @param in       - input reader
     * @param vcSystem - system class
     */
    private static void interpretKeyword(Scanner in, VersionControlSystem vcSystem) {
        String keyword = in.nextLine().trim();
        Iterator<Project> projectIterator = vcSystem.listProjectsByKeyword(keyword);

        if (!projectIterator.hasNext())
            System.out.printf(NO_PROJECTS_WITH_KEYWORD, keyword);
        else {
            System.out.printf(KEYWORD_HEADER, keyword);
            while (projectIterator.hasNext()) {
                Project project = projectIterator.next();
                if (project instanceof InHouseProject) {
                    System.out.printf(KEYWORD_INHOUSE_LISTING, project.getProjectName(), project.getProjectManagerUsername(),
                            ((InHouseProject) project).getConfidentialityLevel(), ((InHouseProject) project).getNumMembers(),
                            ((InHouseProject) project).getNumArtefacts(),
                            ((InHouseProject) project).getNumRevisions(),
                            ((InHouseProject) project).getLastUpdateDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
                } else {
                    System.out.printf(KEYWORD_OUTSOURCED_LISTING, project.getProjectName(), project.getProjectManagerUsername(),
                            ((OutsourcedProject) project).getCompanyName());
                }
            }
        }

    }

    /**
     * Filters all <code>In-House Project</code>s by their <code>Confidentiality Level</code>
     *
     * @param in       - input reader
     * @param vcSystem - system class
     */
    private static void interpretConfidentiality(Scanner in, VersionControlSystem vcSystem) {
        int limit1 = in.nextInt();
        int limit2 = in.nextInt();
        if (limit1 > limit2) {
            int tmp = limit2;
            limit2 = limit1;
            limit1 = tmp;
        }

        Iterator<Project> projectIterator = vcSystem.listProjectsByConfidentiality(limit1, limit2);

        if (!projectIterator.hasNext())
            System.out.printf(NO_PROJECTS_WITHIN_LEVELS, limit1, limit2);
        else {
            System.out.printf(CONFIDENTIALITY_HEADER, limit1, limit2);
            while (projectIterator.hasNext()) {
                Project project = projectIterator.next();

                System.out.printf(CONFIDENTIALITY_LISTING, project.getProjectName(),
                        project.getProjectManagerUsername());
                String[] keywords = project.getKeywords();

                for (int i = 0; i < keywords.length; i++) {
                    System.out.print(keywords[i]);
                    if (i != keywords.length - 1)
                        System.out.print(", ");
                    else
                        System.out.print(".");
                }
                System.out.println();
            }
        }
    }

    /**
     * List out the top three <code>User</code>s with the most <code>Artefact Revision</code>s
     *
     * @param vcSystem - system class
     */
    private static void interpretWorkaholics(VersionControlSystem vcSystem) {
        Iterator<User> workaholicsIterator = vcSystem.getWorkaholics();

        if (!workaholicsIterator.hasNext())
            System.out.println(NO_WORKAHOLICS);
        else {
            while (workaholicsIterator.hasNext()) {
                User workaholic = workaholicsIterator.next();
                System.out.printf(WORKAHOLICS_LISTING, workaholic.getUsername(), workaholic.getNumRevisions(),
                        workaholic.getNumProjectsAsMember(),
                        workaholic.getDateOfLastRevision().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
            }
        }

    }

    /**
     * List out the two <code>User</code>s that have the most <code>Project</code>s in common
     *
     * @param vcSystem - system class
     */
    private static void interpretCommon(VersionControlSystem vcSystem) {
        Commonality commonUsers = vcSystem.listTopCommonUsers();

        Iterator<User> commonUsersIterator = commonUsers.getCommonUsers();

        if (!commonUsersIterator.hasNext())
            System.out.println(NO_USERS_COMMON_PROJECTS);
        else {
            User user1 = commonUsersIterator.next();
            User user2 = commonUsersIterator.next();

            System.out.printf(COMMON_LISTING, user1.getUsername(), user2.getUsername(), commonUsers.getNumCommonProjects());
        }
    }

    /**
     * Interprets the <code>help</code> command - prints out all the available commands
     */
    private static void interpretHelp() {
        System.out.println(HELP_MESSAGE);
    }

    /**
     * Interprets the <code>exit</code> command - prints out an exiting message
     */
    private static void interpretExit() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Reads a command from user input
     *
     * @param in - input reader
     * @return a command of type <code>Command</code>
     */
    private static Command readCommand(Scanner in) {
        String cmd = in.next().toUpperCase().trim();

        try {
            return Command.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    // Commands
    private enum Command {
        REGISTER, USERS, CREATE, PROJECTS, TEAM, ARTEFACTS, PROJECT, REVISION,
        MANAGES, KEYWORD, CONFIDENTIALITY, WORKAHOLICS, COMMON, HELP, EXIT, UNKNOWN
    }
}