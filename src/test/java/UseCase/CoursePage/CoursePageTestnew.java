package UseCase.CoursePage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Float;

import Entity.*;

import static org.junit.Assert.*;

public class CoursePageTestnew {

    @Test(timeout=100)
    public void testgetCourse() {
        Course courseone = new Course("Software Design", "CSC207");
        HashMap<String, String> data = new HashMap<>();
        data.put("position", "Prof");
        InstructorUser instructorone = new InstructorUser("Jonathan Calver", "11111", data);
        List<String> ins = new ArrayList<>();
        ins.add(instructorone.getdisplayName());
        CoursePage a = new CoursePage(courseone, ins);
        assertEquals(a.getCourse(), courseone);
    }

    @Test(timeout=100)
    public void testgetInstructors() {
        Course courseone = new Course("Software Design", "CSC207");
        HashMap<String, String> data = new HashMap<>();
        data.put("position", "Prof");
        InstructorUser instructorone = new InstructorUser("Jonathan Calver", "11111", data);
        List<String> ins = new ArrayList<>();
        ins.add(instructorone.getdisplayName());
        CoursePage a = new CoursePage(courseone, ins);
        assertEquals(a.getInstructors(), ins);
    }

    @Test(timeout=100)
    public void testgetRatings() {
        Course courseone = new Course("Software Design", "CSC207");
        HashMap<String, String> data = new HashMap<>();
        data.put("position", "Prof");
        InstructorUser instructorone = new InstructorUser("Jonathan Calver", "11111", data);
        List<String> ins = new ArrayList<>();
        ins.add(instructorone.getdisplayName());
        CoursePage a = new CoursePage(courseone, ins);
        assertNull(a.getRatings());
    }

    @Test(timeout=100)
    public void testsetAverageScore() {
        Course courseone = new Course("Software Design", "CSC207");
        HashMap<String, String> data = new HashMap<>();
        data.put("position", "Prof");
        InstructorUser instructorone = new InstructorUser("Jonathan Calver", "11111", data);
        List<String> ins = new ArrayList<>();
        ins.add(instructorone.getdisplayName());
        CoursePage a = new CoursePage(courseone, ins);
        a.setAverageScore(4.55f);
        assertEquals(a.getAverageScore(),4.55f, 0.0);
    }
}

