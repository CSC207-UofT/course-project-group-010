package UseCase.CoursePage;

import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;
import UseCase.CourseManager.CourseManager;

import java.util.List;
import java.util.Optional;

public class Director {
    private int year;
    private InstructorUser instructor;

    //client code example:
    //Director director = new Director();
    //CoursePageBuilder cpb = new CoursePageBuilder();
    //director.constructCoursePage(cpb, Course course, List<Rating> ratings)
    //CoursePage cp = CoursePageBuilder.getResult(); now have CoursePage object with all ratings across all years.
    //cp.filter_year(year); provided in CourseManager
    //cp.filter_instructor(instructor); provided in CourseManager


    // default CoursePage
    public void constructCoursePage(CoursePageBuilder cpb, Course course, List<Rating> ratings) {
        cpb.setCourse(course);
        cpb.setRatings(ratings);

    }



    }


