package usecase;

import Interface.IDBSaveable;
import Interface.IHasPermission;
import Interface.IReadModifiable;
import Interface.IUser;
import constants.PermissionLevel;
import constants.UserType;
import entity.Course;
import entity.InstructorUser;
import entity.StudentUser;
import entity.UserFactory;

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
    private UserType type;
    private PermissionLevel permissionLevel;
    private Map<PermissionLevel, List<String>> authDict;

    /**
     * Initializes a new UserManager.
     * UserManager works on use cases for a user object,
     * so it will initialize with a student object or instructor object
     */
    public UserManager(UserType type, String displayName, String ID, Map<String, String> otherData) throws Exception {

        UserFactory userFactory = new UserFactory();
        user = userFactory.getUser(type, displayName, ID, otherData);
        if (type == UserType.INSTRUCTOR)
            this.permissionLevel = PermissionLevel.INSTRUCTOR;
        else {
            this.permissionLevel = PermissionLevel.STUDENT;
        }
        this.authDict = getDefaultAuthDict();
    }


    public UserManager(UserType type, String displayName, String ID) throws Exception {

        UserFactory userFactory = new UserFactory();
        user = userFactory.getUser(type, displayName, ID);
        if (type == UserType.INSTRUCTOR)
            this.permissionLevel = PermissionLevel.INSTRUCTOR;
        else {
            this.permissionLevel = PermissionLevel.STUDENT;
        }
        this.authDict = getDefaultAuthDict();
    }


    // Modify User information

    /**
     * Increment review count of a given user.
     *
     * @param user instance of a user.
     */
    public void userIncrementReviewCount(IUser user, int count) {
        user.incrementReviewCount();
    }

    /**
     * Set display name of a given user.
     *
     * @param user instance of a user.
     * @param s    display name.
     */
    public void userSetDisplayName(IUser user, String s) {
        user.setDisplayName(s);
    }


    /**
     * Set courses of a given user.
     *
     * @param c List of courses.
     */

    public void setCourses(IUser user, HashMap<Integer, List<Course>> c) {
        user.setCourses(c);
    }

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
    public PermissionLevel getPermissionLevel() {
        return this.permissionLevel;
    }

    @Override
    public Map<PermissionLevel, List<String>> getAuthDict() {
        return this.authDict;
    }

    private Map<PermissionLevel, List<String>> getDefaultAuthDict() {
        Map<PermissionLevel, List<String>> permDict = new HashMap<>();
        // for now, everyone can make a new user
        List<String> studentPermissions = Arrays.asList("print", "checkout", "newuser");
        List<String> instructorPermissions = List.of("all");
        permDict.put(PermissionLevel.STUDENT, studentPermissions);
        permDict.put(PermissionLevel.INSTRUCTOR, instructorPermissions);
        return permDict;
    }


    // Modify Student User information.

    /**
     * Set program detail of a given user.
     *
     * @param studentUser instance of a StudentUser.
     * @param s           string of program detail.
     */
    public void studentSetProgramDetail(StudentUser studentUser, String s) {
        studentUser.setProgramDetail(s);
    }

    // Modify InstructorUser information.

    /**
     * Set currently teaching courses of a given InstructorUser.
     *
     * @param instructorUser instance of an InstructorUser.
     * @param t              list of courses.
     */
    public void instructorSetCurrentlyTeaching(InstructorUser instructorUser, List<Course> t) {
        instructorUser.setCurrentlyTeaching(t);
    }

    /**
     * Set position of a give InstructorUser.
     *
     * @param instructorUser instance of an InstructorUser.
     * @param p              string of position.
     */
    public void instructorSetPosition(InstructorUser instructorUser, String p) {
        instructorUser.setPosition(p);
    }
}





