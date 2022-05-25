package versionControlSystem;

import versionControlSystem.project.Project;
import versionControlSystem.exceptions.*;
import versionControlSystem.user.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;

/**
 * <code>VersionControlSystem</code> object. Holds the information on all <code>Project</code>s and all
 * <code>User</code>s and computes the logic behind commands on a system level.
 *
 * @author Lucas Girotto / Pedro Afonso
 */
public interface VersionControlSystem {
    /**
     * Registers a new <code>User</code> to the <code>VersionControlSystem</code>
     *
     * @param jobPosition - new <code>User</code>' job position
     * @param username - new <code>User</code>' username
     * @param managerUsername - this <code>Developer</code>'s <code>Manager</code>
     * @param clearanceLevel - new <code>User</code>' clearance level
     *
     * @throws UnknownJobPositionException - if the <code>jobPosition</code> isn't
     *                                       <code>manager</code> or <code>software developer</code>
     * @throws UserNameAlreadyExistsException - if the <code>username</code> is taken
     * @throws ManagerUsernameInvalidException - if <code>managerUsername</code> doesn't exist or doesn't
     *                                           belong to a <code>Manager</code>
     */
    void registerUser(String jobPosition, String username, String managerUsername, int clearanceLevel)
            throws UnknownJobPositionException, UserNameAlreadyExistsException, ManagerUsernameInvalidException;

    /**
     * Lists out all <code>User</code>'s registered in the <code>VersionControlSystem</code> by insertion order.
     * <code>Manager</code>'s and <code>Developer</code>'s are listed in different format.
     *
     * <code>Manager</code> -  (manager "userName" [number of developers, projects as manager, projects as members])
     * <code>Developer</code> - (developer username is managed by manager [number of projects])
     *
     * @return a <code>User Iterator</code> that iterates through the system's <code>User</code>'s
     *         by insertion order
     */
    Iterator<User> listAllUsers();


    /**
     * Creates a new <code>InHouseProject</code>
     *
     * @param managerUsername - new <code>Project</code>'s <code>Manager</code> username
     * @param projectType - new <code>Project</code>'s type
     * @param projectName - new <code>Project</code>'s name
     * @param keywords - new <code>Project</code>'s keywords, if <code>InHouseProject</code>
     * @param companyName - new <code>Project</code>s company name, if <code>OutsourcedProject</code>
     * @param confidentialityLevel - new <code>Project</code>'s confidentiality level
     *
     * @throws UnknownProjectTypeException - if the <code>projectType</code> isn't
     *                                       <code>inhouse</code> or <code>outsourced</code>
     * @throws ManagerUsernameInvalidException - if <code>managerUsername</code> doesn't exist or doesn't
     *                                           belong to a <code>Manager</code>
     * @throws ProjectNameAlreadyExistsException - if <code>projectName</code> already exists
     * @throws ConfidentialityLevelHigherThanManagerException - if <code>confidentialityLevel</code>
     *                                                          is bigger than the <code>Manager</code>'s,
     *                                                          with <code>managerUsername</code>,
     *                                                          <code>clearanceLevel</code>
     */
    void createProject(String managerUsername, String projectType, String projectName,
                       String[] keywords, String companyName, int confidentialityLevel)
            throws UnknownProjectTypeException, ManagerUsernameInvalidException,
                   ProjectNameAlreadyExistsException, ConfidentialityLevelHigherThanManagerException;

    /**
     * Lists all <code>Project</code>'s registered in the <code>VersionControlSystem</code> by
     * insertion order.
     * <code>InHouse Project</code>'s and <code>Outsourced Project</code>'s are listed in different formats:
     *
     * <code>InHouse Project</code>: in-house "project name" is managed by "username"
     *                              [level, number of members, number of artefacts, number of revisions]
     *
     * <code>Outsourced Project</code>: outsourced "project name" is managed by "username" and developed by "company"
     *
     * @return a <code>Project Iterator</code> that iterates through all the <code>Project</code>'s
     *         registered in the system
     */
    Iterator<Project> listAllProjects();


