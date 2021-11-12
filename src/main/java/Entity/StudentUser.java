package Entity;

import Interface.IReviewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentUser extends User {
    private HashMap<Integer, List<Course>> courses;

    // Permission level : 0

    /**
     * @param displayName   student user's display name.
     * @param ID            student user's ID.
     * @param otherData other Data. (Can make into a list later for more info if we want)
     */

    //Constructors
    public StudentUser(String displayName, String ID, Map<String, String> otherData) {
        super(displayName, ID, otherData);
        this.courses = new HashMap<Integer, List<Course>>();
    }

    public StudentUser(String displayName, String ID) {
        super(displayName, ID);
        this.courses = new HashMap<Integer, List<Course>>();
    }




    //Getters
    public String getProgramDetail() {

        return this.getOtherData().containsKey("programDetail") ? this.getOtherData().get("programDetail") :
                "";
    }

    public HashMap<Integer, List<Course>> getCourses(){
        return this.courses;
    }




    //Setters

    public void setCourses(HashMap<Integer, List<Course>> c) {
        this.courses = c;
    }

    public void setProgramDetail(String s) {
        this.getOtherData().put("programDetail", s);
    }
}



