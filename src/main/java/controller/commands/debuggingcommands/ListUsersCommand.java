package controller.commands.debuggingcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import controller.databasegetter.UserDatabaseGetter;

import java.util.List;

public class ListUsersCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     */
    public ListUsersCommand() {
        super(0, 0);
    }

    /**
     * Lists all available users and their IDs.
     * @param ce commandExecutor
     * @param arguments user arguments(none required)
     * @return string representation of all users in database.
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "listusers");
        UserDatabaseGetter udg = UserDatabaseGetter.getInstance();
        String str = udg.toString().equals("") || udg.toString().equals("\n") ? "No users" : udg.toString();
        return "This is a DEBUGGING method: \n" + str;
    }

    @Override
    public String help() {
        return "This is a DEBUGGING method, that will list all available users and their IDs";
    }

    /**
     * checks all necessary requirements for the run() method to proceed
     * @throws Exception
     */
    @Override
    protected void checkAll(CommandExecutor ce, List<String> arguments, String method) throws Exception {
        checkHelp(arguments);
        checkArgumentsNum(arguments);
    }
}
