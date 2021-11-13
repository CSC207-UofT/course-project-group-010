package UseCase;

import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;
import Entity.StudentUser;
import Exceptions.ArgumentException;
import UseCase.CourseManager.CourseManager;
import UseCase.CoursePage.CoursePage;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

public class CourseManagerTest {
    // Redo test to compensate for changes.
    @Test(timeout=100)
    public void testCourseManager1() throws ArgumentException {
        CoursePage coursePage = new CoursePage(
                new Course("Sample Course1", "CSC108"),
                List.of("Instructor A", "Instructor B", "Instructor C"));
        CourseManager courseManager = new CourseManager(coursePage);

        assertEquals(courseManager.getID(), "CSC108");
        assertEquals(courseManager.getCoursePage(), coursePage);
        assertEquals(courseManager.getCoursePage().getRatings(), null);
        assertEquals(courseManager.getCoursePage().getCommentGraphs(), null);

        StudentUser studentUser = new StudentUser("Student1", "00001");
        try {
            courseManager.addRating(5, studentUser);
            assertEquals("Should throw exception here", "");
        }
        catch(Exception e) {

        }
        courseManager.filterInstructor("Instructor A");
        try {
            courseManager.addRating(5, studentUser);
        }
        catch(Exception e) {
            assertEquals("Should not throw exception here", "");
        }
        assertEquals(5.0, courseManager.getCoursePage().getAverageScore(), 0.001);

    }
}
