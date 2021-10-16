package Controller.Commands;

import Controller.Commands.CommandExceptions.ArgumentException;
import Controller.Commands.CommandExceptions.CommandNotAuthorizedException;
import Interface.IGettable;

import java.util.List;
import java.util.Map;

public class PrintCommand extends Command{
    /**
     * Initializes object, takes no arguments
     */
    public PrintCommand() {
        super(0, 0);
    }

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws ArgumentException {
        this.checkArgumentsNum(arguments);
        IGettable currentlyViewingPage = ce.getPageManager();
        if (currentlyViewingPage != null) {
            Map<String, Object> dataMap = currentlyViewingPage.getData();
            String returnString = "";
            for (String o : dataMap.keySet()) {
                returnString = returnString + o + " : " + dataMap.get(o).toString() + "\n";
            }
            return returnString;
        }
        return "print command failed :(";
    }

    @Override
    public String help() {
        return "Prints a course's info. Must be viewing a course to use. format: \"print\"";
    }
}
