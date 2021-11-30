package controller.commands.debuggingcommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import controller.databasegetter.CourseDatabaseGetter;

import java.util.List;

public class ListCoursesCommand extends Command {
    /**
     * Initializes the command with minimum/maximum arguments
     */
    public ListCoursesCommand() {
        super(0, 0);
    }

    /**
     * Lists all the courses in the database.
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "listcourses");
        CourseDatabaseGetter cdg = CourseDatabaseGetter.getInstance();
        String str = cdg.toString().equals("") || cdg.toString().equals("\n") ? "No courses" : cdg.toString();
        return "This is a DEBUGGING method: \n" + str;
    }

    @Override
    public String help() {
        return "This is a DEBUGGING method, that will list all available courses and their IDs";
    }

    @Override
    protected void checkAll(CommandExecutor ce, List<String> arguments, String method) throws Exception {
        checkHelp(arguments);
        checkArgumentsNum(arguments);
    }
}
