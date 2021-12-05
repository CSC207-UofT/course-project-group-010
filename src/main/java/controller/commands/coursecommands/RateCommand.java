package controller.commands.coursecommands;

import controller.commands.Command;
import controller.commands.CommandExecutor;
import exceptions.ArgumentException;
import usecase.CourseManager;

import java.util.List;

/**
 * Rate a course??
 * format is rate [x] where x is between 1 and 10
 * User can also "rate rm" to remove their rating
 */
public class RateCommand extends Command {

    /**
     * Initializes a Command with max and minimum argument numbers.
     */
    public RateCommand() {
        super(1, 1);
    }

    /**
     * Rates the currently viewing page if it is a course.
     * Format is rate [1-10]
     *
     * @param ce commandExecutor
     * @param arguments arguments(rating number)
     * @return return string
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkAll(ce, arguments, "rate");
        // if the user is allowed to rate, that implies it is a student viewing a coursemanager page.
        try {
            ((CourseManager) ce.getPageManager()).addRating(Double.parseDouble(arguments.get(0)),
                    ce.getUserManager().getUser());
        } catch (NumberFormatException e) {
            throw new ArgumentException("Invalid. Make sure you enter a number as a rating(you entered " + arguments.get(0) + ")");
        }

        return "Rated " + ((CourseManager) ce.getPageManager()).getID();
    }

    @Override
    public String help() {
        return "Rates a course, must be viewing a course to use. Format \"rate [1-10]\"";
    }
}
