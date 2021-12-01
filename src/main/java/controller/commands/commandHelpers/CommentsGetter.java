package controller.commands.commandHelpers;

import controller.commands.CommandExecutor;
import usecase.CommentPresenter;
import exceptions.CommandNotAuthorizedException;
import usecase.CommentManager;
import usecase.CourseManager;

public class CommentsGetter {

    /**
     * This is an example of SRP, I wrapped CommentPresenter, and only have to change this one thing because I isolated
     * the functionality.
     * Gets the comment section of the course that the user is currently viewing, if possible.
     * @param ce
     * @throws CommandNotAuthorizedException
     */
    public void getCommentSection(CommandExecutor ce) throws CommandNotAuthorizedException {
        try {
            // Get use case comment manager
            CommentManager cm = ((CourseManager) ce.getPageManager()).getCommentSection();

            // wrap it, and set as the page manager
            ce.setPageManager(new CommentPresenter(cm));
        } catch (RuntimeException e) {
            // casting exception, etc.
            throw new CommandNotAuthorizedException("Current viewing page does not have a comment section");
        }
        // otherwise, just throw the exception upwards.
    }
}
