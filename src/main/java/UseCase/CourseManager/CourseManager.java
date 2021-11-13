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
     * @param text
     * @param user
     * @throws Exception
     */
    public void startComment(String text, User user) throws Exception{
        // coursePage getCommentGraph is not implemented;
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

    public void addComment(String prevId, String text, User user) throws Exception {
        if(this.filterInstructor == null || this.coursePage.getThread(this.filterInstructor) == null) {
            throw new Exception();
        }
        CommentManager commentManager = this.coursePage.getThread(this.filterInstructor);
        commentManager.replyToComment(prevId, text, user.getdisplayName());
    }


    // When will we use this?
    public CoursePage filterInstructor(String queryInstructorName){
        List<Rating> filteredRatings = ratings.stream().filter(
                r -> r.getInstructor()==queryInstructorName).collect(Collectors.toList());

        this.coursePage.setRatings(filteredRatings);
        float total = 0;
        for(Rating r : filteredRatings) {
            total += r.getScore();
        }
        this.coursePage.setAverageScore(total/filteredRatings.size());
        this.coursePage.setInstructor(queryInstructorName);
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

    public CoursePage defaultCoursePage() {
        this.coursePage.setCourse(this.course);
        this.coursePage.setCommentGraphs(this.commentGraphs);
        this.coursePage.setCommentGraph(null);
        this.coursePage.setRatings(this.ratings);
        this.coursePage.setAverageScore(this.getAvgScore());
        this.coursePage.setInstructor(null);
        return this.coursePage;
    }

    public CoursePage getCoursePage() {
        return this.coursePage;
    }

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

    private float getAvgScore() {
        float total = 0;
        for(Rating r : this.coursePage.getRatings()) {
            total += r.getScore();
        }
        return total / this.coursePage.getRatings().size();
    }
    // IDBSAVEABLE methods

    public HashMap<String, Object> giveDataToDatabase() throws IllegalArgumentException {
        return getData();
    }

    @Override
    public String getID() {
        return coursePage.getCourse().getCode();
    }

    @Override
    public Map<PermissionLevel, List<String>> getAuthDict() {
        return this.authDict;
    }

    public Map<PermissionLevel, List<String>> getDefaultAuthDict() {
        Map<PermissionLevel, List<String>> retDict = new HashMap<>();
        List<String> studentPermissions = Arrays.asList("print", "checkout", "rate");
        List<String> instructorPermissions = Arrays.asList("all");
        retDict.put(PermissionLevel.STUDENT, studentPermissions);
        retDict.put(PermissionLevel.INSTRUCTOR, instructorPermissions);
        return retDict;
    }
}
