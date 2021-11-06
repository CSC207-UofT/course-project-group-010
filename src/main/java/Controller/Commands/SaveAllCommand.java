package Controller.Commands;

import Controller.DatabaseGetter.CourseDatabaseGetter;
import Controller.DatabaseGetter.UserDatabaseGetter;

import java.util.List;

public class SaveAllCommand extends Command{
    /**
     * Initializes the command with minimum/maximum arguments
     *
     */
    public SaveAllCommand() {
        super(0, 0);
    }

    @Override
    public String help() {
        return "saves all users/course editing progress.";
    }

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        UserDatabaseGetter.getInstance().saveAll();
        CourseDatabaseGetter.getInstance().saveAll();
        return "saved all";
    }
}
