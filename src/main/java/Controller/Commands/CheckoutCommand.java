package Controller.commands;

import Controller.AuthHelper;
import Controller.commands.commandHelpers.CommentsGetter;
import Controller.commands.commandHelpers.UserGetter;
import Controller.databasegetter.CourseDatabaseGetter;
import exceptions.ArgumentException;
import Interface.IHasPermission;
import Interface.IReadModifiable;
import UseCase.courseManager.CourseManager;

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
        if (arguments.get(0).equals("-c")) {
            checkPageAuth(ce);
            CommentsGetter cg = new CommentsGetter();

            // all necessary checks should have been run by checkPageAuth
            cg.getCommentSection(ce);
            return "now viewing comment section for page";
        } else if (arguments.get(0).equals("-u")){
            new UserGetter().getUserPage(ce);
            return "now viewing profile of " + ce.getUserManager().getID();
        } else {

            getCourseFromDB(ce, id);
            return "now viewing course " + id;
        }
    }

    private boolean checkPageAuth(CommandExecutor ce) throws Exception{
        checkViewingPageExists(ce);
        AuthHelper ah = new AuthHelper();
        IReadModifiable page = ce.getPageManager();
        IHasPermission user = ce.getUserManager();
        ah.checkAuth(page, user, "getcomments");
        return true;
    }

    private void getCourseFromDB(CommandExecutor ce, String id) throws Exception {
        CourseDatabaseGetter cdg = CourseDatabaseGetter.getInstance();
        CourseManager mgr = cdg.getEntry(id);
        if (mgr == null) {
            throw new ArgumentException("Course not found in Database");
        } else {
            AuthHelper ah = new AuthHelper();
            ah.checkAuth(mgr, ce.getUserManager(), "checkout");
            ce.setPageManager(mgr);
        }
    }

    @Override
    public String help() {
        return "checks out a page.\n - checkout [coursecode]: checks out a course\n - checkout -c gets the comment " +
                "section of the page.\n - checkout -u gets the currently logged in user's profile page.";
    }
}
