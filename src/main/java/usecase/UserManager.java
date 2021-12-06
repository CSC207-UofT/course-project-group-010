package usecase;

import constants.UserType;
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
    private final IUser user;
    private final UserType userType;
    private final Map<UserType, List<String>> authDict;

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
}





