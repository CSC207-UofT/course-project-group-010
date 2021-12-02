package usecase;

import constants.UserType;
import entity.Course;
import entity.InstructorUser;
import entity.StudentUser;
import entity.UserFactory;
import exceptions.ArgumentException;
import interfaces.IDBSaveable;
import interfaces.IHasPermission;
import interfaces.IReadModifiable;
import interfaces.IUser;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description : Responsible for reading information from user objects
 * Create the user object within UserManager
 * <p>
 * Responsibilities:
 * (initializes with a userData object)
 * Getters for student attributes
 * Give up its info to a presenter
 * Implement interfaces
 */
public class UserManager implements IDBSaveable, IHasPermission, IReadModifiable, Serializable {
    private IUser user;
    private UserType userType;
    private Map<UserType, List<String>> authDict;

    /**
     * Initializes a new UserManager.
     * UserManager works on use cases for a user object,
     * so it will initialize with a student object or instructor object
     */
    public UserManager(UserType type, String displayName, String ID, Map<String, String> otherData) throws ArgumentException {

        UserFactory userFactory = new UserFactory();
        user = userFactory.getUser(type, displayName, ID, otherData);
        this.userType = type;
        this.authDict = getDefaultAuthDict();
    }


    /**
     * Initializes a new UserManager without other data inputted.
     * @param type
     * @param displayName
     * @param ID
     * @throws Exception
     */
    // Deprecated, we will return otherData mapping to NA if the user doesn't enter anything.
//    public UserManager(UserType type, String displayName, String ID) throws Exception {
//
//        UserFactory userFactory = new UserFactory();
//        user = userFactory.getUser(type, displayName, ID);
//        this.userType = type;
//        this.authDict = getDefaultAuthDict();
//    }


    // Modify User information

    // METHOD NOT USED
//    /**
//     * Increment review count of a given user.
//     *
//     * @param user instance of a user.
//     */
//    public void userIncrementReviewCount(IUser user) {
//        user.incrementReviewCount();
//    }

//    /**
//     * Set display name of a given user.
//     *
//     * @param user instance of a user.
//     * @param s    display name.
//     */
//    public void userSetDisplayName(IUser user, String s) {
//        user.setDisplayName(s);
//    }
//
//
//    /**
//     * Set courses of a given user.
//     *
//     * @param c List of courses.
//     */
//
//    public void setCourses(IUser user, HashMap<Integer, List<Course>> c) {
//        user.setCourses(c);
//    }

    /**
     * Return the information about the user.
     *
     * @return HashMap of <key, information> of user.
     */
    @Override
    public HashMap<String, Object> getData() {
        return user.getData();
    }

    /**
     * gets id
     *
     * @return the id
     */
    @Override
    public String getID() {
        return user.getID();
    }

    /**
     * getter for the user.
     */
    public IUser getUser() {
        return user;
    }

    @Override
    public UserType getPermissionLevel() {
        return this.userType;
    }

    @Override
    public Map<UserType, List<String>> getAuthDict() {
        return this.authDict;
    }

    private Map<UserType, List<String>> getDefaultAuthDict() {
        Map<UserType, List<String>> permDict = new HashMap<>();
        // for now, everyone can make a new user
        List<String> studentPermissions = Arrays.asList("print", "checkout", "newuser");
        List<String> instructorPermissions = List.of("print", "checkout", "newuser");
        permDict.put(UserType.STUDENT, studentPermissions);
        permDict.put(UserType.INSTRUCTOR, instructorPermissions);
        return permDict;
    }

// TODO I like the idea, but it's too late, and we don't have to do it.
//    // Modify Student User information.
//
//    // FIXME: dependency on StudentUser
//    /**
//     * Set program detail of a given user.
//     *
//     * @param studentUser instance of a StudentUser.
//     * @param s           string of program detail.
//     */
//    public void studentSetProgramDetail(StudentUser studentUser, String s) {
//        studentUser.setProgramDetail(s);
//    }
//
//    // Modify InstructorUser information.
//
//    // FIXME: dependency on InstructorUser
//    /**
//     * Set currently teaching courses of a given InstructorUser.
//     *
//     * @param instructorUser instance of an InstructorUser.
//     * @param t              list of courses.
//     */
//    public void instructorSetCurrentlyTeaching(InstructorUser instructorUser, List<Course> t) {
//        instructorUser.setCurrentlyTeaching(t);
//    }
//
//    // FIXME: dependency on InstructorUser
//    /**
//     * Set position of a give InstructorUser.
//     *
//     * @param instructorUser instance of an InstructorUser.
//     * @param p              string of position.
//     */
//    public void instructorSetPosition(InstructorUser instructorUser, String p) {
//        instructorUser.setPosition(p);
//    }
}





