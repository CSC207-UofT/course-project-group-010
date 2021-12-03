package controller.commands;

import controller.AuthHelper;
import controller.commands.commandHelpers.CommentsPageGetter;
import controller.commands.commandHelpers.CoursePageGetter;
import controller.commands.commandHelpers.PageGetter;
import controller.commands.commandHelpers.UserPageGetter;
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
        String arg = arguments.get(0);
        PageGetter pg = getPageGetter(arg);
        pg.getPage(ce);

        // previous line will throw exception if it fails, so we assume it was successful here.
        return pg.getSuccessString();
    }

    @Override
    public String help() {
        return "checks out a page.\n - checkout [coursecode]: checks out a course\n - checkout -c gets the comment " +
                "section of the page.\n - checkout -u gets the currently logged in user's profile page.";
    }

    private PageGetter getPageGetter(String argument) {
        switch (argument) {
            case "-u":
                return new UserPageGetter();
            case "-c":
                return new CommentsPageGetter();
            default:
                return new CoursePageGetter(argument);
        }
    }
}
