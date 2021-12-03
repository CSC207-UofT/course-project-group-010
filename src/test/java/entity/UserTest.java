package entity;

import interfaces.IUser;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class UserTest {

    @Test(timeout = 100)
    public void testGetID() {
        IUser a = new StudentUser("Kevin Hart", "k12345");
        assertEquals(a.getID(), "k12345");
    }

    @Test(timeout = 100)
    public void testGetDisplayName() {
        IUser a = new StudentUser("Kevin Hart", "k12345");

        assertEquals(a.getDisplayName(), "Kevin Hart");
    }

    @Test(timeout = 100)
    public void testGetData() {
        HashMap<String, String> detail = new HashMap<>();
        detail.put("ID", "k12345");
        detail.put("displayName", "Kevin Hart");
        detail.put("programDetail", "N/A");
        IUser a = new StudentUser("Kevin Hart", "k12345");
        assertEquals(a.getData(), detail);
    }

    @Test(timeout = 100)
    public void testGetData2() {
        HashMap<String, String> detail = new HashMap<>();
        detail.put("ID", "j11111");
        detail.put("displayName", "Jonathan Calver");
        detail.put("position", "Prof");
        HashMap<String, String> data = new HashMap<>();
        data.put("position", "Prof");
        InstructorUser a = new InstructorUser("Jonathan Calver", "j11111", data);
        assertEquals(a.getData(), detail);
    }

    @Test(timeout = 100)
    public void testGetOtherData() {
        IUser a = new StudentUser("Kevin Hart", "k12345");

        if (!a.getOtherData().containsKey("programDetail")) {
            fail();
        }
    }

    @Test(timeout = 100)
    public void testGetOtherData2() {
        IUser a = new StudentUser("Kevin", "k123");
        IUser b = new InstructorUser("Kevin", "k1234");
        if (!(a.getOtherData() instanceof Map) || !(b.getOtherData() instanceof Map)) {
            fail();
        }
    }

    @Test(timeout = 100)
    public void testToString() {
        IUser a = new StudentUser("Kevin Hart", "k12345");
        assertEquals(a.toString(), "Kevin Hart" + "\n" + "k12345");
    }

    @Test(timeout = 100)
    public void testFixOtherData() {
        HashMap<String, String> addDetail = new HashMap<>();
        addDetail.put("programDetail", "Computer Science Specialist");
        StudentUser a = new StudentUser("Kevin Hart", "k12345", addDetail);
        assertEquals("N/A", a.getProgramDetail());
    }

    @Test(timeout = 100)
    public void testGetProgramDetail() {
        HashMap<String, String> addDetail = new HashMap<>();
        addDetail.put("programDetail", "DATA SCIENCE");
        StudentUser a = new StudentUser("Kevin Hart", "k12345", addDetail);
        assertEquals("DATA SCIENCE", a.getProgramDetail());
    }

    @Test(timeout = 100)
    public void testSetProgramDetail() {
        StudentUser a = new StudentUser("Kevin", "kev123");
        a.setProgramDetail("DATA SCIENCE");
        assertEquals("DATA SCIENCE", a.getProgramDetail());
    }

    @Test(timeout = 100)
    public void testSetInvalidProgramDetail() {
        StudentUser a = new StudentUser("Kevin", "kev123");
        a.setProgramDetail("abasdf");
        assertEquals("N/A", a.getProgramDetail());
    }

    @Test(timeout = 100)
    public void testGetPosition() {
        HashMap<String, String> data = new HashMap<>();
        data.put("position", "Prof");
        InstructorUser a = new InstructorUser("Jonathan Calver", "j11111", data);
        assertEquals(a.getPosition(), "Prof");
    }

    @Test(timeout = 100)
    public void testSetPosition() {
        InstructorUser a = new InstructorUser("Jonathan Calver", "j11111");
        a.setPosition("Prof");
        assertEquals(a.getPosition(), "Prof");
    }

    @Test(timeout = 100)
    public void testBasicInstructor() {
        IUser a = new InstructorUser("Kevin", "k123");
        assertEquals("Kevin", a.getDisplayName());
        assertEquals("k123", a.getID());
        assertEquals("N/A", a.getOtherData().get("position"));
    }
}
