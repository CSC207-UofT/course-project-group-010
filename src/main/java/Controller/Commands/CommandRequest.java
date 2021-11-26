package Controller.Commands;

import java.util.ArrayList;
import java.util.List;

/**
 * An object that parse command info from the command line and creates an object.
 * This is here because I don't want CommandExecutor to take text as its command, as the format of these
 * requests might change in the future to HTTP or something.
 */
public class CommandRequest {
    private String method;
    private List<String> arguments;

    /**
     * Initializes the commandRequest, splitting into method and arguments
     *
     * @param command
     */
    public CommandRequest(String command) {
        String[] splitCommand = command.split(" ");
        this.method = splitCommand[0];
        String[] args = new String[splitCommand.length - 1];
        for (int i = 1; i < splitCommand.length; i++) {
            args[i - 1] = splitCommand[i];
        }
        this.arguments = List.of(args);
    }

    public String getMethod() {
        return method;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
