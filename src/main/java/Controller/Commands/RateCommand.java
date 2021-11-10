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

    /**
     * Rates the currently viewing page if it is a course.
     * Format is rate [1-10]
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelpArgsUserPageAuth(ce, arguments, "rate");
        checkUserExists(ce);
        if (arguments.get(0) == "rm") {
            // TODO implement this
            return "Removing rating is not implemented yet";
        } else {
            if (ce.getPageManager() instanceof CourseManager) {
                ((CourseManager) ce.getPageManager()).updateRating(Integer.parseInt(arguments.get(0)),
                        ce.getUserManager());
                return "Rated " + ((CourseManager) ce.getPageManager()).getID();
            }
        }
        return "Unable to rate. Make sure you are viewing a course.";
    }

    @Override
    public String help() {
        return "Rates a course, must be viewing a course to use. Format \"rate [1-10]\"";
    }
}
