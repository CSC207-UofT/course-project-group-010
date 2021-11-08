package Controller.Commands;

import Controller.AuthHelper;
import Exceptions.ArgumentException;
import Exceptions.CommandNotAuthorizedException;
import Interface.IGettable;
import Interface.IHasPermission;
import Interface.IReadModifiable;
import UseCase.UserManager;

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
    public String run(CommandExecutor ce, List<String> arguments) throws ArgumentException, CommandNotAuthorizedException {
        this.checkArgumentsNum(arguments);
        super.checkUserExists(ce);
        super.checkViewingPageExists(ce);
        IReadModifiable currentlyViewingPage = ce.getPageManager();
        IHasPermission user = ce.getUserManager();
        new AuthHelper().checkAuth(currentlyViewingPage, user, "print");

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
