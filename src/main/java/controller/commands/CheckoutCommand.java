package controller.commands;

import controller.AuthHelper;
import controller.commands.commandHelpers.CommentsGetter;
import controller.commands.commandHelpers.UserGetter;
import controller.databasegetter.CourseDatabaseGetter;
import exceptions.ArgumentException;
import interfaces.IHasPermission;
import interfaces.IReadModifiable;
import usecase.CourseManager;

import java.util.List;

/**
 * Checks out a page or review section of a page(to be implemented later)
 * Format for now is checkout [pagename] or checkout r for reviews i guess??
 */
public class CheckoutCommand extends Command {

    /**
     * Initializes a Command with max and minimum argument numbers.
     */
    public CheckoutCommand() {
        super(1, 1);
    }

    /**
     * Checks out a page with given id
     * or you can check out your user page using -u, or the comment section of a course using -c
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
        // Chose to not use a factory design pattern as there are only two options for now.
        // The commentsGetter/userGetter serves to help the Single Responsibility principle.
        if (arguments.get(0).equals("-c")) {
            checkPageAuth(ce);

            // all necessary checks should have been run by checkPageAuth
            new CommentsGetter().getCommentSection(ce);
            return "now viewing comment section for page";
        } else if (arguments.get(0).equals("-u")){
            new UserGetter().getUserPage(ce);
            return "now viewing profile of " + ce.getUserManager().getID();
        } else {

            getCourseFromDB(ce, id);
            return "now viewing course " + id;
        }
    }

    @Override
    public String help() {
        return "checks out a page.\n - checkout [coursecode]: checks out a course\n - checkout -c gets the comment " +
                "section of the page.\n - checkout -u gets the currently logged in user's profile page.";
    }

    /**
     * Checks if the user is currently viewing a page that allows the "getcomments" functionality
     * (implying there exists a comment section)
     * @param ce
     * @return
     * @throws Exception
     */
    private boolean checkPageAuth(CommandExecutor ce) throws Exception{
        checkViewingPageExists(ce);
        AuthHelper ah = new AuthHelper();
        IReadModifiable page = ce.getPageManager();
        IHasPermission user = ce.getUserManager();
        ah.checkAuth(page, user, "getcomments");
        return true;
    }

    // TODO consider putting this in a separate class
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
}
