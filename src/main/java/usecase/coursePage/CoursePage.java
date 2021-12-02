package usecase.coursePage;

import entity.CommentGraph;
import entity.Course;
import entity.Rating;
import usecase.CommentManager;


import javax.xml.stream.events.Comment;
import java.io.Serializable;
import java.util.*;

public class CoursePage implements Serializable {

    /**
     * A CoursePage object that stores all necessary data needed to display to the user.
     *
     * @param course         A course object containing the Course name, code, and description.
     * @param instructors    A list of instructors who have taught this course.
     * @param ratings        A list of all ratings left for this course. Null if not assigned.
     * @param averageScore   The average rating score value across all ratings for this course.
     * @param commentGraph  A commentGraph object representing the comment section on this page. Null if not assigned.
     */

    private Course course;
    private List<String> instructors;
    private List<Rating> ratings;
    private float averageScore;
    private CommentGraph commentGraph;


    /**
     *    { Mathematics:  { Specialist: [Rating1, Rating2, Rating3]
     *                       Major: [Rating1, Rating2, Rating3]
     *                       Minor: [Rating1, Rating2, Rating3] }
     *
     *      Philosophy:   { Specialist: [Rating1, Rating2, Rating3]
     *                       Major: [Rating1, Rating2, Rating3]
     *                       Minor: [Rating1, Rating2, Rating3] }
     *
     *
     *      Computer Science: { Specialist: [Rating1, Rating2, Rating3]
     *                              Major: [Rating1, Rating2, Rating3]
     *                              Minor: [Rating1, Rating2, Rating3] }
     *
     *    Constants for programs:
     *
     *
     *
     */

//    public float getRelativeRating(List<Rating> ratings) {
//        HashMap<String, HashMap<String, List<Rating>>> programToRating = new HashMap<>();
//        for (Rating r : ratings) {
//            programToRating.put()
//        }
//    }

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


    //Setters

    // Set information for course Page
    public void setCourse(Course course) {
        this.course = course;
    }

    public void setInstructors(List<String> instructors) {
        this.instructors = instructors;
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
