package usecase.coursePage;

import entity.CommentGraph;
import entity.Course;
import entity.Rating;
import usecase.CommentManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A course page, that holds a course, related ratings and comment section(commentGraph)
 */
public class CoursePage implements Serializable {

    /**
     * A CoursePage object that stores all necessary data needed to display to the user.
     *
     * course         A course object containing the Course name, code, and description.
     * instructors    A list of instructors who have taught this course.
     * ratings        A list of all ratings left for this course. Null if not assigned.
     * averageScore   The average rating score value across all ratings for this course.
     * commentGraph  A commentGraph object representing the comment section on this page. Null if not assigned.
     */

    private Course course;
    private List<String> instructors;
    private List<Rating> ratings;
    private double averageScore;
    private CommentGraph commentGraph;

    /**
     * Initializes a new coursePage
     *
     * @param course A course object containing the Course name, code, and description.
     * @param instructors A list of instructors who have taught this course.
     */
    public CoursePage(Course course, List<String> instructors) {

        this.course = course;
        this.instructors = instructors;
        this.ratings = new ArrayList<>();
    }

    /**
     * @return the Course object of this CoursePage.
     */
    public Course getCourse() {
        return this.course;
    }

    /**
     * @return the List of Instructors of this CoursePage.
     */
    public List<String> getInstructors() {
        return this.instructors;
    }


    /**
     * @return the List of Ratings of this CoursePage.
     */
    public List<Rating> getRatings() {
        return this.ratings;
    }


    /**
     * @return the average score across all the ratings for this CoursePage.
     */
    public double getAverageScore() {
        return this.averageScore;
    }


    /**
     * @return the commentGraph object associated with this CoursePage.
     */
    public CommentGraph getCommentGraph() {
        return this.commentGraph;
    }


    /**
     * @return the a CommentManager object with this CoursePage's commentGraph as its input.
     */
    public CommentManager getThread() {
        return new CommentManager(this.getCommentGraph());
    }


    /**
     * Set the Course object for this CoursePage.
     */
    public void setCourse(Course course) {
        this.course = course;
    }


    /**
     * Set the Instructors variable for this CoursePage.
     */
    public void setInstructors(List<String> instructors) {
        this.instructors = instructors;
    }


    /**
     * Set the Ratings for this CoursePage.
     */
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }


    /**
     * Set the AverageScore for this CoursePage.
     */
    public void setAverageScore(double AverageScore) {
        this.averageScore = AverageScore;
    }


    /**
     * Set the CommentGraph object for this CoursePage.
     */
    public void setCommentGraph(CommentGraph commentGraph) {
        this.commentGraph = commentGraph;
    }

}