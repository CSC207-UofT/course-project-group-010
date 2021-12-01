package controller.commands;

import exceptions.ArgumentException;
import exceptions.CommandNotAuthorizedException;
import controller.databasegetter.UserDatabaseGetter;
import exceptions.NotInDatabaseException;
import usecase.UserManager;

import java.io.IOException;
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

    @Override
    public String help() {
        String s = "Attempts login. Format: login [id]\n Example: \"login 12345\"";
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
        checkAll(ce, arguments, "login");
        String id = arguments.get(0);
        UserDatabaseGetter userDB = UserDatabaseGetter.getInstance();

        // this will throw NotInDatabaseException upwards if user is not found, which is fine.
        UserManager mgr = userDB.getEntry(id);
        ce.addUserManager(mgr);
        return "Logged in as " + mgr.getUser().getDisplayName();
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
