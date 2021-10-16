package Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InstructorUser extends User {
    private HashMap<Integer, List<Course>> courses;
    private String position; //Options include "Prof" and "TA".
    private List<Course> currentlyTeaching;


    //Constructors
    public InstructorUser(String displayName, String ID, String position) {
        super(displayName, ID);
        this.position = position;
    }




    //Getters
    public String getPosition() {
        return this.position;
    }

    public List<Course> getCurrentlyTeaching() {
        return this.currentlyTeaching;
    }

    public HashMap<Integer, List<Course>> getCourses() {
        return this.courses;
    }




    //Setters
    public void setCurrentlyTeaching(List<Course> t) {
        this.currentlyTeaching = t;
    }

    public void setPosition(String p) {
        this.position = p;
    }

    public void setCourses(HashMap<Integer, List<Course>> c) {
        this.courses = c;
    }

}

