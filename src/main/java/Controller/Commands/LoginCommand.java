package Controller.Commands;

import Controller.Commands.CommandExceptions.CommandNotAuthorizedException;
import Controller.Database.UserDatabase;
import UseCase.UserManager;

import java.util.List;
import java.util.Map;

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
        String s = "Format: login [utorid]";
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
        UserDatabase userDB = new UserDatabase();
        Map<String, String> userMap = userDB.getEntry(id);

        // Loads the user. This code is very dependent on the database, but it'll change in the future.
        // If userMap is not null, then we assume a user was returned with the appropriate fields.
        if (userMap != null) {
            // TODO we're assuming usermap will contain these, ensure it contains these later.
            UserManager mgr = new UserManager(userMap.get("type"),
                    userMap.get("name"),
                    userMap.get("id"),
                    userMap.get("other"));
            ce.addUserManager(mgr);
            return "Logged in as " + mgr.getID();
        }
        return "Couldn't login as " + id;
    }
}
