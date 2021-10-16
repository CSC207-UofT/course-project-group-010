package Controller.Commands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Basic test of the command classes and the commandExecutor
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

    /**
     * Will quickly fail as we update the print command.
     */
    @Test(timeout = 100)
    public void testRunCommand() {
        CommandRequest request = new CommandRequest("print");
        assertEquals(ce.processRequest(request), "you ran the print command!");
    }
}
