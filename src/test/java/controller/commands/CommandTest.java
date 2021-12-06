package controller.commands;

import constants.CommandConstants;
import controller.commands.commandHelpers.DataPrinter;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testNoCommand() {
        CommandRequest request = new CommandRequest("asdf");
        assertEquals(ce.processRequest(request), "Command not Found");
    }

    @Test
    public void testInvalidArgs() {
        CommandRequest request = new CommandRequest("print asdf agaj fklaj");
        assertEquals(ce.processRequest(request), "Invalid number of Arguments");
    }

    @Test
    public void testDataPrinter() {
        DataPrinter dp = new DataPrinter();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put(CommandConstants.allDataString, "hello");
        map2.put("hello", "goodbye");
        assertEquals("hello", dp.printData(map1));
        assertEquals(true, dp.printData(map2).contains("hello"));
    }
}
