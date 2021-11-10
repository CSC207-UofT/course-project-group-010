package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public class CoursePageBuilder implements Builder {

    private int year;
    private List<Integer> years;
    private Course course; // course object
    private List<Rating> ratings; // List of ratings under this course; rating object
    private String instructor; // optional default instructor, WAS // Optional<InstructorUser>
    private List<String> instructors; //list of instructors teaching the course
    private CommentGraph commentGraph;


    @Override
    public void setYears(List<Integer> years) {
        this.years = years;
    }

    @Override
    public void setCourse(Course course){
        this.course = course;
    }

    @Override
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }



    @Override
    public void setInstructors(List<String> instructors) {
        this.instructors = instructors;

    }

    @Override
    public void setCommentGraph(CommentGraph commentGraph) {

    }


    public CoursePage getResult(){
        return new CoursePage(years, course, ratings, instructors, commentGraph);

    }

}
