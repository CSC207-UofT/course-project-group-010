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


/** CoursePage class. The page where it shows the ratings and other information about the course.
 *  It can show default page(all years, all instructors), year filtered page(ex. 2021),
 *  instructor filtered page(ex. ABCD EFGH), or both.
 */

public class CoursePage {
    private List<Integer> years; // The years the course was thought.
    private Course course; // course object
    private List<Rating> ratings; // List of ratings for this course
    private float averageScore;
    private float programRelativeScore;  // TODO: find better name?
    private Optional<InstructorUser> instructor; // optional default instructor
    private Optional<Integer> year; // optional default year
    private List<InstructorUser> instructors; //list of instructors teaching the course
    private List<Integer> years; // list of years the course was taught
    private CommentGraph commentGraph; // CommentGraph for this coursepage.

    public CoursePage(Course course, List<Rating> ratings, List<InstructorUser> instructors,
                      List<Integer> years, CommentGraph commentGraph ){
        // reverse sort the list of years so that current year is in front
        //Collections.reverse(years);
        this.course = course;
        this.ratings = ratings;
        this.instructors = instructors;
        // If it is instructor filtered course page, it has one instructor.
        this.years = years; // If it is year filtered course page, it has one year.
        this.commentGraph = commentGraph;

        // for now the default instructor will be the first one found in the list, later we will sort the list of
        // instructors by overriding the CompareTo method and comparing their names.

        // if there is at least one instructor
        if (instructors.size() > 0) {
            this.instructor = Optional.ofNullable(instructors.get(0));
        }

        // if there are no instructors
        else {
            this.instructor = Optional.empty();
        }

        if (years.size() > 0) {
            this.year = Optional.ofNullable(years.get(0));
        } else {
            this.year = Optional.empty();
        }
    }

    // get information from course Page
    public Course getCourse() {
        return this.course;
    }

    public float getAverageScore() {
        return this.averageScore;
    }

    public float getRelativeScore() {
        return this.programRelativeScore;
    }

    public int getNumberOfRatings() {
        return this.ratings.size();

    public List<InstructorUser> getInstructors() {
        return this.instructors;
    }

    public List<Integer> getYears() {
        return this.years;
    }

    public Optional<InstructorUser> getInstructor() {
        return this.instructor;
    }

    public Optional<Integer> getYear() {
        return this.year;
    }

    public void setInstructor(InstructorUser instructor) {
        this.instructor = Optional.ofNullable(instructor);
    }

    public void setYear(int year) {
        this.year = Optional.ofNullable(year);
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
