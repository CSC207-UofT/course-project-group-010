package entity;

import java.io.Serializable;

public class Course implements Serializable {
    String name; // Course name; i.e Introduction to Computer Science
    final String code; // Course code; i.e CSC110
    String description; // Default description of the course that may be altered.

    /**
     * Initializes a new couse
     * @param name the display name of the course
     * @param code the course code eg. MAT137
     */
    public Course(String name, String code) {
        this.name = name;
        this.code = code;
        this.description = "There is currently no description available for this course";
    }

    //Methods

    /**
     * @return a string representation of the course.
     */
    @Override
    public String toString() {
        return this.name + "\n" + this.code + "\n" + this.description;
    }


    //Getters

    /**
     * @return course's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return course code.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @return course's description.
     */
    public String getDescription() {
        return this.description;
    }


    //Setters

    /**
     * Sets the course's description
     * @param s new description
     */
    public void setDescription(String s) {
        this.description = s;
    }

}
