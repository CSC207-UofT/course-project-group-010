package Entity;

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
    String getName() {
        return this.name;
    }

    /**
     * @return course code.
     */
    String getCode() {
        return this.code;
    }

    /**
     * @return course's description.
     */
    String getDescription() {
        return this.description;
    }

    //Setters
    public void setDescription(String s) {
        this.description = s;
    }


}
