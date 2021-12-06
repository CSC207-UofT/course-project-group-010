package interfaces;

import entity.Course;

import java.util.List;

public interface Builder {

    /**
    * A Builder interface to build different types of pages as needed.
    *
    * @param course builds the courses for the CoursePage.
    */
    void buildCourse(List<String> course);


    /**
    *
    * @param course set the Course object for the CoursePageBuilder.
    */
    void setCourse(Course course);


    /**
    * @param instructors set the instructors parameter for CoursePageBuilder.
    */
    void setInstructors(List<String> instructors);


    /**
    * reset this Builder so that it may be used again.
    */
    void reset();
}
