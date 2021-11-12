package Entity;

import Interface.IReviewer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class User implements Serializable, IReviewer {
    private String displayName; //Username
    private final String ID; //ID
    private Map<String, String> otherData;
    private int reviewCount; //Number of reviews left by this user


    //Constructors
    public User(String displayName, String ID, Map<String, String> otherData) {

        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
        this.reviewCount = 0;
    }

    public User(String displayName, String ID) {
        this.displayName = displayName;
        this.ID = ID;
        this.otherData = new HashMap<>();
        this.otherData.put("programDetail", "n/a");
        this.reviewCount = 0;
    }


    /**
     * @return string representation of this User; "Nima nima@gmail.com"
     */
    @Override
    public String toString() {
        return this.getdisplayName() + "\n" + this.getID();
    }


    //Getters
    public String getID() {
        return this.ID;
    }

    public String getdisplayName() {
        return this.displayName;
    }

    public Map<String, String> getOtherData() {
        return otherData;
    }

    public void setOtherData(Map<String, String> otherData) {
        this.otherData = otherData;
    }

    //Setters

//    public void setreviewCount(int count){
//        if(count >= 0){
//            this.reviewCount = count;
//        }
//    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void incrementReviewCount() {
        this.reviewCount++;
    }

    public void setDisplayName(String s) {
        if (s.length() < 25) {
            this.displayName = s;
        }
    }

}
