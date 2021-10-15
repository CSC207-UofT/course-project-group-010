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

        if (position.equals("TA")) {
            this.setpermissionLevel(1);

        } else if (position.equals("Prof")) {
            this.setpermissionLevel(2);
        }
    }

    //Getters

    String getPosition() {
        return this.position;
    }
    List<Course> getCurrentlyTeaching() {
        return this.currentlyTeaching;
    }

    HashMap<Integer, List<Course>> getCourses() {
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

