package controller.commands;

import controller.databasegetter.UserDatabaseGetter;
import exceptions.CommandNotAuthorizedException;
import usecase.UserManager;

import java.util.List;

/**
 * Login command.
 * Current format is login[utorID]
 */
public class LoginCommand extends Command {

    /**
     * Initializes a Command with max and minimum argument numbers.
     */
    public LoginCommand() {
        super(1, 1);
    }

    /**
     * Attempts to login.
     *
     * @param arguments arguments(user id)
     * @return the result of the command
     * @throws Exception if user is not found
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "login");
        String id = arguments.get(0);
        UserDatabaseGetter userDB = UserDatabaseGetter.getInstance();

        // this will throw NotInDatabaseException upwards if user is not found, which is fine.
        UserManager mgr = userDB.getEntry(id);
        ce.addUserManager(mgr);
        return "Logged in as " + mgr.getUser().getDisplayName();
    }

    @Override
    public String help() {
        return "Attempts login. Format: login [id]\n Example: \"login 12345\"";
    }

    @Override
    protected void checkAll(CommandExecutor ce, List<String> arguments, String method) throws Exception {
        checkHelp(arguments);
        checkArgumentsNum(arguments);
        if (ce.getUserManager() != null) {
            throw new CommandNotAuthorizedException("Already logged in.");
        }
    }
}
