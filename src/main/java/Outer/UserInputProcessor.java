package Outer;

import Controller.Commands.CommandRequest;

/**
 * Processes user input and changes it to a format suitable for the CommandExecutor.
 * Currently it basically does nothing, but in the future if there were http requests to a server
 * or we had a GUI or something, this may do something useful.
 */
public class UserInputProcessor {

    public CommandRequest processCommand(String command) {
        return new CommandRequest(command);
    }
}
