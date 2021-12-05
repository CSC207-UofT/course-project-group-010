package interfaces;

import entity.Course;

import java.util.List;

/**
 * A course builder interface, that builds the related course and instructors.
 */
public interface Builder {

    void buildCourse(List<String> course);

    void setCourse(Course course);

    void setInstructors(List<String> instructors);

    void reset();


}
