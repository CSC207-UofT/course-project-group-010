package UseCase.CourseManager;

import Entity.Instructor;
import UseCase.CoursePage.CoursePage;

public class CourseManager {
    private CoursePage coursePage;

    public CourseManager (CoursePage coursePage)
    {
        this.coursePage = coursePage;
    }

    public fliterData(Instructor instructor, int year)
    {
        if (this.coursePage.getInstructors().contains(instructor) && this.coursePage.getYears().contains(year))
        {
            this.coursePage.setInstructor(instructor);
            this.coursePage.setYear(year);
        }
    }

    public leaveReview(Student student)
    {
        // if Student is authorized
        // then compute new review
    }
}
