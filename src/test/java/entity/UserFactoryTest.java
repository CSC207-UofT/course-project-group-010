package entity;

import constants.ProgramConstants;
import constants.UserType;
import interfaces.IUser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

public class UserFactoryTest {

    @Test
    public void testBasic() throws Exception {
        UserFactory uf = new UserFactory();
        IUser u = uf.getUser(UserType.STUDENT, "Kevin", "kev123", new HashMap<>());
        assertEquals("Kevin", u.getDisplayName());
        assertEquals("kev123", u.getID());
        assertEquals(ProgramConstants.NO_PROGRAM, ((StudentUser)u).getProgramDetail());
    }

    @Test
    public void testInstructor() throws Exception {
        UserFactory uf = new UserFactory();
        IUser u = uf.getUser(UserType.INSTRUCTOR, "Kevin", "kev123", new HashMap<>());
        assertEquals("Kevin", u.getDisplayName());
        assertEquals("kev123", u.getID());
    }
}
