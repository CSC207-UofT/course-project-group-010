package Controller.Commands;

import java.util.List;

/**
 * Rate a course??
 * format is rate [x] where x is between 1 and 10
 * User can also "rate rm" to remove their rating
 */
public class RateCommand extends Command{

    public RateCommand() {
        super(1, 1);
    }

    // TODO implement this method
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkArgumentsNum(arguments);
        if (arguments.get(0) == "rm") {
            // implement this
        } else {
            // remember to try catch
            // get the auth helper to handle rating. If a page can't be rated anyways it will say no
            // TODO make authhelper throw the notauthorizedexception so then it's not possible to move forward in this code.

            // if it's possible then we'll rate it by calling the right function.
        }
        return "method not implemented yet";
    }
}
