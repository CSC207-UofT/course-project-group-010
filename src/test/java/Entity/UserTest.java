package Entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test(timeout = 100)
    public void testBasic() {
        User u = new StudentUser("Kevin", 1234);
        assertEquals(u.getName(), "Kevin");
        assertEquals(u.getID(), 1234);
    }
}
