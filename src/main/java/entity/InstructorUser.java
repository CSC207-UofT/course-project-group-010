package entity;

import Interface.IReviewer;
import Interface.IUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstructorUser implements Serializable, IReviewer, IUser {

    public static int MAXIMUM_DISPLAY_LENGTH = 25;
    private final String ID; //ID
    private String displayName; //Username
    private Map<String, String> otherData;
    private int reviewCount; //Number of reviews left by this user
    private HashMap<Integer, List<Course>> courses;
    // position Options include "Prof" and "TA".
    private List<Course> currentlyTeaching;

    //Constructors
    public InstructorUser(String displayName, String ID, Map<String, String> otherData) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
        this.reviewCount = 0;
    }

    public InstructorUser(String displayName, String ID) {
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

    @Override
    public void setDisplayName(String s) {
        if (s.length() < MAXIMUM_DISPLAY_LENGTH) {
            this.displayName = s;
        }
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

    //Setters

    public void setCurrentlyTeaching(List<Course> t) {
        this.currentlyTeaching = t;
    }

    @Override
    public int getReviewCount() {
        return reviewCount;
    }

    @Override
    public void setReviewCount(int count) {
        if (count >= 0) {
            this.reviewCount = count;
        }
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
        result.put("ID", ID);
        result.put("displayName", displayName);
        result.put("position", getPosition());
        result.put("currentlyTeaching", getCurrentlyTeaching());
        result.put("courses", courses);
        return result;
    }

    @Override
    public void incrementReviewCount() {
        this.reviewCount++;
    }


}

