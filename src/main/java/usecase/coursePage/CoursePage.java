package usecase.coursePage;

import entity.CommentGraph;
import entity.Course;
import entity.Rating;
import usecase.CommentManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CoursePage implements Serializable {



    private Course course;
    private List<String> instructors;
    private List<Rating> ratings;
    private float averageScore;
    private CommentGraph commentGraph;

    /**
     * A CoursePage object that stores all necessary data needed to display to the user.
     * The constructor requires a Course object and a List of instructors who have taught that course to initialize.
     * The ratings parameter is an empty array by default, and may be updated as needed in other domains.
     *
     * @param course         A course object containing the Course name, code, and description.
     * @param instructors    A list of instructors who have taught this course.
     */

    public CoursePage(Course course, List<String> instructors) {

        this.course = course;
        this.instructors = instructors;
        this.ratings = new ArrayList<>();
    }

    public Course getCourse() {
        return this.course;
    }

    public List<String> getInstructors() {
        return this.instructors;
    }

    public List<Rating> getRatings() {
        return this.ratings;
    }

    public float getAverageScore() {
        return this.averageScore;
    }

    public CommentGraph getCommentGraph() {
        return this.commentGraph;
    }

    public CommentManager getThread() {
        return new CommentManager(this.getCommentGraph());
    }


    public void setCourse(Course course) {
        this.course = course;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setAverageScore(float AverageScore) {
        this.averageScore = AverageScore;
    }

    public void setCommentGraph(CommentGraph commentGraph) {
        this.commentGraph = commentGraph;
    }

}
