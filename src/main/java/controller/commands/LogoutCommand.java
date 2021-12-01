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
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
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

    @Override
    protected void checkAll(CommandExecutor ce, List<String> arguments, String method) throws CommandHelpException, CommandNotAuthorizedException {
        checkHelp(arguments);
        checkUserExists(ce);
    }
}
