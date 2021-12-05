package controller.commands;

import constants.UserType;
import controller.commands.commandHelpers.InstructorUserBuilder;
import controller.commands.commandHelpers.StudentUserBuilder;
import controller.commands.commandHelpers.UserBuilder;
import controller.databasegetter.UserDatabaseGetter;
import exceptions.ArgumentException;
import usecase.UserManager;

import java.util.List;
import java.util.Map;
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
     * Creates a new user. Currently, anyone can create new users.
     *
     * @param arguments arguments(none)
     * @return the result of the creation
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelp(arguments);
        super.checkArgumentsNum(arguments);
        Scanner in = new Scanner(System.in);

        // Get use type
        System.out.println("Type of user[STUDENT/INSTRUCTOR]:");
        UserType desiredUserType = processUserType(in.nextLine());
        UserBuilder userBuilder = getUserBuilder(desiredUserType);

        // Get the display name(can be anything)
        System.out.println("Display name: ");
        String argDisplayName = userBuilder.processDisplayName(in.nextLine());

        // Get the id
        System.out.println("ID[lowercase letters followed by lowercase numbers, eg. wangke1");
        String argId = userBuilder.processID(in.nextLine());

        // Process the otherData(eg. programDetail for student, position for instructor)
        System.out.println(userBuilder.getOtherDataPromptString());
        Map<String, String> otherData = userBuilder.processOtherData(in.nextLine());
        UserManager um = createUser(desiredUserType, argDisplayName, argId, otherData);
        return "Added new user with ID " + um.getID() + " and name " + um.getUser().getDisplayName() + "\n" +
                "Run saveall to save this progress.";
    }

    @Override
    public String help() {
        return "Creates a new user. No arguments, follow system instructions after entering the command.";
    }

    // Helper methods

    /**
     * Processes the inputted User type, converting to UserType enum instance
     *
     * @param argUserType argument entered by user
     * @return corresponding enum instance
     * @throws ArgumentException if the user type is invalid
     */
    private UserType processUserType(String argUserType) throws ArgumentException {
        argUserType = argUserType.toUpperCase();

        switch (argUserType) {
            case "STUDENT":
                return UserType.STUDENT;
            case "INSTRUCTOR":
                return UserType.INSTRUCTOR;
            default:
                throw new ArgumentException("'Invalid user type. Type must be [STUDENT/INSTRUCTOR], you entered " + argUserType);
        }
    }

    /**
     * Gets the corresponding user builder for the user type, to help process future user inputs for id, display name and otherData
     *
     * @param userType userType
     * @return corresponding user builder
     * @throws ArgumentException if the user type is invalid
     */
    private UserBuilder getUserBuilder(UserType userType) throws ArgumentException {
        switch (userType) {
            case STUDENT:
                return new StudentUserBuilder();
            case INSTRUCTOR:
                return new InstructorUserBuilder();
            default:
                throw new ArgumentException("Invalid user type");
        }
    }

    /**
     * Creates the user, based on inputted arguments
     *
     * @param desiredUserType usertype
     * @param argDisplayName  display name
     * @param argId           id
     * @param otherData       other data(eg. programDetail for students)
     * @return UserManager created from the data
     */
    private UserManager createUser(UserType desiredUserType, String argDisplayName, String argId, Map<String, String> otherData) throws Exception {
        UserManager um = new UserManager(desiredUserType, argDisplayName, argId, otherData);
        UserDatabaseGetter.getInstance().addEntry(um);
        return um;
    }
}
