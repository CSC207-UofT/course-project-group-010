package controller.commands.coursecommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import controller.commands.commandHelpers.DataPrinter;
import interfaces.IReadModifiable;

import java.util.List;
import java.util.Map;

public class PrintCommand extends Command {
    /**
     * Initializes a Command with max and minimum argument numbers.
     */
    public PrintCommand() {
        super(0, 0);
    }

    /**
     * Prints the currently viewing page. Format is "print"
     * Is able to print course pages and comment pages(anything implementing IGettable)
     *
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "print");
        IReadModifiable currentlyViewingPage = ce.getPageManager();

        Map<String, Object> dataMap = currentlyViewingPage.getData();
        // Single responsibility principle
        DataPrinter dp = new DataPrinter();
        return dp.printData(dataMap);
    }

    @Override
    public String help() {
        return "Prints the page you are currently viewing. Format: \"print\"\n" +
                "This includes course pages, user profiles, comment sections.";
    }
}
