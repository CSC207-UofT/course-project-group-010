package Entity;

import java.util.Map;

public abstract class User {
    private String displayName; //Username
    private String ID; //UTemail
    private Map<String, String> otherData;
    private int reviewCount; //Number of reviews left by this user

    // Student Permission level : 0
    // TA Permissionlevel : 1
    // Instructor Permission level : 2


    //Constructors
    public User(String displayName, String ID, Map<String, String> otherData) {

        this.displayName = displayName;
        this.ID = ID;
        this.otherData = otherData;
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

    //Setters
    public void setreviewCount(int count){
        if(count >= 0){
            this.reviewCount = count;
        }
    }

    public void setDisplayName(String s){
        if (s.length() < 25){
            this.displayName = s;
        }
    }
    public void setOtherData(Map<String, String> otherData) {
        this.otherData = otherData;
    }

}
