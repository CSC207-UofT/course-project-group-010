package Controller.Commands;

import Exceptions.ArgumentException;
import Exceptions.CommandNotAuthorizedException;
import Controller.DatabaseGetter.UserDatabaseGetter;
import UseCase.UserManager;

import java.util.List;

/**
 * Login command.
 * Current format is login[utorID]
 */
public class LoginCommand extends Command {
    /**
     * The format for a login command is login [utorID] for now
     * so it will only take 1 argument
     */
    public LoginCommand() {
        super(1, 1);
    }

    @Override
    public String help() {
        String s = "Attempts login. Format: login [utorid]\n Format: \"login 12345\"";
        return s;
    }

    /**
     * Attempts to login.
     *
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelp(arguments);
        checkArgumentsNum(arguments);
        String id = arguments.get(0);
        if (ce.getUserManager() != null) {
            throw new CommandNotAuthorizedException("Already logged in.");
        }
        UserDatabaseGetter userDB = UserDatabaseGetter.getInstance();
        UserManager mgr = userDB.getEntry(id);
        if (mgr == null) {
            throw new ArgumentException("User not found in Database");
        } else {
            ce.addUserManager(mgr);
            return "Logged in as " + mgr.getUser().getdisplayName();
        }
    }
}
