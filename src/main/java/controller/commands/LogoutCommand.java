package controller.commands;

import exceptions.CommandHelpException;
import exceptions.CommandNotAuthorizedException;
import usecase.UserManager;

import java.util.List;

public class LogoutCommand extends Command {


    /**
     * Initializes the command with minimum/maximum arguments
     */
    public LogoutCommand() {
        super(0, 0);
    }

    /**
     * Logs out of the current user. Stops viewing the current page that the user was viewing.
     *
     * @return the result of the command
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "logout");
        UserManager um = ce.getUserManager();
        ce.resetAll();
        return "Logged out of " + um.getUser().getDisplayName();
    }

    @Override
    public String help() {
        return "logs out. format: logout";
    }

    /**
     * Checks all necessary conditions for the run() method to proceed.
     * The user must be logged in in order to logout, basically.
     */
    @Override
    protected void checkAll(CommandExecutor ce, List<String> arguments, String method) throws CommandHelpException, CommandNotAuthorizedException {
        checkHelp(arguments);
        checkUserExists(ce);
    }
}
