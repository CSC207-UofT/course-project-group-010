package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public class CoursePageBuilder implements Builder {

    private Course course; // course object
    private List<Rating> ratings; // rating object
    private String instructor; //The instructor teaching the course


    @Override
    public void setCourse(Course course){
        this.course = course;
    }

    @Override
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public void setInstructor(String instructor){
        this.instructor = instructor;
    }


    public CoursePage getResult(){
        return new CoursePage(course, ratings, instructor);

    }

}
