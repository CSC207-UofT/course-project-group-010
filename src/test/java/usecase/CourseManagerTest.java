package usecase;

import entity.*;
import exceptions.ArgumentException;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import usecase.coursePage.*;
import usecase.*;
import entity.Course;
import java.util.*;



public class CourseManagerTest {
    @Test(timeout = 100)
    public void testCourseManager1() throws ArgumentException {
        CoursePage coursePage = new CoursePage(
                new Course("Sample Course1", "CSC108"),
                List.of("Instructor A", "Instructor B", "Instructor C"));
        CourseManager courseManager = new CourseManager(coursePage);

        assertEquals(courseManager.getID(), "CSC108");
        assertEquals(courseManager.getCoursePage(), coursePage);
        assertEquals(courseManager.getCoursePage().getRatings(), null);
        try {
            courseManager.getComment();
            fail("Show throw an exception here");
        } catch (Exception e) {
        }

        assertEquals(courseManager.getCoursePage().getCommentGraph(), null);

        StudentUser studentUser1 = new StudentUser("Student1", "00001");

        try {
            courseManager.addRating((float) 0.5, studentUser1);
        } catch (Exception e) {
            fail("Should not throw exception here");
        }

        assertEquals(0.5, courseManager.getCoursePage().getAverageScore(), 0.001);
        try {
            courseManager.updateRating((float) 0.7, studentUser1);
        } catch (Exception e) {
            assertEquals("Should not throw exception here", "");
        }

        assertEquals(0.7, courseManager.getCoursePage().getAverageScore(), 0.001);
        try {
            courseManager.startComment(studentUser1, "Comment 1");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw exception here");
        }

        StudentUser studentUser12 = new StudentUser("Student2", "00002");
        try {
            courseManager.addRating((float) 0.3, studentUser12);
        } catch (Exception e) {
            fail("Should not throw error here");
        }

        assertEquals(courseManager.getCoursePage().getAverageScore(), 0.5, 0.001);
//        System.out.println(courseManager.getCoursePage().getCommentGraph());
        CommentManager commentManager = courseManager.getComment();
//        System.out.println(commentManager.displayEntireThread(false, 3));
        String prevId = commentManager.getChildrenComments("root").get(0).getId();
        try {
            courseManager.addComment(prevId, "Comment 2", studentUser1);
        } catch (Exception e) {
            fail("Should not throw exception here");
        }
        try {
            courseManager.updateCommentVote(prevId, true);
        } catch (Exception e) {
            fail("Should not throw exception here");
        }
        assertEquals(courseManager.getCoursePage().getCommentGraph().getComment(prevId).getVote(), 1);
//        System.out.println(courseManager.getComment().displayEntireThread(true, 3));

    }
}
