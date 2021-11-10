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

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelp(arguments);
        checkUserExists(ce);
        UserManager um = ce.getUserManager();
        ce.resetUserManager();
        return "Logged out of " + um.getUser().getdisplayName();
    }
}
