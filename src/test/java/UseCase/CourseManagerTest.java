package UseCase;

import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;
import Entity.StudentUser;
import UseCase.CourseManager.CourseManager;
import UseCase.CoursePage.CoursePage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CourseManagerTest {
    // Redo test to compensate for changes.
    @Test(timeout=100)
    public void testCourseManager(){
        Course sampleCourse = new Course("Sample Course", "SC100");
        Rating sampleRating = new Rating(sampleCourse);

        InstructorUser profOne = new InstructorUser("Professor One", "1234", "Prof");
        InstructorUser profTwo = new InstructorUser("Professor Two", "4567", "Prof");

        List<InstructorUser> instructors = new ArrayList<>();

        instructors.add(profOne);
        instructors.add(profTwo);

        List<Integer> years = new ArrayList<>();
        years.add(2018);
        years.add(2019);
        years.add(2020);
        years.add(2021);

        Rating sampleCourseRating = new Rating(sampleCourse);

        CoursePage sampleCoursePage = new CoursePage(sampleCourse, sampleRating, instructors, years);

        CourseManager manager = new CourseManager(sampleCoursePage);

        StudentUser sampleStudent = new StudentUser("sample student", "0000", "Computer Science");

        manager.updateRating(5, sampleStudent);
        manager.filterInstructor(profOne);
        manager.filterYear(2020);


    }
}
