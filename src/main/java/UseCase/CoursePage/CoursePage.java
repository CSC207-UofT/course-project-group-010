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
    private List<Rating> ratings; // List of ratings under this course; rating object
    private List<InstructorUser> instructors; // optional default instructor, WAS // Optional<InstructorUser>
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

    }



    // get information from course Page
    public Course getCourse(){
        return this.course;
    }

    public List<Rating> getRating(){
        return this.ratings;
    }

    public List<InstructorUser> getInstructors() {
        return this.instructors;
    }

    public List<Integer> getYears(){
        return this.years;
    }





    public void editRating(Rating rating){
        for(Rating i : this.ratings) {
            if(Rating)
        }
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