    /**
     * Adds a team member to an existing <code>InHouse Project</code>
     *
     * @param projectName - <code>Project</code>'s name for this team
     * @param memberName - <code>Developer</code> username
     *
     * @throws UserDoesntExistException - if <code>User</code> doesn't exist
     * @throws DeveloperAlreadyMemberException - if <code>User</code> is already a member of <code>Project</code>
     *                                           with <code>projectName</code>
     * @throws InsufficientClearanceLevelException - if <code>User</code> <code>clearanceLevel</code> is lower
     *                                               than the <code>Project</code>s <code>confidentialityLevel</code>
     */
    void addTeamMember(String projectName, String memberName)
            throws UserDoesntExistException, DeveloperAlreadyMemberException, InsufficientClearanceLevelException;

    /**
     * Checks if <code>User</code> with <code>managerUsername</code> exists and is the <code>ProjectManager</code>
     * of <code>Project</code> and if <code>Project</code> with <code>projectName</code> exists.
     *
     * @param managerUsername - <code>username</code> to check if its <code>ProjectManager</code>
     * @param projectName - <code>projectName</code> to check if exists
     *
     * @throws ManagerUsernameInvalidException - if <code>managerUsername</code> doesn't exist or doesn't
     *                                           belong to a <code>Manager</code>
     * @throws ProjectNameDoesntExistException - if <code>projectName</code> doesn't exist
     * @throws ProjectNotManagedByManagerException - if <code>Manager</code> with <code>managerUsername</code>
     *                                               doesn't manage <code>Project</code> with <code>projectName</code>
     */
    void checkManagerProject(String managerUsername, String projectName)
            throws ManagerUsernameInvalidException, ProjectNameDoesntExistException,
            ProjectNotManagedByManagerException;


    /**
     * Checks if both <code>Developer</code> and <code>Project</code> exist and if they are associated
     * 
     * @param developerUsername - <code>Developer</code>'s supposed username
     * @param projectName - <code>Project</code>'s supposed name
     * 
     * @throws UserNameDoesntExistException - if <code>developerUsername</code> is not associated to any <code>User</code>
     * @throws ProjectNameDoesntExistException - if <code>projectName</code> is not associated to any <code>InHouseProject</code>
     * @throws DeveloperNotMemberException - if <code>User</code> does not belong to <code>Project</code>
     */
    void checkDeveloperProject(String developerUsername, String projectName)
            throws UserNameDoesntExistException, ProjectNameDoesntExistException, DeveloperNotMemberException;
    
    /**
     * Adds an <code>Artefact</code> to an <code>InHouseProject</code>
     *
     * @param authorUsername - author of the <code>Artefact</code>
     * @param projectName - <code>Project</code>'s name
     * @param artefactName - <code>Artefact</code>'s name
     * @param artefactDate - <code>Artefact</code>'s addition date
     * @param confidentiality - <code>Artefact</code>'s confidentiality level
     * @param description - <code>Artefact</code>'s description
     * 
     * @throws ArtefactAlreadyInProjectException - if <code>artefactName</code> already exists in <code>Project</code>
     * @throws ArtefactExceedsConfidentialityException - if <code>confidentiality</code> is greater than the <code>Project</code>'s confidentiality level
     */
    void addArtefact(String authorUsername, String projectName, String artefactName,
                     LocalDate artefactDate, int confidentiality, String description)
    		throws ArtefactAlreadyInProjectException, ArtefactExceedsConfidentialityException;

    /**
     * Lists the information regarding a single <code>Project</code>, i.e., the
     * project name, manager, developers (by insertion order),
     * artefacts (ordered by date, last revision number and name) and revisions.
     *
     * project "name" [project level] managed by "manager"" [manager level]:
     * member "username" [member level]
     * artefact "name" [artefact level]: "artefact description"
     * revision number "username" "date" "comment"
     *
     * @param projectName - <code>Project</code>'s name
     *
     * @return a copy of <code>Project</code> with <code>projectName</code>
     *
     * @throws ProjectNameDoesntExistException - if <code>projectName</code> is not associated to any <code>InHouseProject</code>
     * @throws ProjectIsOutsourcedException - if <code>Project</code> with <code>projectName</code> is an <code>OutsourcedProject</code>
     */
    Iterator<Project> listProjectInfo(String projectName)
            throws ProjectNameDoesntExistException, ProjectIsOutsourcedException;

