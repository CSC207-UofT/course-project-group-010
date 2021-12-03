package controller.commands;

import constants.CommandConstants;
import constants.UserType;
import controller.commands.coursecommands.CreateCourseCommand;
import exceptions.CommandNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import usecase.CommentPresenter;
import usecase.UserManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * contains basic tests for the entire COMMANDS package. These tests simulate user input into the CLI,
 * testing for the correct output.
 */
public class SimulatedInputTest {
    public static CommandExecutor ce;

    @BeforeClass
    public static void setup() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ce = CommandExecutor.getInstance();
        try {
            CreateCourseCommand ccm = new CreateCourseCommand();
            List<String> course = List.of("csc", "CSC207", "csc");
            List<String> instructor = List.of("a", "a");
            Class<?> createCourseCommandClass = Class.forName("controller.commands.coursecommands.CreateCourseCommand");
            Method constructCourse = createCourseCommandClass.getDeclaredMethod("constructCourse", List.class, List.class);
            constructCourse.setAccessible(true);
            constructCourse.invoke(ccm, course, instructor);

            NewUserCommand nuc = new NewUserCommand();
            Class<?> nucClass = Class.forName("controller.commands.NewUserCommand");
            Method constructUser = nucClass.getDeclaredMethod("createUser", UserType.class, String.class, String.class, Map.class);
            constructUser.setAccessible(true);
            constructUser.invoke(nuc, UserType.STUDENT, "SampleStudent", "test", new HashMap<>());
        } catch (Exception e) {
            // That means these courses/students were already initialized, we don't need to do it again
            e.getMessage();
        }

    }

    @After
    public void tearDown() {
        testcommand("logout");
    }

    @Test (timeout = 100)
    public void testArgMissing() {
        String output = testcommand("asdf");
        assertEquals(output, "Command not Found");
    }

    @Test (timeout = 100)
    public void testLoginLogout() {
        assertEquals("Logged in as SampleStudent", testcommand("login test"));
        assertEquals("Logged out of SampleStudent", testcommand("logout"));
        assertEquals("Not logged in.", testcommand("checkout -u"));
    }

    @Test (timeout = 100)
    public void testCreateCourse() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        CreateCourseCommand ccm = new CreateCourseCommand();
        // this code can't be in the database, or else you get an error.
        List<String> course = List.of("math", "---------", "calc");
        List<String> instructor = List.of("a", "a");
        Class<?> createCourseCommandClass = Class.forName("controller.commands.coursecommands.CreateCourseCommand");
        Method constructCourse = createCourseCommandClass.getDeclaredMethod("constructCourse", List.class, List.class);
        constructCourse.setAccessible(true);

        String expected = "Successfully created " + course.get(0) + " with " + instructor.size() + " instructors.\n" +
                "Make sure to run saveall to save your progress!";

        assertEquals(constructCourse.invoke(ccm, course, instructor), expected);
    }

    @Test (timeout = 100)
    public void testCreateUser() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        NewUserCommand nuc = new NewUserCommand();
        Class<?> nucClass = Class.forName("controller.commands.NewUserCommand");
        Method constructUser = nucClass.getDeclaredMethod("createUser", UserType.class, String.class, String.class, Map.class);
        constructUser.setAccessible(true);

        String name = "---";
        String id = "----";
        UserManager um = (UserManager) constructUser.invoke(nuc, UserType.STUDENT, name, id, new HashMap<>());
        assertEquals(id, um.getID());
        assertEquals(name, um.getUser().getDisplayName());
    }

    @Test (timeout = 100)
    public void testCheckout() {
        // need to create a user
        String output = testcommand("login test");
        String output1 = testcommand("checkout CSC207");
        assertEquals("now viewing course CSC207", output1);

    }

    @Test (timeout = 100)
    public void testCheckoutUserPage() {
        testcommand("login test");
        assertEquals("now viewing profile of test", testcommand("checkout -u"));
    }

    @Test (timeout = 100)
    public void testCheckoutComments() {
        testcommand("login test");
        testcommand("checkout CSC207");
        assertEquals("now viewing comment section for page", testcommand("checkout -c"));
        if (!(ce.getPageManager() instanceof CommentPresenter)) {
            fail("ce.getPageMgr not instance of CommentPresenter");
        }
    }

    @Test (timeout = 100)
    public void testHelp() throws CommandNotFoundException {
        // Every command besides help should display their help string when -h is appended to the command.
        String output = "";
        CommandConstants cc = new CommandConstants();
        for (String s : cc.command_dict.keySet()) {
            if (!s.equals("help")) {
                output = testcommand(s + " -h");
                assertEquals(cc.get(s).help(), output);
            }
        }
    }

    public String testcommand(String command) {
        try {
            CommandRequest request = new CommandRequest(command);
            String output = ce.processRequest(request);
            if (!output.equals("")) {
                return output;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "got nothing";
    }
}
