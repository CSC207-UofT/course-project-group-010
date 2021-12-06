package entity;

import interfaces.IUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class InstructorUser implements Serializable, IUser {

    private final String ID; //ID
    private final String displayName; //Username
    private final Map<String, String> otherData;

    /**
     * Initializes a new instructorUser
     * @param displayName display name
     * @param ID id
     * @param otherData map of other relevant data, eg. position
     */
    public InstructorUser(String displayName, String ID, Map<String, String> otherData) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
    }

    /**
     * Overloaded constructor, creates a blank otherData dictionary
     * @param displayName name
     * @param ID id
     */
    public InstructorUser(String displayName, String ID) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = new HashMap<>();
        setPosition("N/A");
    }

    /**
     * @return a string representation of this InstructorUser in the following format: "Display_Name ID".
     */
    @Override
    public String toString() {
        return this.getDisplayName() + "\n" + this.getID();
    }

    /**
     * @return a this InstructorUser's ID.
     */
    @Override
    public String getID() {
        return this.ID;
    }

    /**
     * @return a this InstructorUser's DisplayName.
     */
    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * @return a this InstructorUser's position.
     */
    public String getPosition() {
        return this.getOtherData().get("position");
    }

    /**
     * @return Get this InstructorUser's OtherData.
     */
    @Override
    public Map<String, String> getOtherData() {
        return otherData;
    }

    /**
     * Gets data relevant to this user, in a map format.
     * @return the data
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

    /**
     * Set a this InstructorUser's position.
     */
    public void setPosition(String p) {
        this.getOtherData().put("position", p);
    }
}

