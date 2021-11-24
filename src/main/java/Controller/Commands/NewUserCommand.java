package Controller.commands;

import constants.UserType;
import exceptions.ArgumentException;
import Controller.databasegetter.UserDatabaseGetter;
import UseCase.UserManager;

import java.util.List;
import java.util.Scanner;

public class NewUserCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     */
    public NewUserCommand() {
        // type name id
        super(0, 0);
    }

    /**
     * Format of this command is newuser, then follow system instructions
     * Creates a new user. Currently, anyone can create new users for convenience.
     *
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
        Scanner in = new Scanner(System.in);
        System.out.println("Type of user[student/instructor]:");
        String argUserType = in.nextLine().toLowerCase();
        if (!argUserType.equals("student") && !argUserType.equals("instructor")) {
            throw new ArgumentException("Type must be [student/instructor], you entered " + argUserType);
        }
        System.out.println("Display name: ");
        String argDisplayName = in.nextLine();
        System.out.println("ID(no spaces)");
        String argId = in.nextLine().replace(" ", "");
        System.out.println("any spaces were removed. ID is " + argId);

        UserType desiredUserType = getUserType(argUserType);

        UserManager um = new UserManager(desiredUserType, argDisplayName, argId);
        // AuthHelper ah = new AuthHelper();
        // ah.checkAuth(um, ce.getUserManager(), "newuser");
        // No auth checks for now, because we have 0 users in the db right now which is unfortunate
        UserDatabaseGetter.getInstance().addEntry(um);
        return "Added new user with ID " + um.getID() + " and name " + um.getUser().getdisplayName() + "\n" +
                "Run saveall to save this progress.";
    }

    private UserType getUserType(String argUserType) throws ArgumentException {
        UserType desiredUserType;
        switch (argUserType) {
            case "student":
                desiredUserType = UserType.STUDENT;
                break;
            case "instructor":
                desiredUserType = UserType.INSTRUCTOR;
                break;
            default:
                throw new ArgumentException("Invalid user type");
        }
        return desiredUserType;
    }

    @Override
    public String help() {
        return "Creates a new user. No arguments, follow system instructions after entering the command.";
    }
}
