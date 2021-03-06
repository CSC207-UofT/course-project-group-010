package controller.commands;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandRequestTest {

    @Test(timeout = 100)
    public void testInit() {
        CommandRequest req = new CommandRequest("login 2334 abcd efg-h");
        assertEquals(req.getMethod(), "login");
        System.out.println(req.getArguments());
        assertEquals(req.getArguments().size(), 3);
    }

    @Test(timeout = 100)
    public void testNoArgs() {
        CommandRequest req = new CommandRequest("login");
        assertEquals(req.getMethod(), "login");
        assertEquals(req.getArguments().size(), 0);
    }

    @Test(timeout = 100)
    public void testBlank() {
        CommandRequest req = new CommandRequest("");
        assertEquals(req.getMethod(), "");
        assertEquals(req.getArguments().size(), 0);
    }
}
