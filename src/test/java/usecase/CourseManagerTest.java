package usecase;

import constants.PermissionLevel;
import entity.*;
import exceptions.ArgumentException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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

        courseManager.updateAvgScore();
        assertEquals(-1, courseManager.getCoursePage().getAverageScore());

        Map<PermissionLevel, List<String>> authDict = courseManager.getAuthDict();
        assertEquals(authDict.get(PermissionLevel.STUDENT),
                Arrays.asList("print", "checkout", "rate", "filter", "getcomments", "startcomment"));
        assertEquals(authDict.get(PermissionLevel.INSTRUCTOR),
                Arrays.asList("all"));

        assertEquals(courseManager.getID(), "CSC108");
        assertEquals(courseManager.getCoursePage(), coursePage);
        assertEquals(courseManager.getCoursePage().getRatings(), null);
        try {
            courseManager.getComment();
            fail("Shouldn't throw an exception here");
        } catch (Exception e) {
        }

        assertEquals(courseManager.getCoursePage().getCommentGraph(), null);
        HashMap<String, String> studentUser1otherData = new HashMap<>();
        studentUser1otherData.put("programDetail", "COMPUTER SCIENCE");
        StudentUser studentUser1 = new StudentUser("Student1", "00001", studentUser1otherData);
        try {
            courseManager.addRating((float) 0.5, studentUser1);
        } catch (Exception e) {
            fail("Should not throw exception here");
        }
        assertEquals(courseManager.getRatingPrograms(), Arrays.asList("COMPUTER SCIENCE"));
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

        HashMap<String, String> studentUser2otherData = new HashMap<>();
        studentUser2otherData.put("programDetail", "DATA SCIENCE");
        StudentUser studentUser2 = new StudentUser("Student2", "00002", studentUser2otherData);
        try {
            courseManager.addRating((float) 0.3, studentUser2);
        } catch (Exception e) {
            fail("Should not throw error here");
        }
        List<String> programList = Arrays.asList("DATA SCIENCE", "COMPUTER SCIENCE");
        Collections.sort(programList);
        List<String> actual = courseManager.getRatingPrograms();
        Collections.sort(actual);
        assertEquals(programList, actual);
        assertEquals(courseManager.getCoursePage().getAverageScore(), 0.5, 0.001);

        CommentManager commentManager = courseManager.getComment();
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
        try {
            courseManager.updateCommentVote(prevId, false);
        } catch (Exception e) {
            fail("Should not throw exception here");
        }

        try {
            courseManager.updateCommentVote("12345", true);
            fail("Should throw exception here");
        } catch (Exception e) {
        }

        HashMap<String, Object> coursePageData = courseManager.getData();
        assertEquals("Sample Course1", coursePageData.get("courseName"));
        assertEquals("CSC108", coursePageData.get("courseCode"));
        assertEquals("There is currently no description available for this course",
                coursePageData.get("courseDescription"));
        assertEquals(Arrays.asList("Instructor A", "Instructor B", "Instructor C"),
                coursePageData.get("all instructors"));
        assertEquals(Double.valueOf(0.5),
                Double.valueOf(coursePageData.get("rating").toString()), 0.001);
    }
}
