package Controller.Commands;

import Interface.IHasPermission;
import Interface.IReadModifiable;

import java.util.List;
import java.util.Map;

public class PrintCommand extends Command {
    /**
     * Initializes object, takes no arguments
     */
    public PrintCommand() {
        super(0, 0);
    }

    /**
     * Prints the currently viewing page. Format is "print"
     *
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelpArgsUserPageAuth(ce, arguments, "print");
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        IHasPermission user = ce.getUserManager();

        Map<String, Object> dataMap = currentlyViewingPage.getData();
        String returnString = "";
        for (String o : dataMap.keySet()) {
            returnString = returnString + o + " : " + dataMap.get(o).toString() + "\n";
        }
        return returnString;
    }

    @Override
    public String help() {
        return "Prints a course's info. Must be viewing a course to use. format: \"print\"";
    }
}
