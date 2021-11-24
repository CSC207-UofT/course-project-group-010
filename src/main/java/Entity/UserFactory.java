package Entity;

import constants.UserType;
import Entity.InstructorUser;
import Entity.StudentUser;
import Interface.IUser;

import java.util.Map;

public class UserFactory {

    /**
     * use getUser method to get the user type and create a new user.
     * @param userType the type of user.
     * @param displayName display name of StudentUser.
     * @param ID          id of user.
     * @param otherData   other data.
     * @return created Student user or throw "couldn't initialize user" message.
     */

    //
    public IUser getUser(UserType userType, String displayName, String ID, Map<String, String> otherData) throws Exception {
        switch (userType) {
            case STUDENT:
                return new StudentUser(displayName, ID, otherData);
            case INSTRUCTOR:
                return new InstructorUser(displayName, ID, otherData);
            default:
                throw new Exception("Couldn't initialize user");
        }
    }

    public IUser getUser(UserType userType, String displayName, String ID) throws Exception {
        switch (userType) {
            case STUDENT:
                return new StudentUser(displayName, ID);
            case INSTRUCTOR:
                return new InstructorUser(displayName, ID);
            default:
                throw new Exception("Couldn't initialize user");
        }
    }
}
