package Controller.Commands;

import Constants.CommandConstants;
import Interface.IGettable;
import UseCase.UserManager.UserManager;

/**
 * CommandExecutor will hold the necessary information for executing a command, such as
 * The user it's executing commands on behalf of, and the page that the user is currently viewing.
 * It will execute commands.
 *
 * it is a singleton class, meaning that every instance of the CLI will have one CommandExecutor active
 * at a time(is that even good...)
 */
public class CommandExecutor {
    private static CommandExecutor instance = null;
    private UserManager userManager = null;
    private IGettable pageManager = null;


    /**
     * Does literally nothing. Initializes a CommandExecutor though.
     */
    private CommandExecutor() {
    }

    /**
     * Processes a request sent to the command executor
     * @param request
     * @throws Exception
     */
    // TODO change the return type to the appropriate type
    private void processRequest(CommandRequest request) throws Exception {
        CommandConstants commandConstants = new CommandConstants();
        Command command = commandConstants.get(request.getMethod());
        command.run(this, request.getArguments());
    }

    /**
     * gets the single instance of CommandExecutor, makes a new one if none exist
     * @return the instance.
     */
    public CommandExecutor getInstance() {
        if (instance == null) {
            instance = new CommandExecutor();
        }
        return instance;
    }
}
