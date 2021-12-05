package controller.commands;

import constants.ProgramConstants;
import controller.commands.commandHelpers.InstructorUserBuilder;
import controller.commands.commandHelpers.StudentUserBuilder;
import controller.commands.commandHelpers.UserBuilder;
import exceptions.ArgumentException;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class UserBuilderTest {

    final UserBuilder sb = new StudentUserBuilder();
    final UserBuilder ib = new InstructorUserBuilder();
    final List<UserBuilder> ubl = List.of(sb, ib);

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

    @Test
    public void testOtherData() {
        Map<String, String> od1 = sb.processOtherData("asd");
        Map<String, String> od2 = ib.processOtherData("asd");
        assertEquals(true, od1.get("programDetail").equals(ProgramConstants.NO_PROGRAM));
        assertEquals(true, od2.get("position").equals("asd"));
    }
}
