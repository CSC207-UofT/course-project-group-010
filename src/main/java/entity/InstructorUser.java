package entity;

import constants.ProgramConstants;
import interfaces.IUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO instructorUser isn't really fleshed out, consider deleteing or adding methods similar to those in StudentUser
public class InstructorUser implements Serializable, IUser {

    public static int MAXIMUM_DISPLAY_LENGTH = 25;
    private final String ID;
    private final String displayName;
    private final Map<String, String> otherData;

    /**
     * An InstructorUser object. The parameters are currently final as no changes need to be made; future developers
     * may remove the finality of the parameter if need be.
     *
     * @param ID The ID of this InstructorUser.
     * @param displayName The display name of this InstructorUser.
     * @param otherData A map containing information about this InstructorUser; current info includes
     *                  their position (TA or Prof) and more info may be added in the future by other developers.
     */
    public InstructorUser(String displayName, String ID, Map<String, String> otherData) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
    }

    /**
     * @return a string representation of this  InstructorUser.
     */
    @Override
    public String toString() {
        return this.getDisplayName() + "\n" + this.getID();
    }

    /**
     * @return the ID of this  InstructorUser.
     */
    @Override
    public String getID() {
        return this.ID;
    }

    /**
     * @return the DisplayName of this  InstructorUser.
     */
    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * @return the otherData of this  InstructorUser.
     */
    @Override
    public Map<String, String> getOtherData() {
        return otherData;
    }

    /**
     * @return this InstructorUser's teaching position, i.e whether they are a Professor or a TA.
     */
    public String getPosition() {
        return this.getOtherData().get("position");
    }

    /**
     * Set this InstructorUser's teaching position, i.e whether they are a Professor or a TA.
     */
    public void setPosition(String p) {
        this.getOtherData().put("position", p);
    }

    /**
     * @return A hashmap of data containing all information about this InstructorUser, used for displaying information
     * in commands.
     */
    @Override
    public HashMap<String, Object> getData() {
        HashMap<String, Object> result = new HashMap<>();
        // Input all general information of user
        result.put("ID", ID);
        result.put("displayName", displayName);
        result.put("position", getPosition());
        // result.put("currentlyTeaching", getCurrentlyTeaching());
        return result;
    }


}

