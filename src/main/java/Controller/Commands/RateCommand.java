package Controller.Commands;

import UseCase.CourseManager.CourseManager;

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
        checkHelp(arguments);
        checkArgumentsNum(arguments);
        checkUserExists(ce);
        if (arguments.get(0) == "rm") {
            // TODO implement this
            return "Removing rating is not implemented yet";
        } else {
            // remember to try catch
            // get the auth helper to handle rating. If a page can't be rated anyways it will say no
            // TODO make authhelper throw the notauthorizedexception so then it's not possible to move forward in this code.
            // if it's possible then we'll rate it by calling the right function.
            // TODO change this, make a new interface or something to deal with this
            if (ce.getUserManager() != null &&
                    ce.getPageManager() instanceof CourseManager) {
                ((CourseManager) ce.getPageManager()).updateRating(Integer.parseInt(arguments.get(0)),
                        ce.getUserManager());
                // TODO add getters for these things on CourseManager
                // I can't get the rating for the course without either accessing the entire getData() map
                // or accessing the entity object that is inside CourseManager
                return "Rated " + ((CourseManager) ce.getPageManager()).getID();
            }
        }
        return "Unable to rate.";
    }

    @Override
    public String help() {
        return "Rates a course, must be viewing a course to use. Format \"rate [1-10]\"\n currently we don't " +
                "check if your rating is between 1-10.";
    }
}
