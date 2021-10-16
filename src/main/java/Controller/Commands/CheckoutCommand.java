package Controller.Commands;

import java.util.List;

/**
 * Checks out a page or review section of a page(to be implemented later)
 * Format for now is checkout [pagename] or checkout r for reviews i guess??
 */
public class CheckoutCommand extends Command{
    private final String reviewArgument = "r";

    public CheckoutCommand() {
        super(0, 1);
    }

    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        try {
            checkArgumentsNum(arguments);
        } catch (Exception e) {
            // TODO change this
            return "bruh";
        }
        // TODO implement this
        // get comment section if c is the argument
        // otherwise if it's a course:
        // look in database if page exists

        // Then, no matter what, execute these:
        // look at ce studentmanager(may exception) to get student perm level
        // look at the gotten manager thing to check if action is allowed.
        // set the pageManager to this manager if the action was allowed.
        return "couldn't view page";
    }
}
