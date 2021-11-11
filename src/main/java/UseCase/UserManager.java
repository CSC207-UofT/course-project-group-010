package UseCase;

import Constants.PermissionLevel;
import Constants.UserType;
import Entity.Course;
import Entity.InstructorUser;
import Entity.StudentUser;
import Entity.User;
import Interface.IDBSaveable;
import Interface.IGettable;
import Interface.IHasPermission;
import Interface.IReadModifiable;

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
public class UserManager implements IGettable, IDBSaveable, IHasPermission, IReadModifiable, Serializable {
    private User user;
    private PermissionLevel permissionLevel;
    private Map<PermissionLevel, List<String>> authDict;

    /**
     * Initializes a new UserManager.
     * UserManager works on use cases for a user object,
     * so it will initialize with a student object
     */
    public UserManager(UserType type, String displayName, String ID, Map<String, String> otherData) throws Exception {
        if (type == UserType.INSTRUCTOR) {
            user = createInstructorUser(displayName, ID, otherData);
            this.permissionLevel = PermissionLevel.INSTRUCTOR;
        } else if (type == UserType.STUDENT) {
            user = createStudentUser(displayName, ID, otherData);
            this.permissionLevel = PermissionLevel.STUDENT;
        } else {
            throw new Exception("Couldn't initialize user");
        }
        this.authDict = getDefaultAuthDict();
        // When amount of data increases, would be good if otherData was always just a
        // map with all the other data
        // then no matter what I can call create[type]User(displayName, ID, otherData);
        // and it would mean the same thing.
    }

    public UserManager(UserType type, String displayName, String ID) throws Exception {
        if (type == UserType.INSTRUCTOR) {
            user = new InstructorUser(displayName, ID);
            this.permissionLevel = PermissionLevel.INSTRUCTOR;
        } else if (type == UserType.STUDENT) {
            user = new StudentUser(displayName, ID);
            this.permissionLevel = PermissionLevel.STUDENT;
        } else {
            throw new Exception("Couldn't initialize user of type " + type);
        }
        this.authDict = getDefaultAuthDict();
    }


    // Constructs users.

    /**
     * Create an instance of StudentUser.
     *
     * @param displayName display name of StudentUser.
     * @param ID          id of user.
     * @param otherData   other data.
     * @return created user.
     */
    public StudentUser createStudentUser(String displayName, String ID, Map<String, String> otherData) {
        return new StudentUser(displayName, ID, otherData);
    }

    /**
     * Create an instance of InstructorUser.
     *
     * @param displayName display name of InstructorUser.
     * @param ID          id of user.
     * @param otherData   other data.
     * @return
     */
    public InstructorUser createInstructorUser(String displayName, String ID, Map<String, String> otherData) {
        return new InstructorUser(displayName, ID, otherData);
    }

    // Modify User information

    /** Set permission level of a given user.
     *
     * @param user instance of a user.
     * @param level permission level.
     */
//    public void userSetPermissionLevel(User user, int level){
//        user.setpermissionLevel(level);
//    }

    /**
     * Increment review count of a given user.
     *
     * @param user instance of a user.
     */
    public void userIncrementReviewCount(User user, int count) {
        user.incrementReviewCount();
    }

    /**
     * Set display name of a given user.
     *
     * @param user instance of a user.
     * @param s    display name.
     */
    public void userSetDisplayName(User user, String s) {
        user.setDisplayName(s);
    }

    // Modify StudentUser information.

    /**
     * Set courses of a given user.
     *
     * @param studentUser instance of a StudentUser.
     * @param c           List of courses.
     */
    public void studentSetCourses(StudentUser studentUser, HashMap<Integer, List<Course>> c) {
        studentUser.setCourses(c);
    }

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

    /**
     * Set courses of a given InstructorUser
     *
     * @param instructorUser instance of an InstructorUser.
     * @param c              list of coureses.
     */
    public void instructorSetCourses(InstructorUser instructorUser,
                                     HashMap<Integer, List<Course>> c) {
        instructorUser.setCourses(c);
    }


    /**
     * Return the information about the user.
     *
     * @return HashMap of <key, information> of user.
     * @throws IllegalArgumentException
     */
    @Override
    public HashMap<String, Object> getData() throws IllegalArgumentException {
        // Create a HashMap
        HashMap<String, Object> result = new HashMap<String, Object>();
        // Input all general information of user
        result.put("ID", user.getID());
        result.put("displayName", user.getdisplayName());

        // If user is a student, put program detail and courses into the HashMap.
        if (user instanceof StudentUser) {
            result.put("programDetail", ((StudentUser) user).getProgramDetail());
            result.put("courses", ((StudentUser) user).getCourses());
        }
        // If user is an instructor, put position, currently teaching courses and courses
        // into HashMap
        else if (user instanceof InstructorUser) {
            result.put("position", ((InstructorUser) user).getPosition());
            result.put("currentlyTeaching", ((InstructorUser) user).getCurrentlyTeaching());
            result.put("courses", ((InstructorUser) user).getCourses());
        }
        // If user is not an instance any of them, throws exception.
        else {
            throw new IllegalArgumentException();
        }
        // Return the final result.
        return result;
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
    public User getUser() {
        return user;
    }

    @Override
    public PermissionLevel getPermissionLevel() {
        return this.permissionLevel;
    }

    private Map<PermissionLevel, List<String>> getDefaultAuthDict() {
        Map<PermissionLevel, List<String>> permDict = new HashMap<>();
        // for now, everyone can make a new user
        List<String> studentPermissions = Arrays.asList("print", "checkout", "newuser");
        List<String> instructorPermissions = Arrays.asList("all");
        permDict.put(PermissionLevel.STUDENT, studentPermissions);
        permDict.put(PermissionLevel.INSTRUCTOR, instructorPermissions);
        return permDict;
    }

    @Override
    public Map<PermissionLevel, List<String>> getAuthDict() {
        return this.authDict;
    }
}
