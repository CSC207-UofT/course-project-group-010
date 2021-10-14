package Entity;

public class Course {
    String name; // Course name; i.e Introduction to Computer Science
    String code; // Course code; i.e CSC110
    String description; 

    public Course(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    String getInfo() {
        // TODO implement
        return this.name + "\n" + this.code + "\n" + this.description;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.code + "\n" + this.description;
    }

    String getName() {
        return this.name;
    }

    String getCode() {
        return this.code;
    }

    String getDescription() {
        return this.description;
    }
}
