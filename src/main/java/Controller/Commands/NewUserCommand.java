package Controller.Commands;

import Constants.UserType;
import Controller.AuthHelper;
import Entity.User;
import Exceptions.ArgumentException;
import Controller.DatabaseGetter.UserDatabaseGetter;
import UseCase.UserManager;

import java.util.List;
import java.util.Locale;

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
     * Creates a new user. Currently, anyone can create new users for convenience.
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelp(arguments);
        super.checkArgumentsNum(arguments);
        // super.checkUserExists(ce);

        String argUserType = arguments.get(0).toLowerCase();
        String argDisplayName = arguments.get(1);
        String argId = arguments.get(2);

        UserType desiredUserType = switch (argUserType) {
            case "student" -> UserType.STUDENT;
            case "instructor" -> UserType.INSTRUCTOR;
            default -> throw new ArgumentException("Invalid user type");
        };

        UserManager um = new UserManager(desiredUserType, arguments.get(1), arguments.get(2));
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
