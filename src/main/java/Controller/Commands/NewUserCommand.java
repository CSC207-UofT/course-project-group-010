package Controller.commands;

import constants.UserType;
import exceptions.ArgumentException;
import Controller.databasegetter.UserDatabaseGetter;
import UseCase.UserManager;

import java.util.HashMap;
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
        System.out.println("Type of user[STUDENT/INSTRUCTOR] and Please use Capital letters:");
        String argUserType = in.nextLine().toUpperCase();
        if (!argUserType.equals("STUDENT") && !argUserType.equals("INSTRUCTOR")) {
            throw new ArgumentException("Type must be [STUDENT/INSTRUCTOR], you entered " + argUserType);
        }
        System.out.println("Display name: ");
        String argDisplayName = in.nextLine();
        System.out.println("ID(no spaces)");
        String argId = in.nextLine().replace(" ", "");
        boolean isSame = argId.matches("^[a-z][a-z0-9]*");
        if (!isSame) {
            throw new ArgumentException("Your entered ID is invaild, you entered " + argId);
        }
        System.out.println("any spaces were removed. ID is " + argId);
        UserType desiredUserType = getUserType(argUserType);
        System.out.println("Program Detail: " + "\n" + "Note : Choose from one of following options: ACCOUNTING, ACTUARIAL SCIENCE, ANTHROPOLOGY, APPLIED MATHEMATICS, APPLIED STATISTICS,COMPUTER SCIENCE, DATA SCIENCE");
        String argProgramDetail = in.nextLine().toUpperCase();
        if (!argProgramDetail.equals(" ")) {
            HashMap<String, String> adddetail = new HashMap<>();
            adddetail.put("programDetail", argProgramDetail);
            UserManager um = new UserManager(desiredUserType, argDisplayName, argId, adddetail);
            UserDatabaseGetter.getInstance().addEntry(um);
            return "Added new user with ID " + um.getID() + " and name " + um.getUser().getdisplayName() + "\n" +
                    "Run saveall to save this progress.";
        } else {
            UserManager um = new UserManager(desiredUserType, argDisplayName, argId);
            UserDatabaseGetter.getInstance().addEntry(um);
            return "Added new user with ID " + um.getID() + " and name " + um.getUser().getdisplayName() + "\n" +
                    "Run saveall to save this progress.";
        }

        // AuthHelper ah = new AuthHelper();
        // ah.checkAuth(um, ce.getUserManager(), "newuser");
        // No auth checks for now, because we have 0 users in the db right now which is unfortunate
    }

    private UserType getUserType(String argUserType) throws ArgumentException {
        UserType desiredUserType;
        switch (argUserType) {
            case "STUDENT":
                desiredUserType = UserType.STUDENT;
                break;
            case "INSTRUCTOR":
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
