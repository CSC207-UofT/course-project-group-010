package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public class CoursePageBuilder implements Builder {

    private int year; // Information on CoursePage should correspond to this year's data, maybe arrange database in a way that we can easily access data by year?
    private Course course; // course object
    private Rating rating; // rating object
    private Optional<InstructorUser> instructor; // optional default instructor

    private List<InstructorUser> instructors; //list of instructors teaching the course
    private List<Integer> years; // list of years the course was taught
    private CommentGraph commentGraph;

    @Override
    public void setCourse(Course course){
        this.course = course;
    }

    @Override
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public void addInstructors(InstructorUser instructor) {
        this.instructors.add(instructor);
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void setCommentGraph(CommentGraph commentGraph) {
        this.commentGraph = commentGraph;
    }

}
