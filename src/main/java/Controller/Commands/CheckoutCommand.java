package Controller.Commands;

import Controller.AuthHelper;
import Controller.DatabaseGetter.CourseDatabaseGetter;
import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;
import Exceptions.ArgumentException;
import UseCase.CourseManager.CourseManager;
import UseCase.CoursePage.CoursePage;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks out a page or review section of a page(to be implemented later)
 * Format for now is checkout [pagename] or checkout r for reviews i guess??
 */
public class CheckoutCommand extends Command {
    private final String reviewArgument = "r";

    public CheckoutCommand() {
        super(1, 1);
    }

    /**
     * Checks out the review section of a page[doesn't exist yet]
     * or a page indicated by course code.
     * Currently can only view "MAT137," and doesn't implement Authorization system.
     * Currently also goes against clean architecture A LOT. sorry :(
     *
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkHelp(arguments);
        checkArgumentsNum(arguments);
        checkUserExists(ce);
        String id = arguments.get(0);
        // TODO get comment section if r.
        if (arguments.get(0).equals("r")) {
            return "Review pages don't exist yet";
        } else {

            CourseDatabaseGetter cdg = CourseDatabaseGetter.getInstance();
            CourseManager mgr = cdg.getEntry(id);
            if (mgr == null) {
                throw new ArgumentException("Course not found in Database");
            } else {
                AuthHelper ah = new AuthHelper();
                ah.checkAuth(mgr, ce.getUserManager(), "checkout");
                ce.setPageManager(mgr);
                return "now viewing course: " + id;
            }
        }
    }

    @Override
    public String help() {
        return "checks out a page. eg. checkout MAT137";
    }
}
