package controller.commands;

import constants.CommandConstants;

import java.util.List;

public class HelpCommand extends Command {

    /**
     * Initializes the command with minimum/maximum arguments
     */
    public HelpCommand() {
        super(0, 0);
    }

    /**
     * Returns a list of command method names, and their entire help string.
     * For individual command help text, you can type [method] -h
     *
     * @return the help string
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkArgumentsNum(arguments);
        String retStr = "Welcome to the course system! You can login and view/interact with courses\n" +
                "For any of the following commands, type [commandname] -h for detailed usage.\n" +
                "type 'end' to end the program. It will automatically save all progress\n" +
                "=============== Commands ================\n" +
                "- [Debugging] getting started: newuser, createcourse, listusers, listcourses\n" +
                "- Logging in: login, logout\n" +
                "- Basic functions: checkout, print, rate\n" +
                "- Comment Navigation: getcomments[deprecated], cd\n" +
                "- Comment Interaction: vote, reply\n" +
                "- Saving: saveall";

        return retStr;

    }
}
