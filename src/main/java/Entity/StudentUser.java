package Entity;

import Interface.IReviewer;
import Interface.IUser;
import constants.UserType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentUser implements Serializable, IReviewer, IUser {

    private IUser user;
    private UserType type; //The type of User
    private String displayName; //Username
    private final String ID; //ID
    private Map<String, String> otherData;
    private int reviewCount; //Number of reviews left by this user
    private HashMap<Integer, List<Course>> courses;

    // Permission level : 0


    //Constructors
    public StudentUser(String displayName, String ID, Map<String, String> otherData) {
        this.type = UserType.STUDENT;
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
        this.reviewCount = 0;
        this.courses = new HashMap<>();
    }

    public StudentUser(String displayName, String ID) {
        this.type = UserType.STUDENT;
        this.displayName = displayName;
        this.ID = ID;
        this.reviewCount = 0;
        this.courses = new HashMap<>();
    }

    @Override
    public String toString() {
        return this.getdisplayName() + "\n" + this.getID();
    }

    //Getters
    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public String getdisplayName() {
        return this.displayName;
    }

    @Override
    public Map<String, String> getOtherData() {
        return otherData;
    }

    @Override
    public int getReviewCount() {
        return reviewCount;
    }

    public String getProgramDetail() {

        return this.getOtherData().get("programDetail");
    }

    @Override
    public HashMap<String, Object> getData() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ID", user.getID());
        result.put("displayName", user.getdisplayName());
        result.put("programDetail", ((StudentUser) user).getProgramDetail());
        result.put("courses", user.getCourses());
        return result;
    }

    @Override
    public void incrementReviewCount() {
        this.reviewCount++;
    }

    //Setters

    public void setProgramDetail(String s) {
        this.getOtherData().put("programDetail", s);
    }


    @Override
    public void setOtherData(Map<String, String> otherData) {
        this.otherData = otherData;
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
    public void setreviewCount(int count) {
        if (count >= 0) {
            this.reviewCount = count;
        }
    }

    public static int MAXIMUM_DISPLAY_LENGTH = 25;

    @Override
    public void setDisplayName(String s) {
        if (s.length() < MAXIMUM_DISPLAY_LENGTH) {
            this.displayName = s;
        }
    }
}



