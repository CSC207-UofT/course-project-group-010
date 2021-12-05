package interfaces;

import entity.Course;

import java.util.List;

public interface Builder {

    void buildCourse(List<String> course);

    void setCourse(Course course);

    void setInstructors(List<String> instructors);

    void reset();


}
