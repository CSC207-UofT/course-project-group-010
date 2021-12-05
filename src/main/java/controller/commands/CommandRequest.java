package controller.commands;

import java.util.List;

/**
 * An object that parse command info from the command line and creates an object.
 * This is here because I don't want CommandExecutor to take text as its command, as the format of these
 * requests might change in the future to HTTP or something.
 */
public class CommandRequest {
    private final String method;
    private final List<String> arguments;

    /**
     * Initializes the commandRequest, splitting into method and arguments
     *
     * @param command text command
     */
    public CommandRequest(String command) {
        String[] splitCommand = command.split(" ");
        this.method = splitCommand[0];
        String[] args = new String[splitCommand.length - 1];
        System.arraycopy(splitCommand, 1, args, 0, splitCommand.length - 1);
        this.arguments = List.of(args);
    }

    public String getMethod() {
        return method;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
