package UseCase;

import UseCase.CoursePage.CoursePage;
import constants.PermissionLevel;
import entity.*;
import exceptions.ArgumentException;
import Interface.IDBSaveable;
import Interface.IReadModifiable;
import usecase.commentManager.CommentManager;

import java.io.Serializable;
import java.lang.management.OperatingSystemMXBean;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The CourseManager modifies the information in CoursePage. Reflecting relative scores depending
 * on student's program.
 *
 * Example usage:
 * CourseManager courseManager = new CourseManager(coursePage);
 * courseManager.filterInstructor("A");
 * courseManager.defaultCoursePage();
 * courseManager.updateCommentVote("ABCD", true);
 * courseManager.updateRating(5, user);
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

    /**
     * Constructor of CourseManager.
     *
     * @param coursePage CoursePage that CourseManager is going to modify.
     */
    public CourseManager(CoursePage coursePage) {
        this.authDict = getDefaultAuthDict();
        this.course = coursePage.getCourse();
        this.ratings = coursePage.getRatings();
        this.commentGraphs = coursePage.getCommentGraphs();
        this.coursePage = coursePage;
    }


    /**
     * Change current coursePage that CourseManager is handling to another coursePage.
     *
     * @param coursePage
     */
    public void changeCourse(CoursePage coursePage) {
        this.authDict = getDefaultAuthDict();
        this.course = coursePage.getCourse();
        this.ratings = coursePage.getRatings();
        this.commentGraphs = coursePage.getCommentGraphs();
        this.coursePage = coursePage;
    }

  
    /**
     * Add rating to the CoursePage.
     * @param ratingNum score that a user wants to leave.
     * @param user user who leaves a rating.
     * @throws Exception if it is not valid, throw exception.
     */
    public void addRating(float ratingNum, StudentUser user) throws Exception {
        List<Rating> ratingList = this.coursePage.getRatings();
        if (ratingList == null) {
            ratingList = new ArrayList<>();
            this.coursePage.setRatings(ratingList);
        }

        // TODO : assuming instructor is deleted in rating class.
        Rating r = new Rating(user, ratingNum);
        ratingList.add(r);
        this.updateAvgScore();
        if (this.ratings == null) {
            this.ratings = new ArrayList<Rating>();
        }
        this.ratings.add(r);


    }

    /**
     * Updates a rating that a current user already left.
     *
     * @param ratingNum A rating score that a user wants to change to. (0 <= ratingNum <= 1)
     * @param user      A user who wants to change its rating score.
     * @throws Exception
     */
    public void updateRating(float ratingNum, User user) throws Exception {
        for (Rating r : coursePage.getRatings()) {
            if (r.getRater().getID().equals(user.getID())) {
                r.setScore(ratingNum);
                this.updateAvgScore();
                return;
            }
        }
        throw new Exception("Rating is not updated");
    }


    /**
     * Change current relative score of CoursePage.
     *
     * @param program string of program name of raters' that will be filtered.
     */
    public void relativeRating(String program) {
        List<Rating> filterdRatings = this.ratings.stream().filter(
                r -> r.getRater().getProgramDetail().equals(program)).collect(Collectors.toList());
        float total = 0;
        for(Rating r : filterdRatings) {
            total += r.getScore();
        }
        coursePage.setRelativRating(total / filterdRatings.size());
    }

    /**
     * Starts a comment on current coursePage. User can only leave a comment when it is seeing
     * filtered coursePage.
     *
     * @param text Context that user wants to leave at the start of comment.
     * @param user Current user.
     * @throws Exception
     */
    public void startComment(String text, User user) throws Exception {
        CommentManager commentManager = this.coursePage.getThread();
        CommentGraph newCommentGraph = new CommentGraph(
                text, "Question", user.getdisplayName());
        if (commentManager == null) {
            this.coursePage.setCommentGraph(newCommentGraph);
            if (this.coursePage.getCommentGraphs() == null) {
                this.coursePage.setCommentGraphs(new ArrayList<CommentGraph>());
            }
            this.coursePage.getCommentGraphs().add(newCommentGraph);
        } else {
            throw new Exception("There is already starting comment");
        }
        if (this.commentGraphs == null) {
            this.commentGraphs = new ArrayList<CommentGraph>();
        }
        this.commentGraphs.add(newCommentGraph);


    }


    /**
     * Add comment to another comment in the commentGraph of current coursePage.
     *
     * @param prevId CommentId of the comment that user wants to reply to.
     * @param text   Context of comment that user wants to leave.
     * @param user   Current user.
     * @throws Exception
     */
    public void addComment(String prevId, String text, User user) throws Exception {

        CommentManager commentManager = this.coursePage.getThread());
        commentManager.replyToComment(prevId, text, user.getdisplayName());
    }





    /**
     * Upvote or downvote a comment in the commentGraph of current coursePage.
     *
     * @param commentId Id of comment that the user wants to upvote or downvote.
     * @param upvote    If true, upvotes, if false, downvotes a comment.
     * @throws Exception
     */
    public void updateCommentVote(String commentId, boolean upvote) throws Exception {
        CommentGraph currCommentGraph = this.coursePage.getCommentGraph();
        if (currCommentGraph == null) {
            throw new Exception("There is no comment in this coursePage");
        }

        if (upvote) {
            currCommentGraph.upvote(commentId);
        } else {
            currCommentGraph.downvote(commentId);
        }
    }


    /**
     * Change to default coursePage where all ratings and commentGraphs are there.
     *
     * @return Default coursePage.
     */
    public CoursePage defaultCoursePage() {
        this.coursePage.setCourse(this.course);
        this.coursePage.setCommentGraphs(this.commentGraphs);
        this.coursePage.setCommentGraph(null);
        this.coursePage.setRatings(this.ratings);
        this.coursePage.setInstructor(null);
        this.updateAvgScore();
        return this.coursePage;
    }


    // Getters

    /**
     * Get current coursePage of courseManager.
     *
     * @return Current coursePage.
     */
    public CoursePage getCoursePage() {
        return this.coursePage;
    }

    public CommentManager getComment() throws ArgumentException {
        if (this.getCoursePage().getInstructor() == null) {
            throw new ArgumentException("No comments found. Remember to filter by an instructor to get their comment section.");
        } else if (this.coursePage.getCommentGraph() == null) {
            throw new ArgumentException("No comment section. try starting one[startcomment]!");
        } else {
            return new CommentManager(this.coursePage.getCommentGraph());
        }
    }


    /**
     * Get Data about courseManager in HashMap.
     *
     * @return HashMap<name, data>.
     */
    @Override
    public HashMap<String, Object> getData() {
        HashMap<String, Object> infoMap = new HashMap<>();
        infoMap.put("filtering by", this.coursePage.getInstructor());
        infoMap.put("courseName", this.coursePage.getCourse().getName());
        infoMap.put("courseCode", this.coursePage.getCourse().getCode());
        infoMap.put("courseDescription", this.coursePage.getCourse().getDescription());
        infoMap.put("all instructors", this.coursePage.getInstructors());
//        infoMap.put("years", this.coursePage.getYears());
//        infoMap.put("currentInstructors", this.coursePage.getInstructor());
//        infoMap.put("currentYear", this.coursePage.getYear());
        infoMap.put("rating", this.coursePage.getAverageScore());

        return infoMap;
    }

    /**
     * Private method that returns average rating score of current coursePage.
     *
     * @return average of current rating scores.
     */
    public void updateAvgScore() {
        if (this.coursePage.getRatings() == null) {
            this.coursePage.setAverageScore(0);
        }
        float total = 0;
        for (Rating r : this.coursePage.getRatings()) {
            total += r.getScore();
        }
        this.coursePage.setAverageScore(total / this.coursePage.getRatings().size());
    }

    // IDBSAVEABLE methods


    /**
     * Get course code of course that CourseManager is handling.
     *
     * @return course code.
     */
    @Override
    public String getID() {
        return coursePage.getCourse().getCode();
    }

    /**
     * Get permission level hashmap of authorization hashmap.
     *
     * @return Current AuthDict.
     */
    @Override
    public Map<PermissionLevel, List<String>> getAuthDict() {
        return this.authDict;
    }


    /**
     * Get default authorization dictionary.
     *
     * @return Map of permission level and list of string.
     */
    public Map<PermissionLevel, List<String>> getDefaultAuthDict() {
        Map<PermissionLevel, List<String>> retDict = new HashMap<>();
        List<String> studentPermissions = Arrays.asList("print", "checkout", "rate", "filter", "getcomments", "startcomment");
        List<String> instructorPermissions = Arrays.asList("all");
        retDict.put(PermissionLevel.STUDENT, studentPermissions);
        retDict.put(PermissionLevel.INSTRUCTOR, instructorPermissions);
        return retDict;
    }
}
