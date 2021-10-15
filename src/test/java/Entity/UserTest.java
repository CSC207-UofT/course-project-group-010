package Entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test(timeout = 100)
    public void testBasic() {
        User u = new StudentUser("Kevin", "Kevin@gmail.com", "Computer Science Major");
        assertEquals(u.getdisplayName(), "Kevin");
        assertEquals(u.getID(), "Kevin@gmail.com");
    }
}
