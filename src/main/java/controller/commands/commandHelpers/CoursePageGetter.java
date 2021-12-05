package controller.commands.commandHelpers;

import controller.AuthHelper;
import controller.commands.CommandExecutor;
import controller.databasegetter.CourseDatabaseGetter;
import exceptions.ArgumentException;
import exceptions.CommandNotAuthorizedException;
import exceptions.NotInDatabaseException;
import usecase.CourseManager;

import java.io.IOException;

public class CoursePageGetter implements PageGetter {
    private final String id;

    /**
     * Initializes a new coursePageGetter that is meant to get the course with id [id]
     *
     * @param id the id of the course to get
     */
    public CoursePageGetter(String id) {
        this.id = id;
    }

    /**
     * gets the course page with the specified id
     *
     * @param ce the command executor to put the page in
     */
    @Override
    public void getPage(CommandExecutor ce) throws CommandNotAuthorizedException, ArgumentException, IOException, ClassNotFoundException, NotInDatabaseException {
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

    /**
     * @return The string to return if getPage is successful
     */
    @Override
    public String getSuccessString() {
        return "now viewing course " + id;
    }
}
