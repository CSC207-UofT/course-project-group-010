package Controller.Commands;

import Controller.AuthHelper;
import Exceptions.ArgumentException;
import Exceptions.NotInDatabaseException;
import Interface.IHasPermission;
import Outer.Database.DatabaseGetter.UserDatabaseGetter;
import UseCase.UserManager;

import java.util.List;

public class NewUserCommand extends Command{
    /**
     * Initializes the command with minimum/maximum arguments
     *
     */
    public NewUserCommand() {
        // type name id
        super(3, 2);
    }

    /**
     * Format of this command is newuser [usertype] [displayname] [id]
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        super.checkArgumentsNum(arguments);
        // super.checkUserExists(ce);
        UserManager um = new UserManager(arguments.get(0), arguments.get(1), arguments.get(2));
        AuthHelper ah = new AuthHelper();
        // ah.checkAuth(um, ce.getUserManager(), "newuser");
        // No auth checks for now, because we have 0 users in the db right now which is unfortunate
        if (!UserDatabaseGetter.getInstance().containsKey(um.getID())) {
            UserDatabaseGetter.getInstance().setEntry(um);
            return "Initialized new user (id=" + um.getID() + ")";
        }
        throw new ArgumentException("User already exists");
    }

    @Override
    public String help() {
        return "format: newuser [student/instructor] [displayname] [id]";
    }
}