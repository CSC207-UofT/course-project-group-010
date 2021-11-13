package UseCase.CourseManager;

import Constants.PermissionLevel;
import Entity.*;
import Exceptions.CommandNotAuthorizedException;
import Interface.IDBSaveable;
import Interface.IReadModifiable;
import UseCase.CommentManager.CommentManager;
import UseCase.CoursePage.CoursePage;
import UseCase.CoursePage.CoursePageBuilder;
import UseCase.CoursePage.Director;
import UseCase.UserManager;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

    /**
     * The CourseManager modifies the information in CoursePage. Reflecting instructor filter and
     * affected ratings and CommentGraph.
     *
     * Example usage:
     * CourseManager courseManager
     */

public class CourseManager implements IReadModifiable, IDBSaveable, Serializable {

    // Course that CourseManager handles.
    private Course course;
    // All ratings about the course of CourseManager.
    private List<Rating> ratings;
    // All commentGraphs about the course of CourseManager.
    private List<CommentGraph> commentGraphs;
    // A CoursePage that CourseManager handles.
    private CoursePage coursePage;
    // Map of permission level.
    private Map<PermissionLevel, List<String>> authDict;
    // All instructors of the course of CourseManager;
    private List<String> instructors;
    // An instructor that CourseManager is currently filtering.
    private String filterInstructor;


    /**
     * Constructor of CourseManager.
     *
     * @param coursePage CoursePage that CourseManager is going to modify.
     */
    public CourseManager(CoursePage coursePage){
        this.authDict = getDefaultAuthDict();
        this.course = coursePage.getCourse();
        this.ratings = coursePage.getRatings();
        this.commentGraphs = coursePage.getCommentGraphs();
        this.instructors = coursePage.getInstructors();
        this.coursePage = coursePage;
        this.filterInstructor = null;
    }


    /** Change current coursePage that CourseManager is handling to another coursePage.
     *
     * @param coursePage
     */
    public void changeCourse(CoursePage coursePage) {
        this.authDict = getDefaultAuthDict();
        this.course = coursePage.getCourse();
        this.ratings = coursePage.getRatings();
        this.commentGraphs = coursePage.getCommentGraphs();
        this.instructors = coursePage.getInstructors();
        this.coursePage = coursePage;
        this.filterInstructor = null;
    }


    /** Updates a rating that a current user already left.
     *
     * @param ratingNum A rating score that a user wants to change to.
     * @param user A user who wants to change its rating score.
     * @throws Exception
     */
    public void updateRating(int ratingNum, UserManager user) throws Exception {
        if(ratingNum < 0 || ratingNum > 10 || coursePage.getRatings() == null) {
            throw new Exception();
        }
        for(Rating r : coursePage.getRatings()) {
            if(r.getRater().equals(user.getUser())) {
                r.setScore(ratingNum);
                break;
            }
        }
    }


    /** Starts a comment on current coursePage. User can only leave a comment when it is seeing
     * filtered coursePage.
     *
     * @param text Context that user wants to leave at the start of comment.
     * @param user Current user.
     * @throws Exception
     */
    public void startComment(String text, User user) throws Exception{
        if(this.filterInstructor == null) {
            throw new Exception();
        }
        CommentManager commentManager = this.coursePage.getThread(this.filterInstructor);
        if(commentManager == null) {
            CommentGraph newCommentGraph = new CommentGraph("Question", user.getdisplayName(), this.filterInstructor);
            this.coursePage.setCommentGraph(newCommentGraph);
            if(this.commentGraphs == null) {
                this.commentGraphs = new ArrayList<CommentGraph>();
                this.commentGraphs.add(newCommentGraph);
            }
            commentManager = new CommentManager(this.coursePage.getCommentGraph());
        }
        commentManager.replyToComment("root", text, user.getdisplayName());


    }


    /** Add comment to another comment in the commentGraph of current coursePage.
     *
     * @param prevId CommentId of the comment that user wants to reply to.
     * @param text Context of comment that user wants to leave.
     * @param user Current user.
     * @throws Exception
     */
    public void addComment(String prevId, String text, User user) throws Exception {
        if(this.filterInstructor == null || this.coursePage.getThread(this.filterInstructor) == null) {
            throw new Exception();
        }
        CommentManager commentManager = this.coursePage.getThread(this.filterInstructor);
        commentManager.replyToComment(prevId, text, user.getdisplayName());
    }


