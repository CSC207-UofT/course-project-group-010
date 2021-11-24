package Controller.commands;

import constants.CommandConstants;
import exceptions.CommandNotFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimulatedInputTest {
    CommandExecutor ce;

    @Before
    public void setup() {
        ce = CommandExecutor.getInstance();
    }

    @Test (timeout = 100)
    public void testArgMissing() {
        String output = testcommand("asdf");
        assertEquals(output, "Command not Found");
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
