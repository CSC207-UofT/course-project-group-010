package entity;

import java.io.Serializable;

public class Course implements Serializable {

    String name;
    String code;
    String description;
    String DEFAULT = "There is currently no description available for this course";

    /**
     * A Course object containing information pertaining to the course. The description of the course is DEFAULT
     * by default on creation, and may be set later.
     *
     * @param name The name of the Course; i.e Introduction to Computer Science.
     * @param code The Course code; i.e CSC110.
     */
    public Course(String name, String code) {
        this.name = name;
        this.code = code;
        this.description = DEFAULT;
    }

    /**
     * @return a string representation of the course.
     */
    public String toString() {
        return this.name + "\n" + this.code + "\n" + this.description;
    }

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

    /**
     * Set this course's description.
     */
    public void setDescription(String s) {
        this.description = s;
    }

    /**
     * Set this course's name.
     */
    public void setName(String s) {this.name = s;}


}