    /** Filter ratings and commentGraphs in coursePage.
     *
     * @param filterInstructorName An instructor name that user uses to filter coursePage.
     * @return
     */
    public CoursePage filterInstructor(String filterInstructorName){
        List<Rating> filteredRatings = ratings.stream().filter(
                r -> r.getInstructor()==filterInstructorName).collect(Collectors.toList());

        this.coursePage.setRatings(filteredRatings);
        float total = 0;
        for(Rating r : filteredRatings) {
            total += r.getScore();
        }
        this.coursePage.setAverageScore(total/filteredRatings.size());
        this.coursePage.setInstructor(filterInstructorName);
        return this.coursePage;

//        if (instructors.contains(instructor)){
//            this.coursePage.setInstructor(instructor);
//        }
//
//        else
//        {
//            //do not change the instructor
//        }
    }


    /** Upvote or downvote a comment in the commentGraph of current coursePage.
     *
     * @param commentId Id of comment that the user wants to upvote or downvote.
     * @param upvote If true, upvotes, if false, downvotes a comment.
     * @throws Exception
     */
    public void updateCommentVote(String commentId, boolean upvote) throws Exception{
        CommentGraph currCommentGraph = this.coursePage.getCommentGraph();
        if(currCommentGraph == null) {
            throw new Exception("There is no comment in this coursePage");
        }

        if(upvote) {
            currCommentGraph.upvote(commentId);
        }
        else {
            currCommentGraph.downvote(commentId);
        }
    }


// Phase 2
//    public void filterYear(int year){
//        List<Integer> years = this.coursePage.getYears();
//
//        if (years.contains(year)){
//            this.coursePage.setYear(year);
//        }
//
//        else
//        {
//            // do not change the year
//        }
//    }


    /** Change to default coursePage where all ratings and commentGraphs are there.
     *
     * @return Default coursePage.
     */
    public CoursePage defaultCoursePage() {
        this.coursePage.setCourse(this.course);
        this.coursePage.setCommentGraphs(this.commentGraphs);
        this.coursePage.setCommentGraph(null);
        this.coursePage.setRatings(this.ratings);
        this.coursePage.setAverageScore(this.getAvgScore());
        this.coursePage.setInstructor(null);
        return this.coursePage;
    }


    // Getters

    /** Get current coursePage of courseManager.
     *
     * @return Current coursePage.
     */
    public CoursePage getCoursePage() {
        return this.coursePage;
    }


    /** Get Data about courseManager in HashMap.
     *
     * @return HashMap<name, data>.
     */
    @Override
    public HashMap<String, Object> getData(){
        HashMap<String, Object> infoMap = new HashMap<>();
        infoMap.put("courseName", this.coursePage.getCourse().getName());
        infoMap.put("courseCode", this.coursePage.getCourse().getCode());
        infoMap.put("courseDescription", this.coursePage.getCourse().getDescription());
        infoMap.put("instructors", this.coursePage.getInstructors());
//        infoMap.put("years", this.coursePage.getYears());
        infoMap.put("currentInstructors", this.coursePage.getInstructor());
//        infoMap.put("currentYear", this.coursePage.getYear());
        infoMap.put("rating", this.coursePage.getRatings());

        return infoMap;
    }

    /** Private method that returns average rating score of current coursePage.
     *
     * @return average of current rating scores.
     */
    private float getAvgScore() {
        float total = 0;
        for(Rating r : this.coursePage.getRatings()) {
            total += r.getScore();
        }
        return total / this.coursePage.getRatings().size();
    }

    // IDBSAVEABLE methods

//    public HashMap<String, Object> giveDataToDatabase() throws IllegalArgumentException {
//        return getData();
//    }

    /** Get course code of course that CourseManager is handling.
     *
     * @return course code.
     */
    @Override
    public String getID() {
        return coursePage.getCourse().getCode();
    }

    /** Get permission level hashmap of authorization hashmap.
     *
     * @return Current AuthDict.
     */
    @Override
    public Map<PermissionLevel, List<String>> getAuthDict() {
        return this.authDict;
    }


    /** Get default authorization dictionary.
     *
     * @return Map of permission level and list of string.
     */
    public Map<PermissionLevel, List<String>> getDefaultAuthDict() {
        Map<PermissionLevel, List<String>> retDict = new HashMap<>();
        List<String> studentPermissions = Arrays.asList("print", "checkout", "rate");
        List<String> instructorPermissions = Arrays.asList("all");
        retDict.put(PermissionLevel.STUDENT, studentPermissions);
        retDict.put(PermissionLevel.INSTRUCTOR, instructorPermissions);
        return retDict;
    }
}
