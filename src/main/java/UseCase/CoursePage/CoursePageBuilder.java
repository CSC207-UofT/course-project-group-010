package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public class CoursePageBuilder implements Builder {

    private List<Integer> years; // list of years the course was taught
    private Course course; // course object
    private Rating rating; // rating object
    private List<InstructorUser> instructors; //list of instructors teaching the course
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
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public void setInstructors(List<InstructorUser> instructors){
        this.instructors = instructors;
    }

    @Override
    public void setCommentGraph(CommentGraph commentGraph) {
        this.commentGraph = commentGraph;
    }


    public CoursePage getResult(){
        return new CoursePage(years, course, rating, instructors, commentGraph);

    }

}
