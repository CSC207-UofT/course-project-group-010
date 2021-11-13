package Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstructorUser extends User {
    private HashMap<Integer, List<Course>> courses;
    // position Options include "Prof" and "TA".
    private List<Course> currentlyTeaching;


    //Constructors
    public InstructorUser(String displayName, String ID, Map<String, String> otherData) {
        super(displayName, ID, otherData);
    }

    public InstructorUser(String displayName, String ID) {
        super(displayName, ID);
    }


    //Getters
    public String getPosition() {
        return this.getOtherData().containsKey("position") ? this.getOtherData().get("position") : "";
    }

    public void setPosition(String p) {
        this.getOtherData().put("position", p);
    }

    public List<Course> getCurrentlyTeaching() {
        return this.currentlyTeaching;
    }

    //Setters
    public void setCurrentlyTeaching(List<Course> t) {
        this.currentlyTeaching = t;
    }

    public HashMap<Integer, List<Course>> getCourses() {
        return this.courses;
    }

    public void setCourses(HashMap<Integer, List<Course>> c) {
        this.courses = c;
    }

}

