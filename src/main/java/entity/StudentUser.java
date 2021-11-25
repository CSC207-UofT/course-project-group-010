package entity;

import Interface.IReviewer;
import Interface.IUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentUser implements Serializable, IReviewer, IUser {

    public static int MAXIMUM_DISPLAY_LENGTH = 25;
    private final String ID; //ID
    private String displayName; //Username
    private Map<String, String> otherData;
    private int reviewCount; //Number of reviews left by this user

    // Permission level : 0
    private HashMap<Integer, List<Course>> courses;

    //Constructors
    public StudentUser(String displayName, String ID, Map<String, String> otherData) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
        this.reviewCount = 0;
        this.courses = new HashMap<>();
    }

    public StudentUser(String displayName, String ID) {
        this.displayName = displayName;
        this.ID = ID;
        this.reviewCount = 0;
        this.courses = new HashMap<>();
    }

    @Override
    public String toString() {
        return this.getDisplayName() + "\n" + this.getID();
    }

    //Getters
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

    @Override
    public Map<String, String> getOtherData() {
        return otherData;
    }

    @Override
    public void setOtherData(Map<String, String> otherData) {
        this.otherData = otherData;
    }

    @Override
    public int getReviewCount() {
        return reviewCount;
    }

    //Setters

    @Override
    public void setReviewCount(int count) {
        if (count >= 0) {
            this.reviewCount = count;
        }
    }

    public String getProgramDetail() {

        return this.getOtherData().get("programDetail");
    }

    public void setProgramDetail(String s) {
        this.getOtherData().put("programDetail", s);
    }

    @Override
    public HashMap<String, Object> getData() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ID", ID);
        result.put("displayName", displayName);
        result.put("programDetail", getProgramDetail());
        result.put("courses", courses);
        return result;
    }

    @Override
    public void incrementReviewCount() {
        this.reviewCount++;
    }

    @Override
    public HashMap<Integer, List<Course>> getCourses() {
        return this.courses;
    }

    @Override
    public void setCourses(HashMap<Integer, List<Course>> c) {
        this.courses = c;
    }
}



