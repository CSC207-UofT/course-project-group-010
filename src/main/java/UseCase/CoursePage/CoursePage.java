package UseCase.CoursePage;

import Entity.CommentGraph;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;
import UseCase.CommentManager.CommentManager;

import java.lang.reflect.Array;
import java.util.*;

public class CoursePage {
    private Course course; // course object
    private List<String> instructors; //List of all instructors who have taught this course.
    private List<Rating> ratings; // List of all ratings left for this course across all instructors. Null if not given.
    private float averageScore; // The rating being presented currently. Will change if filtered by instructor
    private List<CommentGraph> commentGraphs; // List of all commentGraphs for this course across all instructors. Null
                                              // if not assigned.
    private CommentGraph commentGraph; // Current CommentGraph being presented when filtered by instructor. Null if
                                       // not filtered.
    private String instructor; // Current instructor CoursePage is filtered by.


    public CoursePage(Course course, List<String> instructors) {

        this.course = course;
        this.instructors = instructors;
        this.instructor = "No Filter Selected";
        this.ratings = null;
        this.commentGraph = null;
        this.commentGraphs = null;
    }


    // get information from course Page

    // For now, assume there's only one thread associated with this instructor. This thread will contain all questions
    // and discussions by users.

    // Returns CommentGraph associated with instructor. If not found, returns Null. Can throw exception if we want.
    public CommentManager getThread(String instructor){
        for (CommentGraph c : this.commentGraphs) {
            if (c.getInstructor().equals(instructor)) {
                return new CommentManager(c);
            }
        }
        return null;
    }

    public Course getCourse(){
        return this.course;
    }

    public List<String> getInstructors() {
        return this.instructors;
    }

    public List<Rating> getRatings(){
        return this.ratings;
    }


    public String getInstructor(){
        return this.instructor;
    }

    public float getAverageScore(){
        return this.averageScore;
    }


    public int getNumberOfRatings(){
        return this.ratings.size();
    }

    public CommentGraph getCommentGraph(){
        return this.commentGraph;
    }

    public List<CommentGraph> getCommentGraphs(){
        return this.commentGraphs;
    }

    public CommentGraph commentGraph(){
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

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setAverageScore(float AverageScore) {
        this.averageScore = AverageScore;
    }

    public void setCommentGraphs(List<CommentGraph> commentGraphs){
        this.commentGraphs = commentGraphs;
    }

    public void setCommentGraph(CommentGraph commentGraph){
        this.commentGraph = commentGraph;
    }







//FOR PHASE 2 BELOW





    // private Integer year; // The Course filtered by the year being currently viewed. // TODO: Phase 2 maybe.
    //    private HashMap<Integer, List<String>> instructors_to_years; //Hashmap of years the course was taught mapped to the
    //list of instructors who taught the course during that year.
    //  private float programRelativeScore;  // TODO: Phase 2

    //    private HashMap<Integer, List<String>> getInstructors_to_years() {
    //        return this.instructors_to_years;
    //    }


// {2019: [John, May, Bill], 2020: [Jen, Asif, Pat]}
// .values = [ [John, May, Bill], [Jen, Asif, Pat] ]

//Return list of all instructors who have taught this course across all years.
//    public List<String> getInstructors() {
//        List<String> to_return = new ArrayList<String>();
//        List<List<String>> valueList = new ArrayList<>(this.instructors_to_years.values());
//        for(List<String> l: valueList){
//            for(String s: l){
//                if (! to_return.contains(s)) {
//                    to_return.add(s);
//                }
//            }
//        }
//        return to_return;
//    }

    //return list of all years taught.
//    public List<Integer> getYears() {
//        return new ArrayList<>(this.instructors_to_years.keySet());
//    }
//    public float getRelativeScore() {
//        return this.programRelativeScore;
//    }

//    public void setInstructors_to_years(HashMap<Integer, List<String>> instructors_to_years) {
//        this.instructors_to_years = instructors_to_years;
//    }

//    public void setRelativeScore(float programRelativeScore) {
//        this.programRelativeScore = programRelativeScore;
//    }



//    public Optional<InstructorUser> getInstructor() {
//        return this.instructor;
//    }

//    public Optional<Integer> getYear() {
//        return this.year;
//    }



//    public void setYear(int year) {
//        this.year = year;
//    }


}
