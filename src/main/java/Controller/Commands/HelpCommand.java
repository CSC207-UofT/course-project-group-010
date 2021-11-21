package Controller.Commands;

import Constants.CommandConstants;

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
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkArgumentsNum(arguments);
        StringBuilder retStr = new StringBuilder();
        retStr.append("Welcome to the course system! You can login and view/interact with courses\n");
        retStr.append("For any of the following commands, type [commandname] -h for detailed usage.\n");
        retStr.append("type 'end' to end the program. It will automatically save all progress\n");
        retStr.append("=============== Commands ================\n");
        retStr.append("- Debugging commands: newuser, createcourse, listusers, listcourses\n");
        retStr.append("- Logging in: login, logout\n");
        retStr.append("- Basic functions: checkout, print, rate\n");
        retStr.append("- Comment Navigation: displayfullthread[deprecated], displaysubsetthread, getcomments[deprecated], getpath\n");
        retStr.append("- Comment Interaction: vote, reply\n");
        retStr.append("- Saving: saveall");

        return retStr.toString();

    }

    /**
     * The old version of the run method. Is not currently in use.
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    private String runLegacy(CommandExecutor ce, List<String> arguments) throws Exception {
        checkArgumentsNum(arguments);
        String retStr = "";
        CommandConstants commandConstants = new CommandConstants();
        for (String key : commandConstants.command_dict.keySet()) {
            retStr = retStr + "[" + key + "] : " + commandConstants.get(key).help() + "\n";
        }
        return retStr;
    }

    @Override
    public String help() {
        return "Help command";
    }
}
