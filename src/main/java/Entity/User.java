package Entity;

public abstract class User {
    private String displayName; //Username
    private final String ID; //UTemail
    private int permissionLevel;
    private int reviewCount; //Number of reviews left by this user

    // Student Permission level : 0
    // TA Permissionlevel : 1
    // Instructor Permission level : 2


    //Constructors
    User(String displayName, String ID) {

        this.displayName = displayName;
        this.ID = ID;
    }


    /**
     * @return string representation of this User; "Nima nima@gmail.com"
     */
    @Override
    public String toString() {
        return this.getdisplayName() + "\n" + this.getID();
    }


    //Getters
    /**
     * @return user's ID.
     */
    String getID() {
        return this.ID;
    }


    /**
     * @return user's display name.
     */
    String getdisplayName() {
        return this.displayName;
    }



    //Setters
    public void setpermissionLevel(int level){
        if(level >= 0){
            this.permissionLevel = level;
        }
    }

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

}
