package Controller.commands;

import Controller.commands.commentcommands.DisplayFullThreadCommand;
import Controller.commands.commentcommands.ReplyCommand;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Basic test of the command classes and the commandExecutor.
 * Most command functionality has been tested by running the actual code.
 * Assuming that the components of the command's run() method have been unit tested,
 * I believe this is sufficient as a test.
 * I will probably try to make new tests later though.
 */
public class CommandTest {
    CommandExecutor ce;

    @Before
    public void setup() {
        ce = CommandExecutor.getInstance();
    }

    @Test(timeout = 100)
    public void testNoCommand() {
        CommandRequest request = new CommandRequest("asdf");
        assertEquals(ce.processRequest(request), "Command not Found");
    }

    @Test(timeout = 100)
    public void testInvalidArgs() {
        CommandRequest request = new CommandRequest("print asdf agaj fklaj");
        assertEquals(ce.processRequest(request), "Invalid number of Arguments");
    }

    @Test(timeout = 100)
    public void testHelp() {
        CommandRequest request = new CommandRequest("displayfullthread -h");
        assertEquals(ce.processRequest(request), new DisplayFullThreadCommand().help());
    }

    // This test was used to verify that buildComment in ReplyCommand works. However, that method is now private.

    @Test(timeout = 100)
    public void testReplyBuilder() throws Exception {
        Class<?> replyCommandClass = Class.forName("Controller.commands.commentcommands.ReplyCommand");
        Method buildCommentMethod = replyCommandClass.getDeclaredMethod("buildComment", List.class);
        buildCommentMethod.setAccessible(true);

        CommandRequest request = new CommandRequest("reply id hello what is your name?");
        ReplyCommand cmd = new ReplyCommand();
        List<String> args = request.getArguments();
        assertEquals(buildCommentMethod.invoke(cmd, args), "hello what is your name?");
    }
}
