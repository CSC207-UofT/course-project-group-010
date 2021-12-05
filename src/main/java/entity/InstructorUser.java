package entity;

import interfaces.IUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO instructorUser isn't really fleshed out, consider deleteing or adding methods similar to those in StudentUser
public class InstructorUser implements Serializable, IUser {

    public static int MAXIMUM_DISPLAY_LENGTH = 25;
    private final String ID; //ID
    private final String displayName; //Username
    private final Map<String, String> otherData;
    // position Options include "Prof" and "TA".
    private List<Course> currentlyTeaching;

    //Constructors
    public InstructorUser(String displayName, String ID, Map<String, String> otherData) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
    }

    // TODO this constructor is not in use except in tests, we can delete
    public InstructorUser(String displayName, String ID) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = new HashMap<>();
        setPosition("N/A");
    }

    @Override
    public String toString() {
        return this.getDisplayName() + "\n" + this.getID();
    }

    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    public String getPosition() {
        return this.getOtherData().get("position");
    }

    public void setPosition(String p) {
        this.getOtherData().put("position", p);
    }

    @Override
    public Map<String, String> getOtherData() {
        return otherData;
    }

    // TODO unused, delete
//    public List<Course> getCurrentlyTeaching() {
//        return this.currentlyTeaching;
//    }

    //Setters

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

