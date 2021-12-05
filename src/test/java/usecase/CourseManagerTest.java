package usecase;

import constants.UserType;
import entity.StudentUser;
import exceptions.ArgumentException;
import interfaces.IUser;
import org.junit.Before;
import org.junit.Test;
import usecase.coursePage.CoursePage;
import usecase.coursePage.CoursePageBuilder;
import usecase.coursePage.Director;

import java.util.*;

import static org.junit.Assert.*;


public class CourseManagerTest {

    public CourseManager cm;
    public final StudentUser u = new StudentUser("Kevin", "k123");

    @Before
    public void setup() {
        List<String> course = List.of("Math", "MAT137", "description");
        List<String> instructor = List.of("Alfonso");
        CoursePageBuilder cpb = new CoursePageBuilder();
        Director d = new Director();

        // Using the builder to build things
        d.constructCoursePage(cpb, course, instructor);
        CoursePage cp = cpb.getResult();
        cm = new CourseManager(cp);
    }

    @Test
    public void getComments() {
        assertNotEquals(null, cm.getCommentSection());
    }

    @Test(expected = ArgumentException.class)
    public void addRatingInvalid() throws ArgumentException {
        cm.addRating(100, u);
    }

    @Test
    public void testRatings() throws ArgumentException {
        cm.addRating(10, u);
        assertEquals(true, cm.getRelativeRatings().containsKey(u.getProgramDetail()));
        assertEquals(10, cm.getRelativeRating(u.getProgramDetail()), 0.1);
        cm.addRating(5, u);
        assertEquals(5.0, cm.getRelativeRating(u.getProgramDetail()), 0.1);
    }

    @Test
    public void testMultipleUserRatings() throws ArgumentException {
        cm.addRating(10, u);
        IUser u2 = new StudentUser("kev2", "kev2");
        cm.addRating(5, u2);
        assertEquals(7.5, (Double) cm.getData().get("rating"), 0.1);
        assertEquals(7.5, cm.getRelativeRating(u.getProgramDetail()), 0.1);
        cm.addRating(5, u);
        assertEquals(5.0, (Double) cm.getData().get("rating"), 0.1);
        assertEquals(5.0, cm.getRelativeRating(u.getProgramDetail()), 0.1);
    }

    @Test
    public void testRelativeRatings() {
        assertEquals(-1, cm.getRelativeRating("DATA SCIENCE"), 0.1);
    }

    @Test
    public void testGetData() {
        assertEquals(6, cm.getData().keySet().size());
    }

    @Test
    public void testGetID() {
        assertEquals("MAT137", cm.getID());
    }

    @Test
    public void testAuth() {
        assertEquals(true, cm.getAuthDict().containsKey(UserType.STUDENT));
        assertEquals(true, cm.getAuthDict().containsKey(UserType.INSTRUCTOR));
    }
}
