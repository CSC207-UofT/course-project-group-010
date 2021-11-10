package Controller.Commands;

import UseCase.UserManager;

import java.util.List;

public class LogoutCommand extends Command{


    /**
     * Initializes the command with minimum/maximum arguments
     *
     */
    public LogoutCommand() {
        super(0, 0);
    }

    /**
     * Logs out of the current user. Stops viewing the current page that the user was viewing.
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelp(arguments);
        checkUserExists(ce);
        UserManager um = ce.getUserManager();
        ce.resetAll();
        return "Logged out of " + um.getUser().getdisplayName();
    }
}
