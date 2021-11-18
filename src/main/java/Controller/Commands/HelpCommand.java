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
