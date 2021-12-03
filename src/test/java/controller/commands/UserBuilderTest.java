package controller.commands;

import controller.commands.commandHelpers.InstructorUserBuilder;
import controller.commands.commandHelpers.StudentUserBuilder;
import controller.commands.commandHelpers.UserBuilder;
import exceptions.ArgumentException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserBuilderTest {

    UserBuilder sb = new StudentUserBuilder();
    UserBuilder ib = new InstructorUserBuilder();
    List<UserBuilder> ubl = List.of(sb, ib);

    @Test
    public void testProcessDisplayName() {
        String name = "Kevin";
        for (UserBuilder ub : ubl) {
            assertEquals(name, ub.processDisplayName(name));
        }
    }

    @Test
    public void testProcessID() throws ArgumentException {
        String id = "KEV123";
        String id2 = "kev";
        String id3 = "k e v 1 2 3";
        for (UserBuilder ub : ubl) {
            assertEquals("kev123", ub.processID(id));
            assertEquals("kev", ub.processID(id2));
            assertEquals("kev123", ub.processID(id3));
        }
    }

    @Test(expected=ArgumentException.class)
    public void testInvalidID() throws ArgumentException {
        sb.processID("kev1393a");
    }

    @Test(expected=ArgumentException.class)
    public void testInvalidID2() throws ArgumentException {
        ib.processID("1393a");
    }

    @Test(expected=ArgumentException.class)
    public void testInvalidID3() throws ArgumentException {
        ib.processID("");
    }
}
