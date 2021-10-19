package UseCase.CourseManager;

import Controller.Commands.CommandExceptions.CommandNotAuthorizedException;
import Entity.InstructorUser;
import Entity.Rating;
import Entity.StudentUser;
import Entity.User;
import Interface.IDBSaveable;
import Interface.IGettable;
import UseCase.CoursePage.CoursePage;
import UseCase.UserManager;

import java.util.HashMap;
import java.util.List;

public class CourseManager implements IGettable, IDBSaveable {

    private CoursePage coursePage;

    // if it only initializes with a coursePage, why can't we just delete coursePage and put stuff in here?
    // CoursePage only contains getters anyways...
    public CourseManager(CoursePage coursePage){
        this.coursePage = coursePage;
    }

    public void updateRating(int ratingNum, UserManager user) throws CommandNotAuthorizedException {
        // TODO check if rating is in the allowed range?
        Rating ratingToProcess = this.coursePage.getRating();
        // TODO change the code such that the casting below is not required, user.getUser() will not always be a student
        ratingToProcess.processRating(ratingNum, (StudentUser)user.getUser());
        this.coursePage.setRating(ratingToProcess);
    }

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
    public HashMap<String, Object> giveDataToDatabase() throws IllegalArgumentException {
        return getData();
    }

    @Override
    public String getID() {
        return coursePage.getCourse().getCode();
    }
}
