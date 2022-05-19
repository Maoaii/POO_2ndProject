package versionControlSystem;

import project.Project;
import user.User;
import versionControlSystem.expections.*;

import java.util.Date;
import java.util.Iterator;

public class VersionControlSystemClass implements VersionControlSystem {
    @Override
    public void registerManager(String jobPosition, String username, int clearanceLevel) throws UnknownJobPositionException, UserNameAlreadyExistsException, ManagerUsernameInvalidException {

    }

    @Override
    public void registerDeveloper(String jobPosition, String username, String managerUsername, int clearanceLevel) throws UnknownJobPositionException, UserNameAlreadyExistsException {

    }

    @Override
    public Iterator<User> listAllUsers() {
        return null;
    }

    @Override
    public void createInHouseProject(String managerUsername, String projectType, String projectName, String[] keywords, int confidentialityLevel) throws UnknownProjectTypeException, ManagerUsernameInvalidException, ProjectNameAlreadyExistsException, ConfidentialityLevelHigherThanManagerException {

    }

    @Override
    public void createOutsourcedProject(String managerUsername, String projectType, String projectName, String[] keywords, String companyName) throws UnknownProjectTypeException, ManagerUsernameInvalidException, ProjectNameAlreadyExistsException {

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
    public void reviewArtefact(String username, String projectName, String artefactName, Date date, String comment) throws UserNameDoesntExistException, ProjectNameDoesntExistException, DeveloperNotMemberException, DeveloperLowerClearanceLevelException {

    }

    @Override
    public Iterator<User> listDevelopersInfo(String managerUsername) throws UserNameDoesntExistException {
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
    public Workaholics listWorkaholics() throws NoWorkaholicsException {
        return null;
    }

    @Override
    public Commonality listTopCommonUsers() throws NoCommonUsersException {
        return null;
    }
}
