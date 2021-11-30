package usecase;

import constants.PermissionLevel;
import entity.*;
import exceptions.ArgumentException;
import interfaces.IDBSaveable;
import interfaces.IReadModifiable;
import interfaces.IUser;
import usecase.coursePage.CoursePage;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class CourseManager implements IReadModifiable, IDBSaveable, Serializable {

    // Course that CourseManager handles.
    private Course course;
    // All ratings about the course of CourseManager.
    private List<Rating> ratings;
    // All commentGraph about the course of CourseManager.
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
        this.coursePage = coursePage;
    }

    public CommentManager getCommentSection() {
        return this.coursePage.getThread();
    }


    /**
     * Change current coursePage that CourseManager is handling to another coursePage.
     *
     * @param coursePage A course page that a user wants to change to view
     */
    public void changeCourse(CoursePage coursePage) {
        this.authDict = getDefaultAuthDict();
        this.course = coursePage.getCourse();
        this.ratings = coursePage.getRatings();
        this.coursePage = coursePage;
    }

  
    /**
     * Add rating to the CoursePage.
     * @param ratingNum score that a user wants to leave.
     * @param user user who leaves a rating.
     */
    public void addRating(float ratingNum, StudentUser user){
        List<Rating> ratingList = this.coursePage.getRatings();
        if (ratingList == null) {
            ratingList = new ArrayList<>();
            this.coursePage.setRatings(ratingList);
            this.ratings = this.coursePage.getRatings();
        }

        Rating r = new Rating(user, ratingNum);
        ratingList.add(r);
        this.updateAvgScore();

    }

    /**
     * Updates a rating that a current user already left.
     *
     * @param ratingNum A rating score that a user wants to change to. (0 <= ratingNum <= 1)
     * @param user      A user who wants to change its rating score.
     * @throws Exception When rating cannot be updated.
     */
    public void updateRating(float ratingNum, IUser user) throws Exception {
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
    public float getRelativeRating(String program) {
        List<Rating> filterdRatings = this.ratings.stream().filter(
                r -> r.getRater().getProgramDetail().equals(program)).collect(Collectors.toList());
        if(filterdRatings.isEmpty()) {
            return -1;
        }
        float total = 0;
        for(Rating r : filterdRatings) {
            total += r.getScore();
        }
        return total / filterdRatings.size();

    }

    /**
     * Starts a comment on current coursePage. User can only leave a comment when it is seeing
     * filtered coursePage.
     *
     *
     * @param user Current user.
     * @param text Context that user wants to leave at the start of comment.
     * @throws Exception when there is an starting comment already.
     */
    public void startComment(IUser user, String text) throws Exception {
        CommentGraph commentGraph = this.coursePage.getCommentGraph();
        if (commentGraph == null) {
            HashMap<String, List<String>> initialComments = new HashMap<>();
            initialComments.put(user.getDisplayName(), List.of(text));
            CommentGraph newCommentGraph = new CommentGraph(
                    text, "Question", initialComments);
            this.coursePage.setCommentGraph(newCommentGraph);
        } else {
            throw new Exception("There is already a starting comment");
        }

    }



    /**
     * Add comment to another comment in the commentGraph of current coursePage.
     *
     * @param prevId CommentId of the comment that user wants to reply to.
     * @param text   Context of comment that user wants to leave.
     * @param user   Current user.
     * @throws Exception when there is no comment to reply.
     */
    public void addComment(String prevId, String text, IUser user) throws Exception {
        CommentGraph commentGraph = this.coursePage.getCommentGraph();
        if(commentGraph == null) {
            throw new Exception("There are no comments in this course page, you cannot add a comment");
        }
        this.coursePage.getThread().replyToComment(prevId, text, user.getDisplayName());

    }






    /**
     * Upvote or downvote a comment in the commentGraph of current coursePage.
     *
     * @param commentId Id of comment that the user wants to upvote or downvote.
     * @param upvote    If true, upvotes, if false, downvotes a comment.
     * @throws Exception when current comment graph is empty.
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
        if (this.coursePage.getCommentGraph() == null) {
            throw new ArgumentException("No comment section. try starting one[startcomment]!");
        } else {
            return new CommentManager(this.coursePage.getCommentGraph());
        }
    }

    public List<String> getRatingPrograms() {
        HashSet<String> ratingPrograms = new HashSet<>();
        this.ratings.stream().forEach(r -> ratingPrograms.add(r.getRaterProgramOfStudy()));
        return ratingPrograms.stream().collect(Collectors.toList());
    }

    /**
     * Get Data about courseManager in HashMap.
     *
     * @return HashMap<name, data>.
     */
    @Override
    public HashMap<String, Object> getData() {
        HashMap<String, Object> infoMap = new HashMap<>();
        infoMap.put("courseName", this.coursePage.getCourse().getName());
        infoMap.put("courseCode", this.coursePage.getCourse().getCode());
        infoMap.put("courseDescription", this.coursePage.getCourse().getDescription());
        infoMap.put("all instructors", this.coursePage.getInstructors());
        infoMap.put("rating", this.coursePage.getAverageScore());

        return infoMap;
    }


    /**
     * Private method that returns average rating score of current coursePage.
     */
    public void updateAvgScore() {
        if (this.ratings == null) {
            this.coursePage.setAverageScore(-1);
            return;
        }
        float total = 0;
        for (Rating r : this.ratings) {
            total += r.getScore();
        }
        this.coursePage.setAverageScore(total / this.ratings.size());
    }

    // IDBSAVEABLE methods


    /**
     * Get course code of course that CourseManager is handling.
     *
     * @return course code.
     */
    @Override
    public String getID() {
        return this.course.getCode();
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