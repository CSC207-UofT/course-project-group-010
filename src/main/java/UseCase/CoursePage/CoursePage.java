package UseCase.CoursePage;

import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;
import Entity.CommentGraph;
import Entity.CommentGraphHelper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

// TODO look at Commands.CheckoutCommand. The constructor for this is way to complex. Not going to work.
// also the constructor forces any class that constructs this to access entity classes, which is bad.
public class CoursePage {
    private int year;
    private List<Integer> years;
    private Course course; // course object
    private List<Rating> ratings; // List of ratings under this course; rating object
    private String instructor; // optional default instructor, WAS // Optional<InstructorUser>
    private List<InstructorUser> instructors; //list of instructors teaching the course
    private CommentGraph commentGraph;

    public CoursePage(Course course, List<Rating> ratings, String instructor){
        // reverse sort the list of years so that current year is in front
        //Collections.reverse(years);
        this.course = course;
        this.ratings = ratings;
        this.instructor = instructor;
        //this.years = years;
        //this.commentGraph = commentGraph;

        // for now the default instructor will be the first one found in the list, later we will sort the list of
        // instructors by overriding the CompareTo method and comparing their names.

        // if there is at least one instructor
        if (instructors.size() > 0)
        {
            this.instructor = Optional.ofNullable(instructors.get(0));
        }

        // if there are no instructors
        else
        {
            this.instructor = Optional.empty();
        }

        if (years.size() > 0)
        {
            this.year = years.get(0);
        }

        else
        {
            this.year = -1;
        }
    }



    // get information from course Page
    public Course getCourse(){
        return this.course;
    }

    public Rating getRating(){
        return this.rating;
    }

    public List<InstructorUser> getInstructors(){
        return this.instructors;
    }

    public List<Integer> getYears(){
        return this.years;
    }

    public Optional<InstructorUser> getInstructor(){
        return this.instructor;
    }

    public Optional<Integer> getYear(){
        return this.year;
    }

    public void setInstructor(InstructorUser instructor){
        this.instructor = Optional.ofNullable(instructor);
    }

    public void setYear(int year){
        this.year = Optional.ofNullable(year);
    }

    public void setRating(Rating rating){
        this.rating = rating;
    }

    @Override
    public String toString() {
        String result = this.course.toString() + "\n";
        result += this.rating.toString() + "\n";
        result += this.commentGraph.stringRepresentation(
                commentGraph.getVertices().get("root"), 0, commentGraph.getMaxDepth(), true);
        return result;
    }
}
