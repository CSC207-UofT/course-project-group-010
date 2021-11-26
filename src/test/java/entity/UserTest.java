package entity;

import interfaces.IUser;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;


public class UserTest {

    @Test(timeout = 100)
    public void testGetID() {
        IUser a = new StudentUser("Kevin Hart", "12345", null);
        assertEquals(a.getID(), "12345");
    }

    @Test(timeout = 100)
    public void testGetDisplayName() {
        IUser a = new StudentUser("Kevin Hart", "12345", null);

        assertEquals(a.getDisplayName(), "Kevin Hart");
    }

    @Test(timeout = 100)
    public void testGetReviewCount() {
        IUser a = new StudentUser("Kevin Hart", "12345", null);

        assertEquals(a.getReviewCount(), 0);
    }

    @Test(timeout = 100)
    public void testGetOtherData() {
        IUser a = new StudentUser("Kevin Hart", "12345");

        HashMap<String, String> detail = new HashMap<>();
        detail.put("programDetail", "n/a");
        assertEquals(a.getOtherData(), detail);
    }

    @Test(timeout = 100)
    public void testToString() {
        IUser a = new StudentUser("Kevin Hart", "12345");
        assertEquals(a.toString(), "Kevin Hart" + "\n" + "12345");
    }

    @Test(timeout = 100)
    public void testGetOtherData2() {
        HashMap<String, String> addDetail = new HashMap<>();
        addDetail.put("programDetail", "Computer Science Specialist");
        IUser a = new StudentUser("Kevin Hart", "12345", addDetail);
        assertEquals(a.getOtherData(), addDetail);
    }

    @Test(timeout = 100)
    public void testGetProgramDetail() {
        HashMap<String, String> addDetail = new HashMap<>();
        addDetail.put("programDetail", "Computer Science Specialist");
        StudentUser a = new StudentUser("Kevin Hart", "12345", addDetail);
        assertEquals(a.getProgramDetail(), "Computer Science Specialist");
    }

    @Test(timeout = 100)
    public void testGetPosition() {
        HashMap<String, String> data = new HashMap<>();
        data.put("position", "Prof");
        InstructorUser a = new InstructorUser("Jonathan Calver", "11111", data);
        assertEquals(a.getPosition(), "Prof");
    }

    @Test(timeout = 100)
    public void testSetPosition() {
        InstructorUser a = new InstructorUser("Jonathan Calver", "11111");
        a.setPosition("Prof");
        assertEquals(a.getPosition(), "Prof");
    }
}
