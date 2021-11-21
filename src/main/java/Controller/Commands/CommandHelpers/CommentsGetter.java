package Controller.Commands.CommandHelpers;

import Controller.Commands.CommandExecutor;
import Controller.CommentPresenter;
import Exceptions.CommandNotAuthorizedException;
import UseCase.CommentManager.CommentManager;
import UseCase.CourseManager.CourseManager;

import java.io.InvalidClassException;

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
            CommentManager cm = ((CourseManager) ce.getPageManager()).getOnlyComment();

            // wrap it, and set as the page manager
            ce.setPageManager(new CommentPresenter(cm));
        } catch (RuntimeException e) {
            // casting exception, etc.
            throw new CommandNotAuthorizedException("Current viewing page does not have a comment section");
        }
        // otherwise, just throw whatever exception upwards.
    }
}