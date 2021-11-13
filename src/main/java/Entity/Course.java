package Entity;

import java.util.List;

public class Course {
    String name; // Course name; i.e Introduction to Computer Science
    String code; // Course code; i.e CSC110
    String description; // Default description of the course that may be altered.


    //Constructor

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
    public void setDescription(String s) {
        this.description = s;
    }
    public void setName(String s) {this.name = name;}
    public void setCode(String c) {this.code = c;}


}
