package Controller.Commands;

import Entity.Course;
import Entity.InstructorUser;
import Entity.Rating;
import UseCase.CourseManager.CourseManager;
import UseCase.CoursePage.CoursePage;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks out a page or review section of a page(to be implemented later)
 * Format for now is checkout [pagename] or checkout r for reviews i guess??
 */
public class CheckoutCommand extends Command{
    private final String reviewArgument = "r";

    public CheckoutCommand() {
        super(1, 1);
    }

    /**
     * Checks out the review section of a page[doesn't exist yet]
     * or a page indicated by course code.
     * Currently can only view "MAT137," and doesn't implement Authorization system.
     * Currently also goes against clean architecture A LOT. sorry :(
     * @param ce
     * @param arguments
     * @return
     * @throws Exception
     */
    @Override
    public String run(CommandExecutor ce, List<String> arguments) throws Exception {
        checkArgumentsNum(arguments);
        checkUserExists(ce);
        // TODO implement this
        // get comment section if r is the argument
        // otherwise if it's a course:
        // look in database if page exists
        if (arguments.get(0).equals("r")) {
            return "Review pages don't exist yet";
        } else {
            // TODO change this hardcode
            if (arguments.get(0).equals("MAT137")) {
                // this is not cash money, why am I initializing entities
                // this constructor is the most complex thing I've ever seen. Bad.
                Course c1 = new Course("Calculus with Proofs", "MAT137");
                Rating r1 = new Rating(c1);
                List<InstructorUser> l1 = new ArrayList<InstructorUser>();
                List<Integer> years = new ArrayList<Integer>();
                CoursePage matPage = new CoursePage(c1, r1, l1, years);
                CourseManager matManager = new CourseManager(matPage);
                ce.setPageManager(matManager);
                return "now viewing " + matManager.getID();
            }
        }
        // Then, no matter what, execute these:
        // look at ce studentmanager(may exception) to get student perm level
        // look at the gotten manager thing to check if action is allowed.
        // set the pageManager to this manager if the action was allowed.
        return "couldn't view page";
    }
}
