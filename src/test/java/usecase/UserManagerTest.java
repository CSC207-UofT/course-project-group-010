package usecase;

import constants.UserType;
import org.junit.Before;
import org.junit.Test;
import usecase.UserManager;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserManagerTest {

    public UserManager um;

    // TODO New implementation of UserManager, check then change tests
    // or explain how to implement into commandExecutor.
    @Before
    public void setup() throws Exception {
        um = new UserManager(UserType.STUDENT, "Kevin", "kev1", new HashMap<>());
    }

    @Test
    public void getBasic() {
        assertEquals(um.getData(), um.getUser().getData());
        assertEquals("kev1", um.getID());
        assertEquals(UserType.STUDENT, um.getPermissionLevel());
        assertTrue(um.getAuthDict().containsKey(UserType.STUDENT) && um.getAuthDict().containsKey(UserType.INSTRUCTOR));
    }

}
