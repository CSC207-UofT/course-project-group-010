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
public class LoginCommand extends Command{
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
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    // Currently only possible id is 12345
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkArgumentsNum(arguments);
        String id = arguments.get(0);
        // TODO check if ce already has a user, throw otherwise
        if (ce.getUserManager() != null) {
            throw new CommandNotAuthorizedException("Already logged in.");
        }
        // TODO check utorid in database, get data and initialize the usermanager if needed
        UserDatabaseGetter userDB = UserDatabaseGetter.getInstance();

        // Loads the user. This code is very dependent on the database, but it'll change in the future.
        // If userMap is not null, then we assume a user was returned with the appropriate fields.
        // TODO we're assuming usermap will contain these, ensure it contains these later.
        UserManager mgr = userDB.getEntry(id);
        if (mgr == null) {
            throw new ArgumentException("User not found in Database");
        } else {
            ce.addUserManager(mgr);
            return "Logged in as " + mgr.getUser().getdisplayName();
        }
    }
}
