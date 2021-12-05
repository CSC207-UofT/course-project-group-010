package controller.commands;

import controller.databasegetter.CourseDatabaseGetter;
import controller.databasegetter.UserDatabaseGetter;

import java.util.List;

public class SaveAllCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     */
    public SaveAllCommand() {
        super(0, 0);
    }

    /**
     * Saves all courses and users created so far in the session.
     * Format: saveall
     *
     * @return the result of the saving
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelp(arguments);
        UserDatabaseGetter.getInstance().saveAll();
        CourseDatabaseGetter.getInstance().saveAll();
        return "saved all";
    }

    @Override
    public String help() {
        return "saves all users/course editing progress. Format: saveall";
    }
}
