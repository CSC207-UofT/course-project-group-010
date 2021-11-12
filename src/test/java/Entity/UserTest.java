package Entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UserTest {

    @Test(timeout = 100)
    public void testgetID() {
        User a = new StudentUser("Kevin Hart", "12345", null);
        assertEquals(a.getID(), "12345");
    }

    @Test(timeout = 100)
    public void testgetdisplayName() {
        User a = new StudentUser("Kevin Hart", "12345",null);

        assertEquals(a.getdisplayName(), "Kevin Hart");
    }

    @Test(timeout = 100)
    public void testgetReviewCount() {
        User a = new StudentUser("Kevin Hart", "12345", null);

        assertEquals(a.getReviewCount(), 0);
    }
}
