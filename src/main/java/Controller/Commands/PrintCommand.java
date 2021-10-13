package Controller.Commands;

import Interface.IGettable;

import java.util.List;

public class PrintCommand extends Command{
    /**
     * Initializes object, takes no arguments
     */
    public PrintCommand() {
        super(0, 0);
    }

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        IGettable currentlyViewingPage = ce.getPageManager();
        if (currentlyViewingPage != null) {
            // TODO call its IGettable method to get the string and return it
        }
        return "you ran the print command!";
    }
}
