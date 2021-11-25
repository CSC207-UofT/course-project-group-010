package entity;

import Interface.IReviewer;
import Interface.IUser;
import constants.UserType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstructorUser implements Serializable, IReviewer, IUser {

    public static int MAXIMUM_DISPLAY_LENGTH = 25;
    private final String ID; //ID
    private IUser user;
    private UserType type;
    private String displayName; //Username
    private Map<String, String> otherData;
    private int reviewCount; //Number of reviews left by this user
    private HashMap<Integer, List<Course>> courses;
    // position Options include "Prof" and "TA".
    private List<Course> currentlyTeaching;

    //Constructors
    public InstructorUser(String displayName, String ID, Map<String, String> otherData) {
        this.type = UserType.INSTRUCTOR;
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
        this.reviewCount = 0;
    }

    public InstructorUser(String displayName, String ID) {
        this.type = UserType.INSTRUCTOR;
        this.displayName = displayName;
        this.ID = ID;
        this.reviewCount = 0;
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

    @Override
    public void setOtherData(Map<String, String> otherData) {
        this.otherData = otherData;
    }

    public List<Course> getCurrentlyTeaching() {
        return this.currentlyTeaching;
    }

    public void setCurrentlyTeaching(List<Course> t) {
        this.currentlyTeaching = t;
    }

    //Setters

    @Override
    public int getReviewCount() {
        return reviewCount;
    }

    @Override
    public HashMap<Integer, List<Course>> getCourses() {
        return this.courses;
    }

    @Override
    public void setCourses(HashMap<Integer, List<Course>> c) {
        this.courses = c;
    }

    @Override
    public HashMap<String, Object> getData() {
        HashMap<String, Object> result = new HashMap<>();
        // Input all general information of user
        result.put("ID", user.getID());
        result.put("displayName", user.getDisplayName());
        result.put("position", ((InstructorUser) user).getPosition());
        result.put("currentlyTeaching", ((InstructorUser) user).getCurrentlyTeaching());
        result.put("courses", user.getCourses());
        return result;
    }

    @Override
    public void incrementReviewCount() {
        this.reviewCount++;
    }

    @Override
    public void setreviewCount(int count) {
        if (count >= 0) {
            this.reviewCount = count;
        }
    }

    @Override
    public void setDisplayName(String s) {
        if (s.length() < MAXIMUM_DISPLAY_LENGTH) {
            this.displayName = s;
        }
    }


}

