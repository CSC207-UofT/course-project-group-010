package Controller.Commands;

import Constants.CommandConstants;
import Interface.IAuthorizable;
import Interface.IGettable;
import Interface.IReadModifiable;
import UseCase.UserManager;

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
    private IReadModifiable pageManager = null;


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
    public String processRequest(CommandRequest request) {
        CommandConstants commandConstants = new CommandConstants();
        try {
            Command command = commandConstants.get(request.getMethod());
            return command.run(getInstance(), request.getArguments());
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void addUserManager(UserManager u) {
        if (this.userManager == null) {
            this.userManager = u;
        }
    }

    public IReadModifiable getPageManager() {
        return pageManager;
    }

    public void setPageManager(IReadModifiable pageManager)
    {
        this.pageManager = pageManager;
    }

    /**
     * gets the single instance of CommandExecutor, makes a new one if none exist
     * @return the instance.
     */
    public static CommandExecutor getInstance() {
        if (instance == null) {
            instance = new CommandExecutor();
        }
        return instance;
    }
}
