package UseCase.CourseManager;

import Constants.PermissionLevel;
import Exceptions.CommandNotAuthorizedException;
import Entity.InstructorUser;
import Entity.Rating;
import Entity.StudentUser;
import Interface.IDBSaveable;
import Interface.IReadModifiable;
import UseCase.CoursePage.CoursePage;
import UseCase.UserManager;

import java.io.Serializable;
import java.util.*;

public class CourseManager implements IReadModifiable, IDBSaveable, Serializable {

    private CoursePage coursePage;
    private Map<PermissionLevel, List<String>> authDict;

    // if it only initializes with a coursePage, why can't we just delete coursePage and put stuff in here?
    // CoursePage only contains getters anyways...
    public CourseManager(CoursePage coursePage){
        this.coursePage = coursePage;
        this.authDict = getDefaultAuthDict();

    }

    public void updateRating(int ratingNum, UserManager user) throws CommandNotAuthorizedException {
        // TODO check if rating is in the allowed range?
        Rating ratingToProcess = this.coursePage.getRating();
        // TODO change the code such that the casting below is not required, user.getUser() will not always be a student
        ratingToProcess.processRating(ratingNum, (StudentUser)user.getUser());
        this.coursePage.setRating(ratingToProcess);
    }

    // When will we use this?
    public void filterInstructor(InstructorUser instructor){
        List<InstructorUser> instructors = this.coursePage.getInstructors();

        if (instructors.contains(instructor)){
            this.coursePage.setInstructor(instructor);
        }

        else
        {
            //do not change the instructor
        }
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

    public HashMap<String, Object> getData(){
        HashMap<String, Object> infoMap = new HashMap<>();
        infoMap.put("courseName", this.coursePage.getCourse().getName());
        infoMap.put("courseCode", this.coursePage.getCourse().getCode());
        infoMap.put("courseDescription", this.coursePage.getCourse().getDescription());
        infoMap.put("instructors", this.coursePage.getInstructors());
        infoMap.put("years", this.coursePage.getYears());
        infoMap.put("currentInstructors", this.coursePage.getInstructor());
        infoMap.put("currentYear", this.coursePage.getYear());
        infoMap.put("rating", this.coursePage.getRating());

        return infoMap;
    }

    // IDBSAVEABLE methods

    @Override
    public String getID() {
        return coursePage.getCourse().getCode();
    }

    // Authorizable Methods

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
