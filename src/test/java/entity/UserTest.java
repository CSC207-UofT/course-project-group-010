package entity;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;


public class UserTest {

    @Test(timeout = 100)
    public void testgetID() {
        User a = new StudentUser("Kevin Hart", "12345", null);
        assertEquals(a.getID(), "12345");
    }

    @Test(timeout = 100)
    public void testgetdisplayName() {
        User a = new StudentUser("Kevin Hart", "12345", null);

        assertEquals(a.getdisplayName(), "Kevin Hart");
    }

    @Test(timeout = 100)
    public void testgetReviewCount() {
        User a = new StudentUser("Kevin Hart", "12345", null);

        assertEquals(a.getReviewCount(), 0);
    }

    @Test(timeout = 100)
    public void testgetOtherData() {
        User a = new StudentUser("Kevin Hart", "12345");

        HashMap<String, String> detail = new HashMap<>();
        detail.put("programDetail", "n/a");
        assertEquals(a.getOtherData(), detail);
    }

    @Test(timeout = 100)
    public void testtoString() {
        User a = new StudentUser("Kevin Hart", "12345");
        assertEquals(a.toString(), "Kevin Hart" + "\n" + "12345");
    }

    @Test(timeout = 100)
    public void test2getOtherData() {
        HashMap<String, String> adddetail = new HashMap<>();
        adddetail.put("programDetail", "Computer Science Specialist");
        User a = new StudentUser("Kevin Hart", "12345", adddetail);
        assertEquals(a.getOtherData(), adddetail);
    }

    @Test(timeout = 100)
    public void testgetProgramDetial() {
        HashMap<String, String> adddetail = new HashMap<>();
        adddetail.put("programDetail", "Computer Science Specialist");
        StudentUser a = new StudentUser("Kevin Hart", "12345", adddetail);
        assertEquals(a.getProgramDetail(), "Computer Science Specialist");
    }

    @Test(timeout = 100)
    public void testgetPosition() {
        HashMap<String, String> data = new HashMap<>();
        data.put("position", "Prof");
        InstructorUser a = new InstructorUser("Jonathan Calver", "11111", data);
        assertEquals(a.getPosition(), "Prof");
    }

    @Test(timeout = 100)
    public void testsetPosition() {
        InstructorUser a = new InstructorUser("Jonathan Calver", "11111");
        a.setPosition("Prof");
        assertEquals(a.getPosition(), "Prof");
    }
}
