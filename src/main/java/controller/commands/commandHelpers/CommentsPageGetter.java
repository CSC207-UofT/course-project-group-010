package controller.commands.commandHelpers;

import controller.commands.CommandExecutor;
import exceptions.CommandNotAuthorizedException;
import usecase.CommentManager;
import usecase.CommentPresenter;
import usecase.CourseManager;

public class CommentsPageGetter implements PageGetter{

    /**
     * This is an example of SRP, I wrapped CommentPresenter, and only have to change this one thing because I isolated
     * the functionality.
     * Gets the comment section of the course that the user is currently viewing, if possible.
     * @param ce
     * @throws CommandNotAuthorizedException
     */
    public void getPage(CommandExecutor ce) throws CommandNotAuthorizedException {
        try {
            // Get use case comment manager
            CommentManager cm = ((CourseManager) ce.getPageManager()).getCommentSection();

            // wrap it, and set as the page manager
            ce.setPageManager(new CommentPresenter(cm));
        } catch (RuntimeException e) {
            // casting exception, etc.
            throw new CommandNotAuthorizedException("No comment section found. Make sure you are viewing a course page.");
        }
        // otherwise, just throw the exception upwards.
    }

    @Override
    public String getSuccessString() {
        return "now viewing comment section for page";
    }
}
