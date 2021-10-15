package Entity;

import java.util.HashMap;
import java.util.List;

public class StudentUser extends User {
    private HashMap<Integer, List<Course>> courses;
    private String programDetail;
    // Permission level : 0

    /**
     * @param displayName   student user's display name.
     * @param ID            student user's ID.
     * @param programDetail Student's program name. (Can make into a list later for more info if we want)
     */

    //Constructors
    public StudentUser(String displayName, String ID, String programDetail) {
        super(displayName, ID);
        this.programDetail = programDetail;
        this.courses = new HashMap<Integer, List<Course>>();
        this.setpermissionLevel(0);
    }

    //Necessary Actions

    //Getters

    String getProgramDetail() {
        return this.programDetail;
    }

    HashMap<Integer, List<Course>> getCourses(){
        return this.courses;}

    //Setters

    public void setCourses(HashMap<Integer, List<Course>> c) {
        this.courses = c;
    }

    public void setProgramDetail(String s) {
        this.programDetail = s;
    }
}



