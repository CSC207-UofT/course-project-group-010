package UseCase.CourseManager;

import Entity.InstructorUser;
import Entity.Rating;
import Entity.StudentUser;
import UseCase.CoursePage.CoursePage;

import java.util.HashMap;
import java.util.List;

public class CourseManager {
    private CoursePage coursePage;

    public CourseManager(CoursePage coursePage){
        this.coursePage = coursePage;
    }

    public void updateRating(int ratingNum, StudentUser student){
        Rating ratingToProcess = this.coursePage.getRating();
        ratingToProcess.processRating(ratingNum, student);
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


}
