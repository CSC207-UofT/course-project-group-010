package usecase.coursePage;

import entity.CommentGraph;
import entity.Course;
import entity.Rating;
import interfaces.Builder;

import java.util.ArrayList;
import java.util.List;

public class CoursePageBuilder implements Builder {

    private Course course; // course object. Empty string if not assigned.
    private List<String> instructors; // List containing empty string if not assigned.
    private List<Rating> ratings = new ArrayList<>(); // List of all ratings left for this course across all instructors.


    @Override
    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public void setInstructors(List<String> instructors) {
        this.instructors = instructors;
    }

    @Override
    public void buildCourse(List<String> course) {
        Course c = new Course(course.get(0), course.get(1));
        if (course.size() == 3) {
            c.setDescription(course.get(2));
        }
        //Set CoursePageBuilder's course to the above.
        this.setCourse(c);

    }

    /**
     * Gets the resulting coursePage from this builder
     * @return the coursepage that was built.
     */
    public CoursePage getResult() {
        CoursePage cp = new CoursePage(this.course, this.instructors);
        //If Director takes info regarding ratings/cg in the constructPage constructor, assign values in CoursePage.
        cp.setRatings(this.ratings);
        // TODO pls don't change this
        cp.setCommentGraph(new CommentGraph("Comments", "Comments"));
        this.reset();
        return cp;
    }

    /**
     * Resets this class
     */
    @Override
    public void reset() {
        this.ratings = null;
    }

}
