package UseCase.CoursePage;

import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;
import UseCase.CourseManager.CourseManager;

import java.util.List;
import java.util.Optional;

public class CoursePageDirector {
    private Course course;



    //Construct CoursePage filtered by BOTH year and instructor

    public void constructCoursePage(CoursePageBuilder cpb, Course course, List<Rating> ratings,
                                    Integer filter_year, String instructor_filter) {
        cpb.setCourse(course);
        cpb.setRatings(ratings);
        cpb.filterInstructor(instructor_filter);
        cpb.filterYear(filter_year);
        cpb.getResult();


    }

    //Construct CoursePage filtered ONLY by year
    public void constructCoursePage(CoursePageBuilder cpb, Course course, List<Rating> ratings,
                                    Integer filter_year) {

        cpb.setCourse(course);
        cpb.setRatings(ratings);
        cpb.filterYear(filter_year);




    }


    //Construct CoursePage filtered ONLY by instructor
    public void constructCoursePage(CoursePageBuilder cpb, Course course, List<Rating> ratings, String instructor_filter) {
    }



    //Construct CoursePage WITHOUT filters (default CoursePage)
    public void constructCoursePage(CoursePageBuilder cpb, Course course, List<Rating> ratings) {
    }



}


