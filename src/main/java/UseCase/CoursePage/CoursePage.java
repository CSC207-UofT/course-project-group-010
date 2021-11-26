package UseCase.CoursePage;

import entity.CommentGraph;
import entity.Course;
import entity.Rating;

import java.io.Serializable;
import java.util.*;

public class CoursePage implements Serializable {
    private Course course; // course object
    private List<String> instructors; //List of all instructors who have taught this course.
    private List<Rating> ratings; // List of all ratings left for this course across all instructors. Null if not given.
    private float averageScore; // The rating being presented currently.
    private List<CommentGraph> commentGraphs; // List of all commentGraphs for this course across all instructors. Null
    // if not assigned.
    private CommentGraph commentGraph; // CommentGraph for this CoursePage. Null if not set.


    public CoursePage(Course course, List<String> instructors) {

        this.course = course;
        this.instructors = instructors;
        this.ratings = null;
        this.commentGraph = null;
        this.commentGraphs = null;
    }


    // get information from course Page

    // For now, assume there's only one thread associated with this instructor. This thread will contain all questions
    // and discussions by users.

    // Returns CommentGraph associated with instructor. If not found, returns Null. Can throw exception if we want.
//    public CommentManager getThread(String instructor) {
//        if (this.getCommentGraphs() == null) {
//            return null;
//        }
//        for (CommentGraph c : this.commentGraphs) {
//            if (c.getInstructor().equals(instructor)) {
//                return new CommentManager(c);
//            }
//        }
//        return null;
//    }

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

    public int getNumberOfRatings() {
        return this.ratings.size();
    }

    public CommentGraph getCommentGraph() {
        return this.commentGraph;
    }

    public List<CommentGraph> getCommentGraphs() {
        return this.commentGraphs;
    }

    public CommentGraph commentGraph() {
        return this.commentGraph;
    }

    //Setters

    // Set information for course Page
    public void setCourse(Course course) {
        this.course = course;
    }


    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setAverageScore(float AverageScore) {
        this.averageScore = AverageScore;
    }

    public void setCommentGraphs(List<CommentGraph> commentGraphs) {
        this.commentGraphs = commentGraphs;
    }

    public void setCommentGraph(CommentGraph commentGraph) {
        this.commentGraph = commentGraph;
    }
}
