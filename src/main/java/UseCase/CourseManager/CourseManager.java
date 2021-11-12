package UseCase.CourseManager;

import Constants.PermissionLevelConstants;
import Entity.*;
import Exceptions.CommandNotAuthorizedException;
import Interface.IDBSaveable;
import Interface.IReadModifiable;
import UseCase.CoursePage.Builder;
import UseCase.CoursePage.CoursePage;
import UseCase.CoursePage.CoursePageBuilder;
import UseCase.CoursePage.Director;
import UseCase.UserManager;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CourseManager implements IReadModifiable, IDBSaveable, Serializable {

    private Course course;
    private List<Rating> ratings;
    private CommentGraph commentGraph;
    private CoursePage coursePage;
    private Map<Integer, List<String>> authDict;
    private Director director;
    private List<InstructorUser> instructorUsers;

    // if it only initializes with a coursePage, why can't we just delete coursePage and put stuff in here?
    // CoursePage only contains getters anyways...

    // CourseManager is going to tell the director to build coursepage or give
    // builder filtered ratings.
    // I did not touch other classes
    // TODO but please make other constructors public so that it can be used in other packages.
    public CourseManager(Course course, List<InstructorUser> instructorUsers, List<Rating> ratings, CommentGraph commentGraph){
        this.authDict = getDefaultAuthDict();
        this.course = course;
        this.ratings = ratings;
        this.commentGraph = commentGraph;
        this.instructorUsers = instructorUsers;
        this.director = new Director(new CoursePageBuilder());
        this.director.constructCoursePage(this.director.getBuilder(), this.course, this.ratings,
                this.instructorUsers, this.commentGraph);
    }


    //Overloading the constructor for constructCoursePage as per Clean Architecture for optional parameters,
    //I think this is best approach because kevin can just input whatever info is given




    public void updateRating(int ratingNum, UserManager user) throws CommandNotAuthorizedException {
        if(ratingNum < 0 || ratingNum > 10) {
            throw new CommandNotAuthorizedException();
        }
        for(Rating r : coursePage.getRatings()) {
            if(r.getRater().equals(user.getUser())) {
                r.setScore(ratingNum);
                break;
            }
        }
//        // TODO check if rating is in the allowed range?
//        Rating ratingToProcess = this.coursePage.getRating();
//        // TODO change the code such that the casting below is not required, user.getUser() will not always be a student
//        ratingToProcess.processRating(ratingNum, (StudentUser)user.getUser());
//        this.coursePage.setRating(ratingToProcess);
    }

    public void addComment(String prevId, String text, User user) {
        // coursePage getCommentGraph is not implemented;

        CommentGraph commentGraph = this.coursePage.getCommentGraph();
        commentGraph.reply(prevId, text, user.getID());
    }


    // When will we use this?
    public void filterInstructor(String queryInstructorName){
        List<Rating> filteredRatings = ratings.stream().filter(
                r -> r.getInstructor()==queryInstructorName).collect(Collectors.toList());

        this.coursePage = this.director.constructCoursePage(this.director.getBuilder(), this.course, filteredRatings,
                this.instructorUsers, this.commentGraph);

//        if (instructors.contains(instructor)){
//            this.coursePage.setInstructor(instructor);
//        }
//
//        else
//        {
//            //do not change the instructor
//        }
    }

    public void filterYear(int year){
        List<Integer> years = this.coursePage.getYears();

        if (years.contains(year)){
            this.coursePage.setYear(year);
        }

        else
        {
            // do not change the year
        }
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
        infoMap.put("years", this.coursePage.getYears());
        infoMap.put("currentInstructors", this.coursePage.getInstructor());
        infoMap.put("currentYear", this.coursePage.getYear());
        infoMap.put("rating", this.coursePage.getRatings());

        return infoMap;
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
    public Map<Integer, List<String>> getAuthDict() {
        return this.authDict;
    }

    public Map<Integer, List<String>> getDefaultAuthDict() {
        PermissionLevelConstants permLvl = new PermissionLevelConstants();
        Map<Integer, List<String>> retDict = new HashMap<>();
        List<String> studentPermissions = Arrays.asList("print", "checkout", "rate");
        List<String> instructorPermissions = Arrays.asList("all");
        retDict.put(permLvl.STUDENT, studentPermissions);
        retDict.put(permLvl.INSTRUCTOR, instructorPermissions);
        return retDict;
    }
}