    /**
     * Reviews an existing <code>Artefact</code> from an existing <code>Project</code>
     *
     * @param username - author of <code>Revision</code>
     * @param projectName - <code>Project</code>'s name this <code>Revision</code> belongs to
     * @param artefactName - <code>Artefact</code>'s name this <code>Revision</code> belongs to
     * @param date - date of new <code>Revision</code>
     * @param comment - new <code>Revision</code>'s comment
     *
     * @throws UserNameDoesntExistException - if <code>developerUsername</code> doesn't exist
     * @throws ProjectNameDoesntExistException - if <code>projectName</code> doesn't exist
     * @throws DeveloperNotMemberException - if <code>Developer</code> with <code>developerUsername</code>
     *                                       isn't a member of <code>Project</code> with <code>projectName</code>
     * @throws ArtefactNotInProjectException - if the <code>Artefact</code> with <code>artefactName</code> is not in
     *                                        in <code>Project</code> with <code>projectName</code>
     */
    int reviewArtefact(String username, String projectName, String artefactName, LocalDate date, String comment)
            throws UserNameDoesntExistException, ProjectNameDoesntExistException, DeveloperNotMemberException,
                   ArtefactNotInProjectException;


    /**
     * Presents the information of all <code>Developer</code>'s
     * managed by <code>Manager</code> with <code>managerUsername</code>, i.e.,
     * list of <code>Developer</code>'s (by alphabetical order), followed by the <code>Revision</code>'s
     * they made (ordered by date, revision number and project name).
     *
     * Manager "username":
     * "developerUsername"
     * project "name", artefact "name", revision number, "date", "comment"
     *
     * @param managerUsername - <code>Manager</code>'s username to list <code>Developer</code>'s from
     *
     * @return an <code>User Iterator</code> that iterates through a <code>Manager</code>'s <code>Developer</code>s
     */
    Iterator<User> listDevelopersInfo(String managerUsername);


    /**
     * Presents all <code>Project</code>s that have <code>keyword</code>.
     * <code>InHouse Project</code>s appear first, sorted by most recent <code>Revision</code>,
     * then by number of updates and by <code>Project</code> name. Then, <code>Outsourced Project</code>s
     * appear sorted by <code>Project</code> name.
     *
     * All projects with keyword "keyword":
     * in-house "project name" is managed by "username" [level, members, artefacts, revisions, last updated]
     * outsourced "project name" is managed by "username" and developed by "company"
     *
     * @param keyword - keyword to filter <code>Project</code>s to
     *
     * @return a filtered <code>Project Iterator</code> that iterates through all <code>Project</code>s that
     *         have <code>keyword</code>
     */
    Iterator<Project> listProjectsByKeyword(String keyword);

    /**
     * Presents all <code>InHouse Project</code>s that have a <code>confidentialityLevel</code>
     * between <code>lowerLimit</code> and <code>upperLimit</code>, sorted by <code>projectName</code>
     *
     * All projects within levels "lower level" and "upper level":
     * "project name" [level] is managed by "username" and has keywords "list of keywords"
     *
     * @param lowerLimit - lower confidentiality limit
     * @param upperLimit - upper confidentiality limit
     *
     * @return a <code>InHouse Project Iterator</code> that iterates through all <code>Project</code>s that
     *         have a <code>confidentialityLevel</code> between <code>lowerLimit</code> and <code>upperLimit</code>
     */
    Iterator<Project> listProjectsByConfidentiality(int lowerLimit, int upperLimit);


    /**
     * Presents the top three workaholics - the <code>User</code>s that have made more <code>Revision</code>s to <code>Artefacts</code>.
     * In case of ties, the <code>User</code>s that participate in more <code>Project</code>s as
     * <code>Manager</code>s or <code>Developer</code>s come out on top. Next, ties are broken using the
     * date of the last <code>Revision</code>. Lastly, if all else fails, ties are broken by using
     * alphabetical order of the <code>User</code>'s <code>username</code>.
     *
     * "username": number of updates updates, number of projects projects, last update on "date".
     *
     * @return a <code>Workaholics Class</code> with the top three workaholics
     */
    Workaholics listWorkaholics();

    /**
     * Presents the two <code>User</code>s that have the most <code>Project</code>s in common.
     * In case of a tie, the <code>User</code>s presented are the ones that most recently reviewed <code>Project</code>s,
     * ordered in alphabetical order. In case of another tie, select using alphabetical order.
     *
     * @return a <code>Commonality Class</code> with the top two common <code>User</code>s and the
     *         number of <code>Project</code>s in common
     */
    Commonality listTopCommonUsers();
}